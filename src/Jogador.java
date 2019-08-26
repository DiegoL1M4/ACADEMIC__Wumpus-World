
public class Jogador extends Posicao {
	
	boolean flecha = true;
	
	Jogador(int i, int j) {
		super(i, j);
	}
	
	public void atirar() {
		flecha = false;
	}
	
	public String toString() {
		return "J";
	}
}