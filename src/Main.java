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
					Scanner ingput = new Scanner(System.in);
					System.out.print("Manual atau File? (manual/file) ");
					String choose = ingput.nextLine();

					if (choose.equals("manual")){
						System.out.print("Masukkan baris: ");
						M.brs = ingput.nextInt();
						System.out.print("Masukkan kolom: ");
						M.kol = ingput.nextInt();
						M.bacaMatriks();
						M.gauss();
						System.out.println("Ini adalah matriks setelah eliminasi Gauss:");
						M.tulisMatriks();
						M.gaussJordan();
						M.solusiGJ();
					}
					else if(choose.equals("file")){
						M.bacaFileMatriks();
						M.gauss();
						System.out.println("Ini adalah matriks setelah eliminasi Gauss:");
						M.tulisMatriks();
						M.gaussJordan();
						M.solusiGJ();

					}
				}else if (submenu == 2){
					Scanner ingput = new Scanner(System.in);
					System.out.print("Manual atau File? (manual/file) ");
					String choose = ingput.nextLine();

					if (choose.equals("manual")){
						System.out.print("Masukkan baris: ");
						M.brs = ingput.nextInt();
						System.out.print("Masukkan kolom: ");
						M.kol = ingput.nextInt();
						M.bacaMatriks();
						M.gauss();
						M.gaussJordan();
						System.out.println("Ini adalah matriks setelah eliminasi Gauss-Jordan:");
						M.tulisMatriks();
						M.solusiGJ();
					}
					else if(choose.equals("file")){
						M.bacaFileMatriks();
						M.gauss();
						M.gaussJordan();
						System.out.println("Ini adalah matriks setelah eliminasi Gauss-Jordan:");
						M.tulisMatriks();
						M.solusiGJ();

					}
				}else if (submenu == 3){
					Scanner ingput = new Scanner(System.in);
					System.out.print("Manual atau File? (manual/file) ");
					String choose = ingput.nextLine();

					if (choose.equals("manual")){
						System.out.print("Masukkan baris: ");
						M.brs = ingput.nextInt();
						System.out.print("Masukkan kolom: ");
						M.kol = ingput.nextInt();
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
						if (M.brs == (M.kol-1)){
							M.splUsingInverseFile();
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

						// output file
						String toOutput;
						System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
						System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
						String namafile = ingput.nextLine();
						//create file dulu
						M.createfile(namafile);
						if(M.isPersegi()){
							toOutput = "Determinan matriks menggunakan metode reduksi baris adalah " + M.detRed();
							M.outputfile(toOutput, namafile);
							System.out.println(toOutput);
						}else{
							toOutput = "Matriks bukan persegi";
							M.outputfile(toOutput, namafile);
							System.out.println(toOutput);
						}
					}
					else if(choose.equals("file")){
						M.bacaFileMatriks();

						// output file
						String toOutput;
						System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
						System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
						String namafile = ingput.nextLine();
						M.createfile(namafile);
						if(M.isPersegi()){
							toOutput = "Determinan matriks menggunakan metode reduksi baris adalah " + M.detRed();
							M.outputfile(toOutput, namafile);
							System.out.println(toOutput);
						}else{
							toOutput = "Matriks bukan persegi";
							M.outputfile(toOutput, namafile);
							System.out.println(toOutput);
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

						// output file
						String toOutput;
						System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
						System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
						String namafile = ingput.nextLine();
						M.createfile(namafile);
						if(M.isPersegi()){
							toOutput = "Determinan matriks menggunakan ekspansi kofaktor adalah " + M.detEx();
							M.outputfile(toOutput, namafile);
							System.out.println(toOutput);
						}else{
							toOutput = "Determinan matriks menggunakan ekspansi kofaktor adalah " + M.detEx();
							M.outputfile(toOutput, namafile);
							System.out.println(toOutput);
						}
					}
					else if(choose.equals("file")){
						M.bacaFileMatriks();

						// output file
						String toOutput;
						System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
						System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
						String namafile = ingput.nextLine();
						M.createfile(namafile);
						if(M.isPersegi()){
							toOutput = "Determinan matriks menggunakan ekspansi kofaktor adalah " + M.detEx();
							M.outputfile(toOutput, namafile);
							System.out.println(toOutput);
						}else{
							toOutput = "Determinan matriks menggunakan ekspansi kofaktor adalah " + M.detEx();
							M.outputfile(toOutput, namafile);
							System.out.println(toOutput);
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
						if (M.detRed() != 0){
							M.inverseAdj();
							M.tulisMatriks();
						}else{
							// output file
							String toOutput;
							System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
							System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
							String namafile = ingput.nextLine();
							M.createfile(namafile);
							toOutput = "Determinan matriks sama dengan 0";
							M.outputfile(toOutput, namafile);
							System.out.println(toOutput);
						}
					}
					else if(choose.equals("file")){
						M.bacaFileMatriks();
						if(M.isPersegi()){
							if (M.detRed() != 0){
								M.inverseAdj();
								M.tulisMatriks();
							}else{
								// output file
								String toOutput;
								System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
								System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
								String namafile = ingput.nextLine();
								M.createfile(namafile);
								toOutput = "Determinan matriks sama dengan 0";
								M.outputfile(toOutput, namafile);
								System.out.println(toOutput);
							}
						}else{
							// output file
							String toOutput;
							System.out.println("PASTIKAN NAMA FILE BELUM PERNAH DIGUNAKAN AGAR MENDAPAT HASIL YANG DIINGINKAN");
							System.out.print("Masukkan nama output file dengan .txt contoh (beres.txt): ");
							String namafile = ingput.nextLine();
							M.createfile(namafile);
							toOutput = "Matriks bukan persegi";
							M.outputfile(toOutput, namafile);
							System.out.println(toOutput);
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
				Scanner ingput = new Scanner(System.in);
				System.out.print("Manual atau File? (manual/file) ");
				String choose = ingput.nextLine();
				if (choose.equals("manual")){
					int i,j;
					System.out.print("Masukkan jumlah data: ");
					M.brs = input.nextInt();
					System.out.print("Masukkan jumlah peubah: ");
					M.kol = input.nextInt();
					M.kol = M.kol + 2;
					M.muatriks = new double[M.brs][M.kol];
					for (i=0; i<M.brs; i++){
						M.muatriks[i][0] = 1;
					}
					for (i=0; i<M.brs; i++){
						for (j=1; j<M.kol; j++){
							M.muatriks[i][j] = input.nextDouble();
						}
					}
					M.regresi();
				}
				else if(choose.equals("file")){
					int i,j;
					Matriks M1 = new Matriks();
					M1.bacaFileMatriks();
					M.brs = M1.brs;
					M.kol = M1.kol + 1;
					M.muatriks = new double[M.brs][M.kol];
					for (i=0; i<M.brs; i++){
						M.muatriks[i][0] = 1;
					}
					for (i=0; i<M1.brs; i++){
						for (j=0; j<M1.kol; j++){
							M.muatriks[i][j+1] = M1.muatriks[i][j];
						}
					}
					M.regresi();
				}
				
			}else if (menu == 6){
				isMenu = false;
			}
		}
	}
}
