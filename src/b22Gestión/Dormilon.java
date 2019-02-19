package b22Gesti�n;

public class Dormilon extends Thread {
	int siesta;
	int intervalo;

	public Dormilon(int siesta) {
		this.siesta = siesta;
	}

	public void run() {
		try {
			System.out.println("El ni�o se echa a dormir");
			Thread.sleep(siesta);
		} catch (InterruptedException e) {
			System.out.println("Siesta interrumida por mam�");
			//return;							//rompe y termina la ejecuci�n
			//if (!Thread.interrupted()) {		//3.provocar un interrupci�n hace que salte el catch
				//System.out.println("El ni�o se despierta solito");//NO VA BIEN
			//}
			System.exit(0);
		}
		System.out.println("El ni�o se despierta solito");
	}

	public static void main(String[] args) {
		int siesta = 600;				//1. se invierte el orden
		int despertador = 500;
		System.out.println("La mam� acuesta al ni�o");
		Thread nene = new Dormilon(siesta);
		nene.start();
		try {
			Thread.sleep(despertador);
			System.out.println("Todav�a dormido: "+ nene.isAlive()); //4.
			//nene.interrupt();
			//System.out.println("Interruptido1: "+nene.isInterrupted());
			//System.out.println("Interruptido1: "+Thread.interrupted());	//est�tico, pertenece a la clase Thread, NO al objeto
																	//solo puede aplicarse al hilo en el que estoy, current thread
			nene.join();				//aseguramos que lo que viene despu�s del join va a ser siempre post a que el hijo finalice
										//2. si lo quitamos, el "Fin de la H�" sucede antes de que la "Siesta interruptida por mam�"
										//el hilo ppal termina antes del otro
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin de la historia");
	}
}
