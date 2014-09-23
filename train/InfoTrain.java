package train;

import java.util.Calendar;
import java.util.GregorianCalendar;

//import gare.*;
public class InfoTrain {
	private int quai;
	private GregorianCalendar heure;
	private boolean passé;
	private int tempsStationnement;
	private static final int TEMPS_STANDARD = 5000;
	
	@SuppressWarnings("deprecation")
	public InfoTrain(int q, int h, int m){
		quai = q;
		this.passé = false;
		heure = new GregorianCalendar(0,0,0,h,m);
		tempsStationnement = TEMPS_STANDARD;
	}
	
	@SuppressWarnings("deprecation")
	public InfoTrain(int q, int h, int m, int tps){
		quai = q;
		this.passé = false;
		heure = new GregorianCalendar(0,0,0,h,m);
		tempsStationnement = tps;
	}

	public GregorianCalendar getHeurePrevue() {
		// TODO Auto-generated method stub
		return heure;
	}

	public int getNumQuai() {
		// TODO Auto-generated method stub
		return quai;
	}

	public int getTemps() {
		// TODO Auto-generated method stub
		return tempsStationnement;
	}
	
}

