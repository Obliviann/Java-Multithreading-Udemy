package b22Gestión;

public class Dormilon extends Thread {
	int siesta;
	int intervalo;

	public Dormilon(int siesta) {
		this.siesta = siesta;
	}

	public void run() {
		try {
			System.out.println("El niño se echa a dormir");
			Thread.sleep(siesta);
		} catch (InterruptedException e) {
			System.out.println("Siesta interrumida por mamá");
			//return;							//rompe y termina la ejecución
			//if (!Thread.interrupted()) {		//3.provocar un interrupción hace que salte el catch
				//System.out.println("El niño se despierta solito");//NO VA BIEN
			//}
			System.exit(0);
		}
		System.out.println("El niño se despierta solito");
	}

	public static void main(String[] args) {
		int siesta = 600;				//1. se invierte el orden
		int despertador = 500;
		System.out.println("La mamá acuesta al niño");
		Thread nene = new Dormilon(siesta);
		nene.start();
		try {
			Thread.sleep(despertador);
			System.out.println("Todavía dormido: "+ nene.isAlive()); //4.
			//nene.interrupt();
			//System.out.println("Interruptido1: "+nene.isInterrupted());
			//System.out.println("Interruptido1: "+Thread.interrupted());	//estático, pertenece a la clase Thread, NO al objeto
																	//solo puede aplicarse al hilo en el que estoy, current thread
			nene.join();				//aseguramos que lo que viene después del join va a ser siempre post a que el hijo finalice
										//2. si lo quitamos, el "Fin de la Hª" sucede antes de que la "Siesta interruptida por mamá"
										//el hilo ppal termina antes del otro
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin de la historia");
	}
}
