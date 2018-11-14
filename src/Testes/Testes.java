package Testes;

import java.util.Random;

import Model.JogoIndivíduo;

public class Testes {
	static Random random = new Random();
	static int vetor[] = new int[8];

	public static void main(String[] args) {
		JogoIndivíduo jogo = new JogoIndivíduo();
		
		for (int i = 0; i < 8; i++) {
			do {
				vetor[i] = numeroRandomico();
			} while (verificaRepeticao(i, vetor[i]));
			
		}
		jogo.colocaRainha(vetor);
		System.out.println(jogo.toString());
	}
	
	
	static boolean verificaRepeticao(int p, int n) {
		for (int i = 0; i < vetor.length; i++) {
			if (i != p) {
				if (vetor[i] == n) {
					return true;
				}
			}
		}
		return false;

	}
	static int numeroRandomico(){
		return random.nextInt(64);
	}

}
