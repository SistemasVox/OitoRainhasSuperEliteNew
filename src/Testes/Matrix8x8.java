package Testes;

public class Matrix8x8 {

	public static void main(String[] args) {
		int vetor[][] = new int[8][8];

		for (int i = 0; i < vetor.length; i++) {
			for (int j = 0; j < vetor.length; j++) {
				System.out.print(i + "." + j);
				System.out.print(" ");
			}
			System.out.println("");
		}

	}

}
