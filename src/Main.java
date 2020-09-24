import java.util.*;
import java.io.*;

class Main{
	public static void main(String[] args){
		//ini adalah scanner :)
		Scanner input = new Scanner (System.in);

		//input perintah baca file atau input manual
		System.out.print("Baca file atau input manual? (file/manual) : ");
	  	String bac = input.nextLine();

	  	//jika input manual
	  	if (bac.equals("manual")){
			  //buat objek
			Matriks M = new Matriks();
	  		
			//input baris dan kolom
			System.out.print("Masukkan jumlah baris: ");
			M.brs = input.nextInt();

			System.out.print("Masukkan jumlah kolom: ");
			M.kol = input.nextInt();

			//panggil prosedur bacaMatriks
			M.bacaMatriks();

			M.tulisMatriks();
			//tes gauss
			M.gauss();
			//panggil prosedur tulisMatriks setelah dijadikan eselon baris
			M.tulisMatriks();
	  	}
	  	//jika input dengan file
		else if (bac.equals("file")){
	  		System.out.print("Nama file : ");
	  		String namaFile = input.nextLine();

	  		System.out.println("terus yoopo iki hayo aku ga ngerti wkwk");
	  	}
	}
}
