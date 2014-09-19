package gare;

import java.io.IOException;
import java.util.HashMap;

import outilserveur.Serveur;
import train.InfoTrain;
import train.ServiceTrain;


public class AppliGare {
	public final static int NUM_PORT_TRAIN = 3000;
	//private final static String HOST = "localhost";
	
	
	public static void main(String[] args){
		
		Gare gare = new Gare(10);
		
		// Création du planning
		HashMap<Integer, InfoTrain> planning = new HashMap<Integer, InfoTrain>();
		planning.put(1, new InfoTrain(1, 17, 22));
		planning.put(2, new InfoTrain(2, 16, 23));
		planning.put(3, new InfoTrain(2, 17, 24));
		planning.put(4, new InfoTrain(3, 17, 10));
		planning.put(5, new InfoTrain(4, 17, 11));
		
		ServiceGare.setGare(gare);
		ServiceGare.setPlanning(planning);
		
		//new ServeurGare(gare, planning).lancer();	
		try {
			new Serveur(gare.ServiceGare.class, NUM_PORT_TRAIN).start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
}
