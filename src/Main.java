import java.util.*;
import java.io.*;

class Main{
	public static void main(String[] args){
		//ini adalah scanner :)
		Scanner input = new Scanner (System.in);

		//buat objek
		Matriks M = new Matriks();
		boolean isMenu = true;
		int menu, submenu, brskol;

		while(isMenu == true){
			//Print Menu
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
					Scanner ingput = new Scanner(System.in);
					System.out.print("Manual atau File? (manual/file) ");
					String choose = ingput.nextLine();

					if (choose.equals("manual")){
						System.out.print("Masukkan jumlah baris/kolom(n): ");
						brskol = input.nextInt();
						M.brs = brskol;
						M.kol = brskol;
						System.out.println("Masukkan elemen matriks(aij): ");
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
					}
					else if(choose.equals("file")){
						M.bacaFileMatriks();
						if (M.isPersegi()){
							if (M.detRed() != 0){
								M.splUsingInverse();
							}else{
								System.out.println("Determinan matriks sama dengan 0");
							}
						}else{
							System.out.println("Matriks bukan persegi");
						}
					}
				}else if (submenu == 4){
					Scanner ingput = new Scanner(System.in);
					System.out.print("Manual atau File? (manual/file) ");
					String choose = ingput.nextLine();

					if (choose.equals("manual")){
						System.out.print("Masukkan jumlah baris: ");
						M.brs = input.nextInt();
						System.out.print("Masukkan jumlah kolom: ");
						M.kol = input.nextInt();
						M.bacaMatriks();
						M.cramer();
					}
					else if(choose.equals("file")){
						M.bacaFileMatriks();
						M.cramer();
					}
				}
			}else if (menu == 2){
				System.out.println("1. Metode reduksi baris");
				System.out.println("2. Metode ekspansi kofaktor");
				System.out.print("Masukkan Sub-menu: ");
				submenu = input.nextInt();
				if (submenu == 1){
					Scanner ingput = new Scanner(System.in);
					System.out.print("Manual atau File? (manual/file)");
					String choose = ingput.nextLine();
					if (choose.equals("manual")){
						System.out.print("Masukkan jumlah baris/kolom(n): ");
						brskol = input.nextInt();
						M.brs = brskol;
						M.kol = brskol;
						System.out.println("Masukkan elemen matriks(aij): ");
						M.bacaMatriks();
						if(M.isPersegi()){
							System.out.println("Determinan matriks menggunakan metode reduksi baris adalah " + M.detRed());
						}else{
							System.out.println("Matriks bukan persegi");
						}
					}
					else if(choose.equals("file")){
						M.bacaFileMatriks();
						if(M.isPersegi()){
							System.out.println("Determinan matriks menggunakan metode reduksi baris adalah " + M.detRed());
						}else{
							System.out.println("Matriks bukan persegi");
						}
					}
					
				}else if (submenu == 2){
					Scanner ingput = new Scanner(System.in);
					System.out.print("Manual atau File? (manual/file) ");
					String choose = ingput.nextLine();
					if (choose.equals("manual")){
						System.out.print("Masukkan jumlah baris/kolom(n): ");
						brskol = input.nextInt();
						M.brs = brskol;
						M.kol = brskol;
						System.out.println("Masukkan elemen matriks(aij): ");
						M.bacaMatriks();
						if(M.isPersegi()){
							System.out.println("Determinan matriks menggunakan ekspansi kofaktor adalah " + M.detEx());
						}else{
							System.out.println("Matriks bukan persegi");
						}
					}
					else if(choose.equals("file")){
						M.bacaFileMatriks();
						if(M.isPersegi()){
							System.out.println("Determinan matriks menggunakan ekspansi kofaktor adalah " + M.detEx());
						}else{
							System.out.println("Matriks bukan persegi");
						}
					}
				}
			}else if (menu == 3){
				System.out.println("1. Gauss-Jordan");
				System.out.println("2. Metode adjoin");
				System.out.print("Masukkan Sub-menu: ");
				submenu = input.nextInt();
				if (submenu == 1){
					Scanner ingput = new Scanner(System.in);
					System.out.print("Manual atau File? (manual/file)");
					String choose = ingput.nextLine();
					if (choose.equals("manual")){
						System.out.print("Masukkan ukuran matriks: ");
						M.brs = input.nextInt();
						M.kol = M.brs;
						M.bacaMatriks();
						M.balikanGJordan();
						M.tulisMatriks();
					}
					else if(choose.equals("file")){
						M.bacaFileMatriks();
						M.balikanGJordan();
						M.tulisMatriks();
					}
					
				}else if (submenu == 2){

					Scanner ingput = new Scanner(System.in);
					System.out.print("Manual atau File? (manual/file)");
					String choose = ingput.nextLine();
					if (choose.equals("manual")){
						System.out.print("Masukkan jumlah baris/kolom(n): ");
						brskol = input.nextInt();
						M.brs = brskol;
						M.kol = brskol;
						System.out.println("Masukkan elemen matriks(aij): ");
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
					else if(choose.equals("file")){
						M.bacaFileMatriks();
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
				}
			}else if (menu == 4){
				Scanner ingput = new Scanner(System.in);
				System.out.print("Manual atau File? (manual/file)");
				String choose = ingput.nextLine();

				if (choose.equals("manual")){
					System.out.print("Masukkan jumlah n : ");
					int barees = input.nextInt();
					M.brs = barees;
					M.kol = 2;
					M.bacaMatriks();
					M.interpolasi();
				}
				else if(choose.equals("file")){
					M.bacaFileMatriks();
					M.interpolasi();
				}
				
			}else if (menu == 5){
				//mikir cok
			}else if (menu == 6){
				isMenu = false;
			}
		}
	}
}


/*
Scanner ingput = new Scanner(System.in);
System.out.print("Manual atau File? (manual/file) ");
String choose = ingput.nextLine();

if (choose.equals("manual")){

}
else if(choose.equals("file")){

}
*/
