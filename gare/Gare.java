package gare;

import java.util.ArrayList;
import java.util.List;

public class Gare {
	private List<Quai> quais;
	private int nbQuais;
	
	public Gare(int nq){
		nbQuais = nq;
		quais = new ArrayList<Quai>();
		for(int i = 0; i < nbQuais; ++i){
			quais.add(new Quai(i));
		}
	}
	
	
	
	public Quai getQuai(int numQuai){
		return quais.get(numQuai);
	}		
}
