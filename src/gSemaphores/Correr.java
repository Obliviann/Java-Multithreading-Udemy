package gSemaphores;
import java.util.concurrent.Semaphore;

class Contador {
	public static int c;
}

class Suma extends Thread {
	Semaphore SemaforoSuma;

	public Suma(Semaphore SemaforoSuma) {
		this.SemaforoSuma = SemaforoSuma;
	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			try {
				SemaforoSuma.acquire();			//53:00		/HECHO EL CERROJO
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Contador.c++;									//HAGO LO QUE TENGO QUE HACER
			SemaforoSuma.release();		//el hilo que termina de usar el recurso avisa de su liberación a UN hilo/(proceso?)
		}													//CIERRO EL CERROJO
	}														//DE ESTA FORMA SIEMPRE DARÁ CERO
}

class Resta extends Thread {
	Semaphore SemaforoResta;

	public Resta(Semaphore SemaforoResta) {
		this.SemaforoResta = SemaforoResta;
	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			try {
				SemaforoResta.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Contador.c--;
			SemaforoResta.release();
		}
	}
}

public class Correr {
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaforo = new Semaphore(1);
		Contador.c = 0;
		Thread s1 = new Suma(semaforo);
		Thread r1 = new Resta(semaforo);
		s1.start();
		r1.start();
		s1.join();
		r1.join();
		System.out.println("El resultado final es: " + Contador.c);
	}
}
