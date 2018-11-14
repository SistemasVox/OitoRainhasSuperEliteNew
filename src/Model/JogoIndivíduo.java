package Model;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class JogoIndivíduo {
	int vetor[] = new int[64];

	public JogoIndivíduo() {
		for (int i = 0; i < vetor.length; i++) {
			vetor[i] = 0;
		}
	}

	public void colocaRainha(int vetorRainhas[]) {
		for (int i = 0; i < vetorRainhas.length; i++) {
			vetor[vetorRainhas[i]] = vetorRainhas[i];
		}
	}

	public void colocaRainha(int i) {
		vetor[i] = 1;
	}

	@Override
	public String toString() {
		String x = "Indivíduo:\n\n";
		for (int i = 0; i < vetor.length; i++) {
			if ((i + 1) % 8 == 0) {
				x += " " + vetor[i] + "\n";
			} else {
				x += " " + vetor[i];
			}
		}
		return x;
	}

}
