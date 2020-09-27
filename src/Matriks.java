import java.util.*;
import java.io.*;

class Matriks{
	//Atribut
	double[][] muatriks;
	int brs;
	int kol;

	//Method

	void bacaMatriks() {
		this.muatriks = new double[this.brs][this.kol];
		Scanner input = new Scanner (System.in);
		int i,j;
		for (i=0; i<this.brs; i++){
			for (j=0; j<this.kol; j++){
				this.muatriks[i][j] = input.nextDouble();
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
		double tmp;
		for(int j=0; j<=lastKolom(); j++){
			tmp = this.muatriks[brs1][j];
			this.muatriks[brs1][j] = this.muatriks[brs2][j];
			this.muatriks[brs2][j] = tmp; 
		}
	}

	// tukarkolom (Tested)
	void tukarKolom(int kol1, int kol2){
		double tmp;
		for(int i=0; i<=lastBaris(); i++){
			tmp = this.muatriks[i][kol1];
			this.muatriks[i][kol1] = this.muatriks[i][kol2];
			this.muatriks[i][kol2] = tmp; 
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

	// copymatriks (Tested) cara pakai -> double[][] variable = copyMatriks()
	public double[][] copyMatriks(){
		double[][] copy = new double[this.brs][this.kol];
		for (int i=0; i<=lastBaris(); i++){
			for (int j=0; j<=lastKolom(); j++){
				copy[i][j] = this.muatriks[i][j];
			}
		}
		return copy;
	}

	// Transpose (Tested)
	void Transpose(){
		if (isPersegi()){
			double[][] tmpMatriks = copyMatriks();
			for (int i=0; i<=lastBaris(); i++){
				for (int j=0; j<=lastKolom(); j++){
					this.muatriks[i][j] = tmpMatriks[j][i];
				}
			}
		}
	}

	// Determinan dengan reduksi baris
	public double detRed(){
		double det;
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
					double rate = this.muatriks[i][klm]/this.muatriks[klm][klm];
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
		return det;
	}

	public boolean isBarisNol(int bar) {
		int j=0;
		boolean nol=true;
		while(j<this.kol && nol){
			if(this.muatriks[bar][j] != 0) nol=false;
			j++;
		}
		return nol;
	}

	void gauss(){
		int i,j,k,l;
		double faktor;
		boolean rowDone=false, found=false;
		for(i=0; i<this.brs; i++){
			for(j=0; j<this.kol; j++){
				if (i==j && this.muatriks[i][j] == 0){
					k=i+1;
					while(k<this.brs && !found){
						if(this.muatriks[k][j] != 0){
							tukarBaris(i,k);
							found = true;
						}
						k++;
					}
					found = false;
				}
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
		for(i=0; i<this.brs-1; i++){
			if(isBarisNol(i)){
				if(!isBarisNol(i+1)){
					tukarBaris(i, i+1);
				}
			}
		}
	}

	void gaussJordan(){
		//kalo mau manggil ini, panggil method gauss dulu
		int i,j,k,l;
		double faktor;
		boolean rowDone=false;
		for(i=this.brs-1; i>=0; i--){
			for(j=0; j<this.kol; j++){
				if(this.muatriks[i][j]==1 && !rowDone){
					for(k=i-1; k>=0; k--){
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

	public boolean isPersegi() {
		return(this.brs == this.kol);
	}
	
	void cramer(){
		if(this.brs == this.kol -1) {
			int i,j,k,l;
			double hasil,detAwal;
			
			//membuat matriks tanpa kolom terakhir
			Matriks M1 = new Matriks();
			M1.muatriks = new double[this.brs][this.kol -1];
			M1.brs = this.brs;
			M1.kol = this.kol -1;
			
			//membuat matriks dummy
			Matriks M2 = new Matriks();
			M2.muatriks = new double[this.brs][this.kol-1];
			M2.brs = this.brs;
			M2.kol = this.kol -1;
			
			//mengisi setiap elemen M1 dengan elemen M tanpa kolom terakhir
			for (i=0; i<=M1.lastBaris(); i++){
				for (j=0; j<=M1.lastKolom(); j++){
					M1.muatriks[i][j] = this.muatriks[i][j];
				}
			}
			//determinan awal
			detAwal = M1.detRed();
			
			for(j=0;j<=M1.lastKolom();j++) {
				
				//membuat salinan matriks M1
				for (k=0; k<=M1.lastBaris(); k++){
					for (l=0; l<=M1.lastKolom(); l++){
						M2.muatriks[k][l] = M1.muatriks[k][l];
					}
				}
				
				//substitusi kolom terakhir kepada seiap kolom M2
				for(i=0;i<=M1.lastBaris();i++) {
					M2.muatriks[i][j] = this.muatriks[i][this.lastKolom()];
				}
				
				//determinan setelah substitusi
				hasil = M2.detRed();
				//setiap variabel dinamai huruf "x" diikuti angka urutan variabel tersebut
				System.out.println("x" + (j+1) + " = " + (hasil / detAwal));
			}
		}
	}

	void interpolasi(){
		int i,j;
		Scanner input = new Scanner (System.in);
		System.out.print("Masukkan jumlah n: ");
		int n = input.nextInt();
		double[][] masukan = new double[n][2];
		double[][] polinom = new double[n][n+1];
		//masukan (x,y)
		for (i=0; i<n; i++){
			for (j=0; j<2; j++){
				masukan[i][j] = input.nextDouble();
			}
		}

		//menjadikannya polinom berderajat 2
		for (i=0; i<n; i++){
			for (j=0; j<n+1; j++){
				if (j==n) polinom[i][j] = masukan[i][1];
				else{
					polinom[i][j]=Math.pow(masukan[i][0],j);
				}
			}
		}

		//menjadikan variabel-variabel berikut agar dapat dieliminasi
		this.muatriks = polinom;
		this.brs = n;
		this.kol = n+1;
		
		//lakukan eliminasi pada matriks
		gauss();
		gaussJordan();

		//cetak persamaan polinom dan hasil taksirnya
		System.out.println("persamaannya adalah: ");
		System.out.print("f(x) = ");
		for(i=0; i<n; i++){
			if (i==0) System.out.print(this.muatriks[i][n]);
			else{
				if (this.muatriks[i][n] >= 0) System.out.print(" + "+this.muatriks[i][n]+"x^"+i);
				else System.out.print(" "+this.muatriks[i][n]+"x^"+i);
			}
		}
		System.out.println("");
	}

}








