import java.util.Random;

public class Caverna {
	
	Jogador jogador;
	Ouro ouro;
	Wumpus wumpus;
	
	Posicao buraco1;
	Posicao buraco2;
	Posicao buraco3;
	
	Posicao[][] caverna = new Posicao[4][4];
	
	Caverna() {
		
		Random gerador = new Random();
		
		jogador = new Jogador(3,0);
		caverna[jogador.i][jogador.j] = jogador;
		
		ouro = new Ouro(gerador.nextInt(4),gerador.nextInt(4));
		caverna[ouro.i][ouro.j] = ouro;
		
		wumpus = new Wumpus(gerador.nextInt(4),gerador.nextInt(4));
		caverna[wumpus.i][wumpus.j] = wumpus;
		
		buraco1 = new Buraco(gerador.nextInt(4),gerador.nextInt(4));
		caverna[buraco1.i][buraco1.j] = buraco1;
		buraco2 = new Buraco(gerador.nextInt(4),gerador.nextInt(4));
		caverna[buraco2.i][buraco2.j] = buraco2;
		buraco3 = new Buraco(gerador.nextInt(4),gerador.nextInt(4));
		caverna[buraco3.i][buraco3.j] = buraco3;
		
		
		
	}
	
	public void mostrarCaverna() {
		System.out.println("MUNDO DE WUMPUS:");
		for (int i = 0; i < caverna.length; i++) {
			for (int j = 0; j < caverna.length; j++) {
				if(caverna[i][j] == null)
					System.out.printf("\t" + "-");
				else
					System.out.printf("\t" + caverna[i][j]);
			}
			System.out.println();
		}
	}
	
	public void movimentar(int D) {
		// Atirar Flecha
		boolean acerto = false;
		boolean tiro = false;
		if(D == 58 && jogador.flecha == true) {
			tiro = true;
			jogador.atirar();
			if(wumpus.i == jogador.i-1 && wumpus.j == jogador.j && wumpus.vivo == true)
				acerto = true;
		} else if(D == 52 && jogador.flecha == true) {
			tiro = true;
			jogador.atirar();
			if(wumpus.i == jogador.i+1 && wumpus.j == jogador.j && wumpus.vivo == true)
				acerto = true;
		} else if(D == 54 && jogador.flecha == true) {
			tiro = true;
			jogador.atirar();
			if(wumpus.i == jogador.i && wumpus.j == jogador.j-1 && wumpus.vivo == true)
				acerto = true;
		} else if(D == 56 && jogador.flecha == true) {
			tiro = true;
			jogador.atirar();
			if(wumpus.i == jogador.i && wumpus.j == jogador.j+1 && wumpus.vivo == true)
				acerto = true;
		}
		// Movimentacao
		boolean impacto = false;
		if(D == 8) {
			if(jogador.i-1 == -1)
				impacto = true;
			else 
				mudarPosicao(jogador.i-1, jogador.j);
		} else if(D == 2) {
			if(jogador.i+1 == 4)
				impacto = true;
			else 
				mudarPosicao(jogador.i+1, jogador.j);
		} else if(D == 4) {
			if(jogador.j-1 == -1)
				impacto = true;
			else 
				mudarPosicao(jogador.i, jogador.j-1);
		} else if(D == 6) {
			if(jogador.j+1 == 4)
				impacto = true;
			else 
				mudarPosicao(jogador.i, jogador.j+1);
		}
		
		mostrarCaverna();
		verificacao(jogador.i, jogador.j);
		if(impacto)
			System.out.println(" * Impacto com a parede!");
		if(acerto)
			wumpus.morreu();
		if(tiro && !acerto)
			System.out.println(" * Errou o disparo!");
		System.out.println();
		// Verificacao de vitoria
		if(jogador.i == 3 && jogador.j == 0 && ouro.pego == true)
			fimJogo();
		 
	}
	
	public void mudarPosicao(int i, int j) {
		caverna[jogador.i][jogador.j] = null;
		caverna[i][j] = jogador;
		jogador.i = i;
		jogador.j = j;
	}
	
	public void verificacao(int i, int j) {
		System.out.println("Status do local:");
		// Verificar Norte
		if(i>0)
			opcoesStatus(i-1, j);
		// Verificar Sul
		if(i<caverna.length-1)
			opcoesStatus(i+1, j);	
		// Verificar Oeste
		if(j>0)
			opcoesStatus(i, j-1);
		// Verificar Leste
		if(j<caverna.length-1)
			opcoesStatus(i, j+1);
		
		if(i==ouro.i && j==ouro.j && ouro.pego == false) {
			System.out.println(" * Ouro reluzindo");
			ouro.pegou();
		} else if(i==wumpus.i && j==wumpus.j && wumpus.vivo == true) {
			System.out.println(" * Wumpus matou você!");
			fimJogo();
		} else if((i==buraco1.i && j==buraco1.j) || 
				  (i==buraco2.i && j==buraco2.j) ||
				  (i==buraco3.i && j==buraco3.j) ){
			System.out.println(" * Você caiu no buraco!");
			fimJogo();
		}

	}
	
	public void opcoesStatus(int i, int j) {
		if(wumpus.i == i && wumpus.j == j && wumpus.vivo == true)
			System.out.println(" * Cheiro de Wumpus");
		
		if((buraco1.i == i && buraco1.j == j) ||
		   (buraco2.i == i && buraco2.j == j) ||
		   (buraco3.i == i && buraco3.j == j))
			System.out.println(" * Brisa");
	}
	
	public void fimJogo() {
		System.out.println(" --- Jogo Encerrado! --- ");
		if(ouro.pego == true)
			System.out.println("Parabens, voce conseguiu!");
		System.exit(0);
	}
	
}
