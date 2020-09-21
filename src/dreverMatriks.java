//Matrix Class :)
import java.util.*;
import java.io.*;

public class dreverMatriks{
	public static void main(String[] args){
		//Input dimensi matriksnya
	  	Scanner ingput = new Scanner (System.in);

	  	System.out.print("Baca file atau input manual? (file/manual) : ");
	  	String bac = ingput.nextLine();

	  	if (bac.equals("manual")){
	  		System.out.print("Masukkan jumlah baris: ");
			int baris = ingput.nextInt();

			System.out.print("Masukkan jumlah kolom: ");
			int kolom = ingput.nextInt();

			System.out.println("Anda memasukkan matriks "+baris+" x "+kolom+".");

			//Definisikan matriksnya
			int[][] muatriks = new int[baris][kolom];

			//Baca matriks
			bacaMatriks(ingput, muatriks, baris, kolom);

			//Tulis matriks
			tulisMatriks(muatriks, baris, kolom);
	  	}

	  	else if (bac.equals("file")){
	  		System.out.print("Nama file : ");
	  		String namaFile = ingput.nextLine();

	  		System.out.println("terus yoopo iki hayo aku ga ngerti wkwk");
	  	}

	}

	public static void bacaMatriks(Scanner ingput, int[][] muatriks, int baris, int kolom){
		//Kamus
		int i,j;

		//Algoritma
		for (i=0; i<baris; i++){
			for (j=0; j < kolom; j++){
				System.out.print("Masukkan nilai matriks baris "+(i+1)+" kolom "+(j+1)+": ");
				muatriks[i][j] = ingput.nextInt();
			}
		}
	}
	public static void tulisMatriks(int[][] muatriks, int baris, int kolom){
		//Kamus
		int i,j;

		//Algoritma
		for (i=0; i<baris; i++){
			for (j=0; j < kolom; j++){
				System.out.print(muatriks[i][j]+"\t");
			}
			System.out.println();
		}
	}
}