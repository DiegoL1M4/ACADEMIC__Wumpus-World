import java.util.Random;

public class Buraco extends Posicao {
	
	Buraco(int i, int j) {
		super(i, j);
		
		Random gerador = new Random();
		
		while(i==3 && j==0) {
			i = gerador.nextInt(4);
			j = gerador.nextInt(4);
		}
		
		super.i = i;
		super.j = j;
	}
	
	public String toString() {
		return "B";
	}
}