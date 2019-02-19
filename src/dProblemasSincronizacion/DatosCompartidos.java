package dProblemasSincronizacion;

class Historial {
	String[] mensajes = new String[100];
	byte pos = 0;	//we can add the field 'volatile'

	public void agregar(String msg) {
		mensajes[pos++] = msg;
	}
	public void mostrar() {
		for (int i = 0; i < mensajes.length; i++)
			System.out.println("Posición " + i + " tiene: " + mensajes[i]);
	}
}

class Habla extends Thread {
	String mensaje;
	Historial historial;

	public Habla(String msg, Historial h) {
		mensaje = msg;
		historial = h;
	}
	public void run() {
		for (int i = 0; i < 50; i++) {
			historial.agregar(mensaje);
			// yield();
		}
	}
}

public class DatosCompartidos { 
	public static void main(String[] args) { 
		Historial historial = new Historial (); 
		new Habla ("Hola", historial).start(); 
		new Habla ("Adiós", historial).start(); 
		try { 
			Thread.sleep(2000); 
		} catch (InterruptedException e) { 
			e.printStackTrace(); 
		} 
		historial.mostrar();
	}
}

