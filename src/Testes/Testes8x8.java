package Testes;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import Model.JogoIndivíduoNxN;

public class Testes8x8 {
	static Random random = new Random();
	static int conflito = 0;
	static ArrayList<Integer> media = new ArrayList<Integer>();
	public static Double soma = 0.0;
	static int tam = 10;

	public static void main(String[] args) {
		JogoIndivíduoNxN jogo;
		for (int i = 0; i < tam; i++) {
			do {
				jogo = new JogoIndivíduoNxN();
				conflito ++;
			} while (jogo.getConflitos() != 28);
			media.add(conflito);
			conflito = 0;
			System.out.println(jogo.toString());
			System.out.println(i);
		}
		//System.out.println(jogo.toString());
		
		
		for (int i = 0; i < media.size(); i++) {
			soma += media.get(i);
		}
		soma = soma / tam;
		System.out.println("\n"+soma);
		
	}

	private static void imprimirMatriz(int[][] matriz) {
		String x = "Indivíduo:\n\n";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				x += " " + matriz[i][j];
			}
			x += "\n";
		}
		System.out.println(x);

	}

	private static int verificaColisao(JogoIndivíduoNxN jogo) {
		verificaLinhaEColuna(jogo.getMatriz());
		verificaDiagonal(jogo.getMatriz());
		return conflito;
	}

	private static void verificaLinhaEColuna(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {

				if (matriz[i][j] == 1) {// Percorre as linhas
					for (int k = j + 1; k < matriz.length; k++) {
						if (matriz[i][k] == 1) {
							conflito++;
							System.out.println("L: " + i + " C: " + j + "| E: "
									+ matriz[i][j] + " |Linha|");
						}
					}
				}
				if (matriz[i][j] == 1) {// Percorre as Colunas
					for (int k = i + 1; k < matriz.length; k++) {
						if (matriz[k][j] == 1) {
							conflito++;
							System.out.println("L: " + i + " C: " + j + "| E: "
									+ matriz[i][j] + " |Colun|");
						}
					}
				}

			}
		}

	}

	private static void verificaDiagonalFull(int[][] matriz) {
		

	}

	private static int difenca(int m, int n) {
		if ((m - n) > 0) {
			return m - n;
		} else {
			return (m - n) * -1;
		}
	}

	private static void verificaDiagonal(int[][] matriz) {
		// Verifica conflitos no meio
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (j > 0 && j < (matriz.length - 1) && i < (matriz.length - 1)) {
					if (matriz[i][j] == 1 && matriz[i + 1][j - 1] == 1
							|| matriz[i][j] == 1 && matriz[i + 1][j + 1] == 1) {
						conflito++;
						System.out.println("L: " + i + " C: " + j + "| E: "
								+ matriz[i][j] + " |Meio|");
					}
				}
			}
		}

		// Diagonal Principal e Secundária
		int princ = -1;
		int secund = -1;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (i == j) {// Principal
					if (matriz[i][j] == 1) {
						princ++;
						if (princ > 0) {
							System.out.println("L: " + i + " C: " + j + "| E: "
									+ matriz[i][j] + " |Princ|");
						}
					}
				}
				if ((i + j) == (matriz.length - 1)) {// Secundária
					if (matriz[i][j] == 1) {
						secund++;
						if (secund > 0) {
							System.out.println("L: " + i + " C: " + j + "| E: "
									+ matriz[i][j] + " |Secun|");
						}
					}
				}
			}
		}
		if (princ > 0) {
			conflito += princ;
		}
		if (secund > 0) {
			conflito += secund;
		}

	}

	static int numeroRandomico() {
		return random.nextInt(8);
	}

	private static void imprimir(int[] vetorParametro) {
		String x = "Indivíduo:\n\n";
		for (int i = 0; i < vetorParametro.length; i++) {
			if ((i + 1) % 8 == 0) {
				x += " " + vetorParametro[i] + "\n";
			} else {
				x += " " + vetorParametro[i];
			}
		}
		System.out.println(x);
	}
}
