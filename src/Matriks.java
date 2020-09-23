import java.util.*;
import java.io.*;

class Matriks{
	//Atribut
	int[][] muatriks;

	//Method

	void bacaMatriks(int baris, int kolom) {
		this.muatriks = new int[baris][kolom];
		Scanner input = new Scanner (System.in);
		int i,j;
		for (i=0; i<baris; i++){
			for (j=0; j<kolom; j++){
				this.muatriks[i][j] = input.nextInt();
			}
		}
	}

	void tulisMatriks(int baris, int kolom) {
		int i,j;
		for (i=0; i<baris; i++){
			for (j=0; j < kolom; j++){
				System.out.print(this.muatriks[i][j]+"\t");
			}
			System.out.println();
		}
	}
}