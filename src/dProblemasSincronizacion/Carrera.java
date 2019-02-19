package dProblemasSincronizacion;

class Contador {
	public static int c;
}

class Suma extends Thread {
	public void run() {
		for (int i = 0; i < 1000; i++)
			Contador.c++;
	}
}

class Resta extends Thread {
	public void run() {
		for (int i = 0; i < 1000; i++)
			Contador.c--;
	}
}

public class Carrera {
	public static void main(String[] args) throws InterruptedException {
		Contador.c = 0;
		Thread s1 = new Suma();
		Thread r1 = new Resta();
		s1.start();
		r1.start();
		s1.join();									//no entiendo la necesidad de los joins
		r1.join();
		System.out.println("El resultado final de la carrera es: " + Contador.c);
	}
}
