import java.util.*;
import java.io.*;

class mainMatriks{
	public static void main(String[] args){
		//ini adalah scanner :)
		Scanner input = new Scanner (System.in);

		//input perintah baca file atau input manual
		System.out.print("Baca file atau input manual? (file/manual) : ");
	  	String bac = input.nextLine();

	  	//jika input manual
	  	if (bac.equals("manual")){
	  		//input baris dan kolom
			System.out.print("Masukkan jumlah baris: ");
			int baris = input.nextInt();

			System.out.print("Masukkan jumlah kolom: ");
			int kolom = input.nextInt();

			//buat objek
			Matriks M = new Matriks();

			//panggil prosedur bacaMatriks
			M.bacaMatriks(baris, kolom);

			//panggil prosedur tulisMatriks
			M.tulisMatriks(baris, kolom);
	  	}
	  	//jika input dengan file
		else if (bac.equals("file")){
	  		System.out.print("Nama file : ");
	  		String namaFile = input.nextLine();

	  		System.out.println("terus yoopo iki hayo aku ga ngerti wkwk");
	  	}
	}
}
