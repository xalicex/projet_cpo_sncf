package gare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import outilserveur.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import train.InfoTrain;
import train.ServiceTrain;
import outilserveur.Service;
public class ServiceGare extends Service{ // La Gare et le reste
	private static Gare gare;
	private static HashMap<Integer, InfoTrain> planning;
	
	
	
	public static void setGare(Gare g){
		gare = g;
	}
	
	public static void setPlanning(HashMap<Integer, InfoTrain> p){
		planning = p;
	}
	
	
	@Override
	public void run() {
		System.out.println("Un train arrive en gare");
		try {
			System.out.println(this.getSocket().getInetAddress());		
			BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
			
			while(true){
				//attendre un train
				String line = in.readLine();
			
			
				System.out.println("J'ai re�u " + line);
					if(Integer.parseInt(line) > 0 && Integer.parseInt(line) < 99999){
						//numberformatexception
						System.out.println("ACCEPTE");

						// Si cette ligne est vide, le serveur se termine
						//if (line == null) break;
						//if (line.equals("")) break;
						//out.println("J'ai bien re�u " + line);
						int numTrain = Integer.parseInt(line);
						
						if(trainDansPlanning(numTrain)){
							System.out.println("Client accept�");
							out.println("Oui le train est dans le planning");
							GregorianCalendar temps = new GregorianCalendar();
							int numQuai = getNumQuai(numTrain);
							// A FAIRE RENVOYER NUM QUAI
							Date test = new Date();
							//On regarde si c'est il est � la bonne heure
							System.out.println("TEMPS : " + test);
							System.out.println("TRAIN : " + getHeureTrain(numTrain).toString());
							int difference = (int) (temps.getTimeInMillis() - getHeureTrain(numTrain).getTimeInMillis());
							// POSITIF : train en avance
							System.out.println("Difference : " + difference);
							//if(difference < 0){
							if(getHeureTrain(numTrain).before(temps)){
								System.out.println(" --> TRAIN EN AVANCE");
								try {
									Thread.sleep(difference);
									//Thread.sleep(3000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								System.out.println(" --> LE TRAIN EST A L HEURE");
								//Regarder si quai est occup�!!!
								
								synchronized(gare.getQuai(numQuai)){
									this.trainEntreEnQuai(numTrain, numQuai);
								}
								
								//DIRE AU TRAIN D'ENTRER EN QUAI
								
							}else{
								System.out.println("TROP TARD!!!!");
							}
						}else{
							System.out.println("Client rejet�");
							out.println("NON casse-toi!");
						}
					}else{
						System.out.println("REJETTE");
						out.println("Num�ro de train non valide!");
					}
			}
		} catch (IOException e) {
			System.err.println("Erreur dans le dialogue des sockets : " + e);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("TRAIN REFUSE : Donn�e incorrecte - Num�ro de train NON NUMERIQUE : " + e);
		}
		
		//finally { try {this.getSocket().close();} catch (IOException e2) {} }
		System.out.println("FIN");
		try {
			finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean trainDansPlanning(int numTrain){
		return planning.containsKey(numTrain);
	}
	
	private GregorianCalendar getHeureTrain(int numTrain){
		return planning.get(numTrain).getHeurePrevue();
	}
	
	private int getNumQuai(int numTrain){
		return planning.get(numTrain).getNumQuai();
	}
	
	public void faireAttendreTrain(ServiceTrain train){
		train.attendre();
	}
	private synchronized void trainEntreEnQuai(int numTrain, int numQuai){
		gare.getQuai(numQuai).occuper(planning.get(numTrain).getTemps());
	}
	
	// un train arrive, il envoie son num�ro
	// "Salut je suis le train 21 et j'arrive au quai n�3!
}
