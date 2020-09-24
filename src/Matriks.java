import java.util.*;
import java.io.*;

class Matriks{
	//Atribut
	float[][] muatriks;
	int brs;
	int kol;

	//Method

	void bacaMatriks() {
		this.muatriks = new float[this.brs][this.kol];
		Scanner input = new Scanner (System.in);
		int i,j;
		for (i=0; i<this.brs; i++){
			for (j=0; j<this.kol; j++){
				this.muatriks[i][j] = input.nextFloat();
			}
		}
	}

	void tulisMatriks() {
		int i,j;
		for (i=0; i<this.brs; i++){
			for (j=0; j<this.kol; j++){
				System.out.print(this.muatriks[i][j]+"\t");
			}
			System.out.println();
		}
	}

	// getlast baris kolom (Tested)
	int lastBaris(){
		return this.brs-1;
	}

	int lastKolom(){
		return this.kol-1;
	}

	// tukarbaris (Tested)
	void tukarBaris(int brs1, int brs2){
		float tmp;
		for(int j=0; j<=lastKolom(); j++){
			tmp = this.muatriks[brs1][j];
			this.muatriks[brs1][j] = this.muatriks[brs2][j];
			this.muatriks[brs2][j] = tmp; 
		} 
	}

	// urutmatriks biar gaus,determinan lancarr (Tested)
	void urutMatriks(){
		// urut per kolom
		int idxpertama = 0;
		int j = 0;
		while ((j<=lastKolom()) && (idxpertama != lastBaris())){
			for(int i=idxpertama; i<=lastBaris(); i++){
				for(int k=idxpertama; k<=lastBaris()-1; k++){
					if (this.muatriks[k+1][j] > this.muatriks[k][j]){
						tukarBaris(k, k+1);
					}
				}
			}
			// mencari kolom 0 pertama
			while ((idxpertama<=lastBaris()) && (this.muatriks[idxpertama][j] != 0)){
				idxpertama++;
			}
			j++;
		}
	}

	// Determinan dengan reduksi baris
	public float detRed(){
		float det;
		if (this.brs == 1){
			det = this.muatriks[0][0];
		}else if (this.brs == 2){
			det = (this.muatriks[0][0]*this.muatriks[1][1])-(this.muatriks[0][1]*this.muatriks[1][0]);
		}else{
			det = 1;
			// Di urut terlebihdahulu
			int idxpertama = 0;
			int j = 0;
			while ((j<=lastKolom()) && (idxpertama != lastBaris())){
				for(int i=idxpertama; i<=lastBaris(); i++){
					for(int k=idxpertama; k<=lastBaris()-1; k++){
						if (this.muatriks[k+1][j] > this.muatriks[k][j]){
							tukarBaris(k, k+1);
							det *= -1;
						}
					}
				}
				// mencari kolom 0 pertama
				while ((idxpertama<=lastBaris()) && (this.muatriks[idxpertama][j] != 0)){
					idxpertama++;
				}
				j++;
			}
			// Reduksi baris
			for (int klm = 0; klm<=lastKolom(); klm++){
				for (int i = klm+1; i<=lastBaris(); i++){
					if (this.muatriks[i][klm] != 0){
						float rate = this.muatriks[i][klm]/this.muatriks[klm][klm];
						for (int k = klm; k<=lastKolom(); k++){
							this.muatriks[i][k] -= rate*this.muatriks[klm][k];
						}
					}
				}
			}
			// Cari determinan dengan perkalian diagonal utamanya
			for (int i = 0; i<=lastBaris(); i++){
				det *= this.muatriks[i][i];
			}
		}
		return det;
	}

	void gauss(){
		int i,j,k,l;
		float faktor;
		boolean rowDone=false;
		for(i=0; i<this.brs; i++){
			for(j=0; j<this.kol; j++){
				if (this.muatriks[i][j]!=0 && !(rowDone)){
					faktor = this.muatriks[i][j];
					for(k=j; k<this.kol; k++){
						this.muatriks[i][k] = this.muatriks[i][k]/faktor;
					}
					for(k=i+1; k<this.brs; k++){
						faktor = this.muatriks[k][j];
						for(l=j; l<this.kol; l++){
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








