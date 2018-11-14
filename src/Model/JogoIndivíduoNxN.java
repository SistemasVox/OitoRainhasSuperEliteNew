package Model;

import java.util.Random;

public class JogoIndivíduoNxN {
	Random random = new Random();
	private int n = 8;
	int matriz[][] = new int[n][n];
	int conflitos = 0;
	int linhas = 0;
	int colunas = 0;
	int diagonalD = 0;
	int diagonalE = 0;
/*
	public JogoIndivíduoNxN() {
		colocaRainha(geraMatriz(), geraMatriz());
		verificaConflitos();
		somaConflitos();
	}
	*/
	public JogoIndivíduoNxN() {
		geraMatrizcomC();
		verificaConflitos();
		somaConflitos();
	}

	public JogoIndivíduoNxN(JogoIndivíduoNxN pai, int[] genetico, int n) {
		int tempmatriz[][] = pai.getMatriz();
		for (int i = 0; i < pai.geraMatriz().length; i++) {
			for (int j = 0; j < pai.geraMatriz().length; j++) {
				if (i == n) {
					matriz[i][j] = genetico[j];
				} else {
					matriz[i][j] = tempmatriz[i][j];
				}
			}
		}
	}

	private void somaConflitos() {
		this.conflitos = this.linhas + this.colunas + this.diagonalD + this.diagonalE;
	}
	public int lc() {
		return this.linhas + this.colunas;
	}

	private void verificaConflitos() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] == 1) {
					diagonalDireita(i, j, matriz);
					diagonalEsquerda(i, j, matriz);
					//c_linha(i, j, matriz);
					c_Colunas(i, j, matriz);
				}
			}
		}
	}

	private void c_Colunas(int i, int j, int[][] pMatrix) {
		for (int k = i + 1; k < pMatrix.length; k++) {
			if (pMatrix[k][j] == 1) {
				this.colunas++;
			}
		}
	}

	@SuppressWarnings("unused")
	private void c_linha(int i, int j, int[][] pMatrix) {
		for (int k = j + 1; k < pMatrix.length; k++) {
			if (pMatrix[i][k] == 1) {
				this.linhas++;
			}
		}
	}

	private void diagonalEsquerda(int i, int j, int[][] pMatrix) {
		int aux;
		if (j >= ((pMatrix.length - 1) - i)) {
			aux = (pMatrix.length - i);
		} else {
			aux = (j + 1);
		}

		for (int k = 1; k < aux; k++) {
			if (pMatrix[i + k][j - k] == 1) {
				this.diagonalE++;
			}
		}
	}

	private void diagonalDireita(int i, int j, int[][] pMatrix) {
		int aux;
		if (i >= j) {
			aux = (matriz.length - i);
		} else {
			aux = (matriz.length - j);
		}
		for (int k = 1; k < aux; k++) {
			if (matriz[i + k][j + k] == 1) {
				this.diagonalD++;
			}
		}
	}
	
	public void atualizaConflito() {
		this.linhas = 0;
		this.colunas = 0;
		this.conflitos = 0;
		this.diagonalD = 0;
		this.diagonalE = 0;
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz.length; j++) {
				if (this.matriz[i][j] == 1) {
					c_Colunas(i, j, this.matriz);
					//c_linha(i, j, this.matriz);
					diagonalDireita(i, j, this.matriz);
					diagonalEsquerda(i, j, this.matriz);
				}
			}
		}
		this.somaConflitos();
	}

	private int numeroRandomico() {
		return random.nextInt(n);
	}

	private void colocaRainha(int matrizRainhas[], int matrizRainhas2[]) {
		for (int i = 0; i < matrizRainhas2.length; i++) {
			matriz[matrizRainhas[i]][matrizRainhas2[i]] = 1;
		}
	}

	private int[] geraMatriz() {
		int matriz[] = new int[n];
		for (int i = 0; i < matriz.length; i++) {
			matriz[i] = -1;
		}
		for (int i = 0; i < matriz.length; i++) {
			do {
				matriz[i] = numeroRandomico();
			} while (verificaRepeticao(i, matriz[i], matriz));

		}
		return matriz;
	}
	
	private void geraMatrizcomC() {
		for (int i = 0; i < matriz.length; i++) {
			int um = numeroRandomico();
			for (int j = 0; j < matriz.length; j++) {
				if (j == um) {
					matriz[i][j] = 1;
				}else{
					matriz[i][j] = 0;
				}
			}
		}
		
	}

	private boolean verificaRepeticao(int p, int n, int matriz[]) {
		for (int i = 0; i < matriz.length; i++) {
			if (i != p) {
				if (matriz[i] == n) {
					return true;
				}
			}
		}
		return false;

	}

	public int[][] getMatriz() {
		return matriz;
	}

	public int getNum(int i, int j) {
		return matriz[i][j];
	}
	public void setNum(int i, int j, int n) {
		matriz[i][j] = n;
	}

	public int getConflitos() {
		return conflitos;
	}

	@Override
	public String toString() {
		String x = "Indivíduo:\n\n";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				x += " " + matriz[i][j];
			}
			x += "\n";
		}
		x += "\nConflitos: " + conflitos;
		x += "\n\nLinhas: " + linhas;
		x += "\nColunas: " + colunas;
		x += "\nDiagonal E: " + diagonalE;
		x += "\nDiagonal D: " + diagonalD;
		return x;
	}

	public String toString(int n) {
		String x = "\n\nIndivíduo: " + (n + 1) + "\n\n";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				x += " " + matriz[i][j];
			}
			x += "\n";
		}
		x += "\nConflitos: " + conflitos;
		x += "\n\nLinhas: " + linhas;
		x += "\nColunas: " + colunas;
		x += "\nDiagonal E: " + diagonalE;
		x += "\nDiagonal D: " + diagonalD;
		return x;
	}

}
