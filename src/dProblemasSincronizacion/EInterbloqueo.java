package dProblemasSincronizacion;

//el interbloqueo no es real, 
//puede llegarse al extremo de que nunca suceda esto (si ninguno ha entrado, te da resultado final: 0):
//el extremo de la ausencia de sincronismo
//hay ocasiones en las que ninguno de los dos consiguen hacer las cosas

class PuertaE {
	public static boolean CerrojoA;
	public static boolean CerrojoB;
	public static int contador;
}
//cada thread es responsable de una llave

class LlaveA extends Thread {
	public void run() {
		for (int i = 0; i < 1000; i++) {
			PuertaE.CerrojoA = true;
			if (PuertaE.CerrojoB)
				PuertaE.contador++;
			PuertaE.CerrojoA = false;
		}
		System.out.println("LlaveA terminando");
	}
}

class LlaveB extends Thread {
	public void run() {
		for (int i = 0; i < 1000; i++) {
			PuertaE.CerrojoB = true;
			if (PuertaE.CerrojoA)
				PuertaE.contador++;
			PuertaE.CerrojoB = false;
		}
		System.out.println("LlaveB terminando");
	}
}

public class EInterbloqueo {
	public static void main(String[] args) throws InterruptedException {
		Thread a = new LlaveA();
		Thread b = new LlaveB();
		a.start();
		b.start();
		a.join();
		b.join();
		System.out.println("El resultado final es: " + PuertaE.contador);
	}
}
