package train;

public class ServiceTrain implements Runnable{
	private Thread thread;
	private int numero;

	@Override
	public void run() {
		
	}
	
	public void lancer(){
		this.thread = new Thread(this);
		thread.start();
	}
	
	public void attendre(){
		try {
			thread.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
