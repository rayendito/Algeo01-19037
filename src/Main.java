import java.util.*;
import java.io.*;

class Main{
	public static void main(String[] args){
		//ini adalah scanner :)
		Scanner input = new Scanner (System.in);

		//input perintah baca file atau input manual
		System.out.print("Baca file atau input manual? (file/manual/interpol) : ");
	  	String bac = input.nextLine();

	  	//jika input manual
	  	if (bac.equals("manual")){
			//buat objek
			Matriks M = new Matriks();
			boolean isMenu = true;
			int menu, submenu;
			while(isMenu == true){
				System.out.println("MENU");
				System.out.println("1. Sistem Persamaan Linear");
				System.out.println("2. Determinan");
				System.out.println("3. Matriks balikan");
				System.out.println("4. Interpolasi Polinom");
				System.out.println("5. Regresi linier berganda");
				System.out.println("6. Keluar");
				System.out.print("Masukkan Menu: ");
				menu = input.nextInt();

				if (menu == 1){
					System.out.println("1. Metode eliminasi Gauss");
					System.out.println("2. Metode eliminasi Gauss-Jordan");
					System.out.println("3. Metode matriks balikan");
					System.out.println("4. Kaidah Cramer");
					System.out.print("Masukkan Sub-menu: ");
					submenu = input.nextInt();
					if (submenu == 1){
						//
					}else if (submenu == 2){
						//
					}else if (submenu == 3){
						System.out.print("Masukkan jumlah baris: ");
						M.brs = input.nextInt();
						System.out.print("Masukkan jumlah kolom: ");
						M.kol = input.nextInt();
						M.bacaMatriks();
						if (M.isPersegi()){
							if (M.detRed() != 0){
								M.splUsingInverse();
							}else{
								System.out.println("Determinan matriks sama dengan 0");
							}
						}else{
							System.out.println("Matriks bukan persegi");
						}
					}else if (submenu == 4){
						//
					}
				}else if (menu == 2){
					System.out.println("1. Metode reduksi baris");
					System.out.println("2. Metode ekspansi kofaktor");
					System.out.print("Masukkan Sub-menu: ");
					submenu = input.nextInt();
					if (submenu == 1){
						System.out.print("Masukkan jumlah baris: ");
						M.brs = input.nextInt();
						System.out.print("Masukkan jumlah kolom: ");
						M.kol = input.nextInt();
						M.bacaMatriks();
						if(M.isPersegi()){
							System.out.println("Determinan matriks menggunakan metode reduksi baris adalah " + M.detRed());
						}else{
							System.out.println("Matriks bukan persegi");
						}
					}else if (submenu == 2){
						System.out.print("Masukkan jumlah baris: ");
						M.brs = input.nextInt();
						System.out.print("Masukkan jumlah kolom: ");
						M.kol = input.nextInt();
						M.bacaMatriks();
						if(M.isPersegi()){
							System.out.println("Determinan matriks menggunakan ekspansi kofaktor adalah " + M.detEx());
						}else{
							System.out.println("Matriks bukan persegi");
						}
					}
				}else if (menu == 3){
					System.out.println("1. Metode opo iki to");
					System.out.println("2. Metode adjoin");
					System.out.print("Masukkan Sub-menu: ");
					submenu = input.nextInt();
					if (submenu == 1){
						
					}else if (submenu == 2){
						System.out.print("Masukkan jumlah baris: ");
						M.brs = input.nextInt();
						System.out.print("Masukkan jumlah kolom: ");
						M.kol = input.nextInt();
						M.bacaMatriks();
						if(M.isPersegi()){
							if (M.detRed() != 0){
								M.inverseAdj();
								M.tulisMatriks();
							}else{
								System.out.println("Determinan matriks sama dengan 0");
							}
						}else{
							System.out.println("Matriks bukan persegi");
						}
					}
				}else if (menu == 4){
					//monggo to
				}else if (menu == 5){
					//mikir cok
				}else if (menu == 6){
					isMenu = false;
				}
			}
	  		
			// //input baris dan kolom
			// System.out.print("Masukkan jumlah baris: ");
			// M.brs = input.nextInt();

			// System.out.print("Masukkan jumlah kolom: ");
			// M.kol = input.nextInt();

			// //panggil prosedur bacaMatriks
			// M.bacaMatriks();

			// M.tulisMatriks();

			// // tes determinan
			// System.out.println("determinannya: " + M.detRed());
			
			// //tes gauss
			// M.gauss();
			// M.gaussJordan();
			// //panggil prosedur tulisMatriks setelah dijadikan eselon baris
			// M.tulisMatriks();
			// M.solusi();
			
			// //tes apakah matriks persegi
			// if(M.isPersegi()) {
			// 	System.out.println("matriks persegi");
			// }
			// else System.out.println("matriks tidak persegi");
			
			// //tes cramer
			// System.out.println("Hasil tes Cramer : ");
			// M.cramer();
			
	  	}
	  	//jika input dengan file
		else if (bac.equals("file")){
	  		System.out.print("Nama file : ");
	  		String namaFile = input.nextLine();

	  		System.out.println("terus yoopo iki hayo aku ga ngerti wkwk");
	  	}
	  	else if (bac.equals("interpol")){
	  		Matriks M = new Matriks();
	  		M.interpolasi();
	  	}
	}
}
