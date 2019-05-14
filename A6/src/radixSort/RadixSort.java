package radixSort;

import java.util.Arrays;

import fasterQuicksort.Node;

/**
 * <br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 5 RadixSort Algorithm<br>
 * A RadixSort Algorithm<br>
 * 
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 1.0, 14.05.19
 *
 */

/*Copyright (C) 2013 Yeison Rodriguez ( github.com/yeison )
This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

public class RadixSort {
	private long Counter;

	public RadixSort() {
		Counter = 0;
	}

	/**
	 * A method that sorts a part of a list
	 * 
	 * @param list
	 *            The list that is going to be sorted by the Nodes keys
	 */
	public Node[] sort(Node[] list, int m) {
		Node[] t = radixsort(list, m);
		return t;
	}

	private Node[] countSort(Node[] list, int exp) {
		Node[] out = new Node[list.length];

		int[] count = new int[10];

		for (int i = 0; i < list.length; i++) {
			int digit = getDigit(list[i].getKey(), exp);
			count[digit] += 1;
		}

		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}

		for (int i = list.length - 1; i >= 0; i--) {
			Counter++;
			int digit = getDigit(list[i].getKey(), exp);

			out[count[digit] - 1] = list[i];
			count[digit]--;
		}

		return out;
	}

	// The main function to that sorts arr[] of size n using
	// Radix Sort
	private Node[] radixsort(Node[] list, int m) {
		// Do counting sort for every digit. Note that instead
		// of passing digit number, exp is passed. exp is 10^i
		// where i is current digit number
		for (int exp = 1; m / exp > 0; exp *= 10)
			list = countSort(list, exp);

		return list;
	}

	private static int getDigit(int value, int digitPlace) {
		return ((value / digitPlace) % 10);
	}

	public long getCounter() {
		return Counter;
	}

	public void resetCounter() {
		Counter = 0;
	}
}
