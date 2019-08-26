import java.util.Random;

public class Wumpus extends Posicao {
	
	boolean vivo = true;
	
	Wumpus(int i, int j) {
		super(i, j);
		
		Random gerador = new Random();
		
		while(i==3 && j==0) {
			i = gerador.nextInt(4);
			j = gerador.nextInt(4);
		}
		
		super.i = i;
		super.j = j;
	}
	
	public void morreu() {
		vivo = false;
		System.out.println(" * Grito do Wumpus");
	}
	
	public String toString() {
		return "W";
	}
}
