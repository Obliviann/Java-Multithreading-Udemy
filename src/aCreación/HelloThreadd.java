package aCreaci�n;

class HelloThreadd extends Thread {
	public void run() {
		// C�digo a ejecutar por el hilo
		System.out.println("Hola desde el hilo creado!");
	}
}

 class RunThreadd{

	public static void main(String args[]) {
		HelloThreadd hilo = new HelloThreadd(); 	//Crea
		hilo.start();								//y arranca un nuevo hilo de ejecuci�n
		System.out.println("Hola desde el hilo principal!");
		System.out.println("Proceso acabando.");
	}
}
