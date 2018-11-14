package View;
import java.util.ArrayList;

import Model.JogoIndivíduoNxN;

public class HeapSort {
	public HeapSort() {
		super();
		// TODO Auto-generated constructor stub
	}

	static void sort(ArrayList<JogoIndivíduoNxN> lista) {
		int n = lista.size();
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(lista, n, i);
		for (int i = n - 1; i >= 0; i--) {
			JogoIndivíduoNxN temp = lista.get(0);
			lista.set(0, lista.get(i));
			lista.set(i, temp);
			heapify(lista, i, 0);
		}
	}

	static void heapify(ArrayList<JogoIndivíduoNxN> lista, int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		if (l < n && lista.get(l).getConflitos() > lista.get(largest).getConflitos())
			largest = l;
		if (r < n && lista.get(r).getConflitos() > lista.get(largest).getConflitos())
			largest = r;
		if (largest != i) {
			JogoIndivíduoNxN swap = lista.get(i);
			lista.set(i, lista.get(largest));
			lista.set(largest, swap);
			heapify(lista, n, largest);
		}
	}
}