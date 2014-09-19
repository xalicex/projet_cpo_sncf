package train;

// COUCOU NOEMIE 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class AppliTrain { //trainarrive
	private final static int NUM_PORT_TRAIN = 3000;
	
	public static void main(String[] args){
		
		Socket s = null;		
//		try {
			// Cree une socket pour communiquer avec le service se trouvant sur la
			// machine host au port PORT
			try {
;
				s = new Socket("172.30.17.154", NUM_PORT_TRAIN);
				
				BufferedReader sin = new BufferedReader (new InputStreamReader(s.getInputStream ( )));
				PrintWriter sout = new PrintWriter (s.getOutputStream ( ), true);
				BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Connect� au serveur : " + s.getInetAddress() + " Port : " + s.getPort());
				
				String line;
				while(true) {
					System.out.print("> "); System.out.flush();
					line = clavier.readLine();
					// envoie au serveur
					sout.println(line);

					// lit la r�ponse provenant du serveur
					line = sin.readLine();
					
					// Verifie si la connection est fermee.
					// Si oui on sort de la boucle
					if (line == null) { 
						System.out.println("Connection fermee par le serveur."); 
						break;
					}
					// Ecrit la ligne envoyee par le serveur
					System.out.println(line);
				}
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	

}
