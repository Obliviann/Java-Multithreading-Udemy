package aCreación;

class HelloThread implements Runnable {
	Thread t;
	//constructor
	HelloThread() {
		//crea un obj de la clase Thread, pasándole la referencia propia (this, parámetro implícito: la referencia a mi mismo)
		t = new Thread(this, "Nuevo Thread"); 
		System.out.println("Creado hilo: " + t); //nombre, id, 
		t.start(); // Arranca el nuevo hilo de ejecución. Ejecuta run(). Si no lo añado, no se ejec el sig código.
	}
	//obligatorio si implementas el Runnable
	public void run() {
		// Contiene el dódigo a ejecutar por el hilo:
		System.out.println("Hola desde el hilo creado! " + t);
		System.out.println("Hilo finalizando.");
	}
}

class RunThread {
	public static void main(String args[]) {
		new HelloThread(); // Crea un nuevo hilo de ejecución
		System.out.println("Hola desde el hilo principal!");
		for (int i=0; i<1000000;i++){
			//este for permite que los dos hilos se vayan ejec
			//Dependiendo del valir de nuestro y y l avelocidad de mi procesador, los hilos se colarán de diferentes formas
		}
		System.out.println("Proceso acabando.");
	}
}
