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

	void bacaFileMatriks(){
		//inisialisasi
		this.brs = 1;
		this.kol = 0;

		//input nama file
		Scanner namaf = new Scanner(System.in);
		System.out.print("Nama file : ");
		String namafile = namaf.nextLine();
		namafile = "../test/"+namafile;
		
		//menghitung ukuran matriks terlebih dahulu
		try{
			Scanner read = new Scanner(new File(namafile));
			this.kol = read.nextLine().split(" ").length;
			while (read.hasNextLine()){
				this.brs++;
				read.nextLine();
			}
			read.close();

			//memasukkan data ke dalam matriks
			Scanner baca = new Scanner(new BufferedReader(new FileReader(namafile)));
			this.muatriks = new double[this.brs][this.kol];
			while(baca.hasNextLine()){
				for(int i=0; i<this.brs; i++){
					String[] kolom = baca.nextLine().split(" ");
					for(int j=0; j<this.kol; j++){
						this.muatriks[i][j] = Double.parseDouble(kolom[j]);
					}
				}
			}
			
		}
		catch(FileNotFoundException e){
			System.out.println("file tidak ditemukan");
			bacaFileMatriks();
		}
	}

	//create file (Tested)
	public void createfile(String namafile) {
        try {
        
        File myObj = new File(namafile);
		if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }
        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }

	//append file
    public void outputfile(String toprint, String namafile) {
        try {
        FileWriter fw = new FileWriter(namafile,true);
    	BufferedWriter bw = new BufferedWriter(fw);
    	PrintWriter thewriter = new PrintWriter(bw);
        thewriter.println(toprint);
        thewriter.close();
        // System.out.println("Successfully wrote to the file.");
        } 
		catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }

	void tulisMatriks() {
		int i,j;
		String toOutput;

		Scanner ingput = new Scanner(System.in);
		System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
		System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
		String namafile = ingput.nextLine();
		//create file dulu
		createfile(namafile);
		outputfile("Matriks yang dihasilkan adalah: ", namafile);

		for (i=0; i<this.brs; i++){
			toOutput = "";
			for (j=0; j<this.kol; j++){
				System.out.print(this.muatriks[i][j]+"\t");
				toOutput += this.muatriks[i][j] + " ";
			}
			outputfile(toOutput, namafile);
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

	public boolean isBarisNol(int bar) {
		int j=0;
		boolean nol=true;
		while(j<this.kol && nol){
			if(this.muatriks[bar][j] != 0) nol=false;
			j++;
		}
		return nol;
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

	// copyMatriks (Tested) cara pakai -> double[][] variable = copyMatriks()
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

	// Matriks cofactor (Tested)
	public double[][] mCofactor(){
		double[][] tmp = copyMatriks();
		double[][] mCofactor = copyMatriks();
		
		int k,l,bariske,kolomke;
		for (int i=0; i<=lastBaris(); i++){
			for (int j=0; j<=lastKolom(); j++){
				this.brs--;
				this.kol--;
				//bikin matriks minor dulu
				k = 0;
				l = 0;
				bariske = 0;
				kolomke = 0;
				while(k<=lastBaris()){
					l = 0;
					kolomke = 0;
					while(l<=lastKolom()){
						if (bariske==i){
							bariske++;
						}
						if (kolomke==j){
							kolomke++;
						}
						this.muatriks[k][l] = tmp[bariske][kolomke];
						l++;
						kolomke++;
					}
					k++;
					bariske++;
				}
				//assign elemen matriks cofactor
				if((i+j)%2 == 1){
					mCofactor[i][j] = -1*detRed();
				}else{
					mCofactor[i][j] = detRed();
				}
				this.brs++;
				this.kol++;
			}
		}
		this.muatriks = tmp;
		
		return mCofactor;
	}

	// Determinan dengan ekspansi cofactor (Tested)
	public double detEx(){
		double[][] cofactor = mCofactor();
		double det = 0;
		for(int j=0; j<=lastKolom(); j++){
				det += cofactor[0][j]*this.muatriks[0][j];
		}
		
		return det;
	}

	// Determinan dengan reduksi baris (Tested)
	public double detRed(){
		double[][] tmp = copyMatriks();
		double det;
		det = 1;
		// Di urut terlebihdahulu
		int idxpertama = 0;
		int j = 0;
		while ((j<=lastKolom()) && (idxpertama != lastBaris())){
			for(int i=idxpertama; i<=lastBaris(); i++){
				for(int k=idxpertama; k<=lastBaris(); k++){
					if (this.muatriks[k][j] == 0){
						int l = k+1;
						boolean found = false;
						while(l<=lastBaris() && !found){
							if(this.muatriks[l][j] != 0){
								found = true;
								tukarBaris(k, l);
								det *= -1;
							}
							l++;
						}
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
		this.muatriks = tmp;
		return det;
	}

	// adjoin (Tested)
	void adjoin(){
		this.muatriks = mCofactor();
		Transpose();
	}

	// inverse menggunakan adjoin (Tested)
	void inverseAdj(){
		double det = detEx();
		adjoin();
		for (int i=0; i<=lastBaris(); i++){
			for (int j=0; j<=lastKolom(); j++){
				this.muatriks[i][j] = this.muatriks[i][j]/det;
			}
		}
	}

	// spl menggunakan metode matriks balikan (Tested)
	void splUsingInverse(){
		inverseAdj();
		double[][] toInverse = copyMatriks();
		this.kol = 1;
		System.out.println("Masukkan elemen matriks(bi): ");
		bacaMatriks();
		double[][] matriks2 = copyMatriks();

		// Ax = b -> x = inverse(A)b (kali matriksnya)
		for (int i=0; i<=lastBaris(); i++){
			for (int j=0; j<=lastKolom(); j++){
				this.muatriks[i][j] = 0;
				for (int k=0; k<=lastBaris(); k++){
					this.muatriks[i][j] = this.muatriks[i][j] + toInverse[i][k]*matriks2[k][j];
				}
			}
		}

		// output file
		Scanner ingput = new Scanner(System.in);
		System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
		System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
		String namafile = ingput.nextLine();
		//create file dulu
		createfile(namafile);

		// menampilkan solusi splnya dan menuliskan ke file
		String toprint;
		for (int baris=0; baris<=lastBaris(); baris++){ 
			int bariske = baris+1;
			toprint = "X"+ bariske + " = " + this.muatriks[baris][0];
			outputfile(toprint, namafile);
			System.out.println(toprint);
		}
	}

	void splUsingInverseFile(){
		// split matriks augmented jadi 2 matriks
		double[][] toSplit = copyMatriks();
		int kolterakhir = lastKolom();
		// inverse matriks A
		this.kol = this.kol-1;
		for (int a=0; a<=lastBaris(); a++){
			for(int b=0; b<=lastKolom();b++){
				this.muatriks[a][b] = toSplit[a][b];
			}
		}
		if (detRed() == 0){
			System.out.println("Determinan matriks sama dengan 0");
		}else{
			// lanjutan inverse A ketika det A != 0
			inverseAdj();
			double[][] toInverse = copyMatriks();
			// matriks b
			this.kol = 1;
			for (int c=0; c<=lastBaris(); c++){
				this.muatriks[c][kolterakhir] = toSplit[c][kolterakhir];
			}
			double[][] matriks2 = copyMatriks();

			// Ax = b -> x = inverse(A)b (kali matriksnya)
			for (int i=0; i<=lastBaris(); i++){
				for (int j=0; j<=lastKolom(); j++){
					this.muatriks[i][j] = 0;
					for (int k=0; k<=lastBaris(); k++){
						this.muatriks[i][j] = this.muatriks[i][j] + toInverse[i][k]*matriks2[k][j];
					}
				}
			}

			// output file
			Scanner ingput = new Scanner(System.in);
			System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
			System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
			String namafile = ingput.nextLine();
			//create file dulu
			createfile(namafile);

			// menampilkan solusi splnya
			String toprint;
			for (int baris=0; baris<=lastBaris(); baris++){
				int bariske = baris+1;
				toprint = "X"+ bariske + " = " + this.muatriks[baris][0];
				outputfile(toprint, namafile);
				System.out.println(toprint);
			}
		}
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
	
	public void solusiG() {
		Scanner ingput = new Scanner (System.in);
		System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
		System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
		String namafile = ingput.nextLine();
		this.createfile(namafile);
		int i,j,k,l;
		int varnumber = 97;
		char var;
		boolean konsisten = true;
		String hasil = "";
		for(l=0;l<=this.brs-1;l++) {
			if(this.inkonsisten(l)) {
				konsisten = false;
				break;
			}
		}
		if(!(konsisten)) {
			String toprint;
			toprint = "SPL tidak memiliki solusi";
			outputfile(toprint, namafile);
			System.out.println(toprint);
		}
		else {
			for(i=0;i<=this.brs-1;i++) {
				if(!(this.barisKosong(i))) {
					if(this.muatriks[i][i] == 1) {
						hasil = "x" + (i+1) + " = " + this.muatriks[i][this.kol-1];
						for(j=0;j<=this.kol-2;j++) {
							if((j != i) && (this.muatriks[i][j] != 0)) {
								if(this.muatriks[i][j] < 0) {
								hasil = hasil + " + " + (-(this.muatriks[i][j])) + " x" + (j+1); 
								}
								else {
									hasil = hasil + " - " + this.muatriks[i][j] + " x" + (j+1); 
								}
							}
						}
					}
				}
				else if(this.muatriks[i][i] == 0) {
					var = (char)varnumber;
					hasil = "x" + (i+1) + " = " + var;
					varnumber = varnumber + 1;
				}
				outputfile(hasil, namafile);
				System.out.println(hasil);
			}
		}
	}
	
	public void solusiGJ() {
		// output file
		Scanner ingput = new Scanner (System.in);
		System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
		System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
		String namafile = ingput.nextLine();
		this.createfile(namafile);
		int i,j,k,l,m;
		int varnumber = 97;
		boolean konsisten = true;
		String hasil;
		String[] storage = new String[this.kol-1];
		for(j=0;j<this.kol-1;j++) {
			storage[j] = "";
		}
		for(l=0;l<=this.brs-1;l++) {
			if(this.inkonsisten(l)) {
				konsisten = false;
				break;
			}
		}
		if(!(konsisten)) {
			String toprint;
			toprint = "SPL tidak memiliki solusi";
			outputfile(toprint, namafile);
			System.out.println(toprint);
		}
		else {
			for(i=0;i<=this.brs-1;i++) {
				for(j=0;j<=this.kol-2;j++) {
					if(this.muatriks[i][j] == 1) {
						hasil = this.muatriks[i][this.kol-1] + "";
						for(k=j+1;k<=this.kol-2;k++) {
							if(this.muatriks[i][k] != 0) {
								if(this.muatriks[i][k] < 0) {
									if(storage[k] == "") {
										storage[k] = (char)varnumber + "";
										varnumber = varnumber + 1;
									}
									if(-(this.muatriks[i][k]) == 1) {
										hasil = hasil + " + " + storage[k];
									}
									else {
										hasil = hasil + " + " + (-(this.muatriks[i][k])) + storage[k];
									}
								}
								else if(this.muatriks[i][k] > 0) {
									if(storage[k] == "") {
										storage[k] = (char)varnumber + "";
										varnumber = varnumber + 1;	
									}
									if(this.muatriks[i][k] == 1) {
										hasil = hasil + " - " + storage[k]; 
									}
									else {
										hasil = hasil + " - " + (this.muatriks[i][k]) + storage[k];
									}
								}
							}
						}
						storage[j] = hasil;
						break;
					}
				}
			}
			for(m=0;m<this.kol-1;m++) {
				if(storage[m] == "") {
					storage[m] = (char)varnumber + "";
					varnumber = varnumber + 1;
				}
			}
			for(m=0;m<this.kol-1;m++) {
				String toprint;
				toprint = "x" + (m+1) + " = " + storage[m];
				outputfile(toprint, namafile);
				System.out.println(toprint);
			}
		 }
	}	

	public boolean isPersegi() {
		return(this.brs == this.kol);
	}
	
	void cramer(){
		// output file
		Scanner ingput = new Scanner (System.in);
		System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
		System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
		String namafile = ingput.nextLine();
		this.createfile(namafile);
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
			if(detAwal == 0) {
				String toprint;
				toprint = "matriks tidak memiliki solusi unik";
				outputfile(toprint, namafile);
				System.out.println(toprint);
			}
			else {
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
					
					
					String toprint;
					toprint = "x" + (j+1) + " = " + (hasil / detAwal);
					outputfile(toprint, namafile);
					System.out.println(toprint);
					
				}
			}
		}
		else {
			String toprint;
			toprint = "matriks tidak valid";
			outputfile(toprint, namafile);
			System.out.println(toprint);
		}
	}

	public boolean barisKosong(int n) {
		int j;
		for(j=0;j<=this.kol-1;j++) {
			if(this.muatriks[n][j] != 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean kolomKosong(int n) {
		int i;
		for(i=0;i<=this.brs-1;i++) {
			if(this.muatriks[i][n] != 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean inkonsisten(int n) {
		int j;
		for(j=0;j<=this.kol-2;j++) {
			if(this.muatriks[n][j] != 0) {
				return false;
			}
		}
		if (this.muatriks[n][this.kol-1] == 0) {
			return false;
		}
		return true;
	}
	
	void interpolasi(){
		int i,j;
		double[][] polinom = new double[this.brs][this.brs+1];

		//menjadikannya polinom berderajat 2
		for (i=0; i<this.brs; i++){
			for (j=0; j<this.brs+1; j++){
				if (j==this.brs) polinom[i][j] = this.muatriks[i][1];
				else{
					polinom[i][j]=Math.pow(this.muatriks[i][0],j);
				}
			}
		}

		//menjadikan variabel-variabel berikut agar dapat dieliminasi
		this.muatriks = polinom;
		this.kol = this.brs+1;
		
		//lakukan eliminasi pada matriks
		gauss();
		gaussJordan();
		
		// output file
		Scanner ingput = new Scanner(System.in);
		System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
		System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
		String namafile = ingput.nextLine();
		//create file dulu
		createfile(namafile);

		//cetak persamaan polinom dan hasil taksirnya, serta tulis ke file
		System.out.println("persamaannya adalah: ");
		outputfile("persamaannya adalah: ", namafile);

		String persaman;
		System.out.print("f(x) = ");
		persaman = "f(x) = ";
		for(i=0; i<this.brs; i++){
			if (i==0) System.out.print(this.muatriks[i][this.brs]);
			else{
				if (this.muatriks[i][this.brs] >= 0){
					System.out.print(" + "+this.muatriks[i][this.brs]+"x^"+i);
					persaman = persaman + " + "+this.muatriks[i][this.brs]+"x^"+i;
				} 
				else{
					System.out.print(" "+this.muatriks[i][this.brs]+"x^"+i);
					persaman = persaman + " "+this.muatriks[i][this.brs]+"x^"+i;
				}
			}
		}
		outputfile(persaman,namafile);
		System.out.println("");

		//input nilai yang ingin ditaksir
		double total=0;
		System.out.print("Masukkan x yang ingin ditaksir: ");

		String haasil;
		Scanner input = new Scanner(System.in);
		double taksir = input.nextDouble();
		for(i=0; i<this.brs; i++){
			total += this.muatriks[i][this.brs]*Math.pow(taksir,i);
		}
		System.out.println("Hasil taksiran untuk "+taksir+" adalah = "+total);
		haasil = "Hasil taksiran untuk "+taksir+" adalah = "+total;
		outputfile(haasil,namafile);
	}

	void balikanGJordan(){
		int i,j;
		//menambahkan matriks identitas ke matriks dengan matriks sementara
		double[][] augmented = new double[this.brs][this.brs+this.kol];
		for(i=0; i<this.brs; i++){
			for(j=0; j<this.kol; j++){
				augmented[i][j] = this.muatriks[i][j];
			}
		}
		for(i=0; i<this.brs; i++){
			for(j=this.kol; j<(this.brs+this.kol); j++){
				if(i == j-this.kol) augmented[i][j]=1;
				else augmented[i][j] = 0;
			}
		}

		//matriks sementara, masih ada identitas di depan matriks hasil balikan
		this.muatriks = augmented;
		this.brs = this.brs;
		this.kol = this.brs+this.kol;

		//proses
		gauss();
		gaussJordan();
		
		//mengembalikan matriks ke matriks awal
		double[][] akhir = new double[this.brs][this.kol-this.brs];
		for(i=0; i<this.brs; i++){
			for(j=0; j<this.kol-this.brs; j++){
				akhir[i][j] = this.muatriks[i][j+(this.kol-this.brs)];
			}
		}

		//mengembalikan ke matriks awal 'muatriks'
		this.muatriks = akhir;
		this.brs = this.brs;
		this.kol = this.kol-this.brs;
	}
	
	void regresi() {
		// output file
		Scanner ingput = new Scanner (System.in);
		System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
		System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
		String namafile = ingput.nextLine();
		this.createfile(namafile);
		int i,j,k,l;
		String hasil = "y = ";
		Scanner input = new Scanner (System.in);
		double total = 0;
		double x;
			
		Matriks M1 = new Matriks();
		M1.muatriks = new double[this.kol-1][this.kol];
		M1.brs = this.kol-1;
		M1.kol = this.kol;
		for (i=0; i<M1.brs; i++){
			for (j=0; j<M1.kol; j++){
				M1.muatriks[i][j] = 0;
			}
		}
		
		for(i=0;i<M1.brs;i++) {
			for(j=0;j<M1.kol;j++) {
				for(k=0;k<this.brs;k++) {
					M1.muatriks[i][j] = M1.muatriks[i][j] + (this.muatriks[k][i] * this.muatriks[k][j]);
				}
			}	
		}
		
		M1.gauss();
		M1.gaussJordan();
		
		hasil = hasil + M1.muatriks[0][this.kol-1];
		for(i=1;i<M1.brs;i++) {
			if(M1.muatriks[i][this.kol-1] > 0) {
				hasil = hasil + " + " + M1.muatriks[i][this.kol-1] + " x" + i;
			}
			else if(M1.muatriks[i][this.kol-1] < 0) {
				hasil = hasil + " + " + (-(M1.muatriks[i][this.kol-1])) + " x" + i;
			}
		}
		outputfile(hasil, namafile);
		System.out.println(hasil);
		
		total = total + M1.muatriks[0][this.kol-1];
		for(j=1;j<this.kol-1;j++) {
			System.out.print("Masukkan nilai x" + (j) + " : " );
			x = input.nextDouble();
			total = total + (x * M1.muatriks[j][this.kol-1]);
		}
		String toprint;
		toprint = "Taksiran nilai fungsi adalah: " + total;
		outputfile(toprint, namafile);
		System.out.println(toprint);
	}
}
