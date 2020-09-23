import java.util.*;
import java.io.*;

class Matriks{
	//Atribut
	float[][] muatriks;

	//Method

	void bacaMatriks(int baris, int kolom) {
		this.muatriks = new float[baris][kolom];
		Scanner input = new Scanner (System.in);
		int i,j;
		for (i=0; i<baris; i++){
			for (j=0; j<kolom; j++){
				this.muatriks[i][j] = input.nextFloat();
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
	void gauss(int baris, int kolom){
		int i,j,k,l;
		float faktor;
		boolean rowDone=false;
		for(i=0; i<baris; i++){
			for(j=0; j<kolom; j++){
				if (this.muatriks[i][j]!=0 && !(rowDone)){
					faktor = this.muatriks[i][j];
					for(k=j; k<kolom; k++){
						this.muatriks[i][k] = this.muatriks[i][k]/faktor;
					}
					for(k=i+1; k<baris; k++){
						faktor = this.muatriks[k][j];
						for(l=j; l<kolom; l++){
							this.muatriks[k][l] -= (faktor*this.muatriks[i][l]);
						}	
					}
					rowDone = true;
				}
			}
			rowDone = false;
		}
	}
}








