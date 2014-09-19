package gare;

public class Quai {
	private int numero;
	private boolean libre;
	//private static int cpt = 0;
	//private int numTrainAQuai = 0;
	
	public Quai(int num){
		this.numero = num;
		//this.numero = ++cpt;
		this.libre = true;
	}
	
	public boolean estLibre(){
		return this.libre;
	}
	
	private void seLibere(){
		this.libre = true;
		//this.numTrainAQuai = 0;
	}
	
	private void trainArrive(){
		this.libre = false;
	}
	
	public void occuper(int tps){
		try {
				trainArrive();
				System.out.println("Un train est arrivé au quai");
				Thread.sleep(tps);
				seLibere();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Un train se casse du quai");
	}
	
	
}
