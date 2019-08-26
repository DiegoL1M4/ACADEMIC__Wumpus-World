import java.util.Scanner;

public class Jogo {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		Caverna caverna = new Caverna();
		
		caverna.mostrarCaverna();
		caverna.verificacao(3, 0);
		
		while(true) {
			int D = in.nextInt();

			caverna.movimentar(D);
			
			if(D == 5)
				break;
		}
		
		System.out.println(" --- Jogo Encerrado! --- ");
		in.close();

	}

}
