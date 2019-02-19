package dProblemasSincronizacion;

class Puerta {
	//atributos del objeto compartido
	public static boolean abierta;
	public static int rapido;
	public static int lento;
}

class Rapido extends Thread {
	public void run() {
		for (int i = 0; i < 1000; i++)
			if (Puerta.abierta) {
				Puerta.abierta = false;
				Puerta.rapido++;
				Puerta.abierta = true;
			}
		System.out.println("Rápido terminando");
	}
}

class Lento extends Thread {
	public void run() {
		for (int i = 0; i < 1000; i++)
			try {
				sleep(10); 							//10 ms x 100 = 1 seg
			} catch (InterruptedException e) {		//10 ms en cada iteración del bucle
				e.printStackTrace();
			}
		if (Puerta.abierta) {
			Puerta.abierta = false;
			Puerta.lento++;
			Puerta.abierta = true;
		}
		System.out.println("Lento terminando");
	}
}

public class Inanicion { 
	public static void main(String[] args) throws InterruptedException { 
		Puerta.abierta = true; 
		Thread r = new Rapido();
		Thread l = new Lento(); 
		r.start(); l.start(); 
		r.join(); l.join(); 
		System.out.println("El rápido tiene: " + Puerta.rapido +" y el lento: " + Puerta.lento); 
		} 
	}

