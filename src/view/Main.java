package view;

import storage.Management;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Management management=new Management();


        while (true){
            System.out.println("----------Chuong trinh quan li hoc sinh----------");
            System.out.println();
            System.out.println("1. Xem danh sach.");
            System.out.println("2. Them moi.");
            System.out.println("3. Cap nhat.");
            System.out.println("4. Xoa.");
            System.out.println("5. Sap xep.");
            System.out.println("6. Doc tu file.");
            System.out.println("7. Ghi vao file.");
            System.out.println("8. Thoat.");
            System.out.println();
            System.out.println("nhap lua chon cua ban:");
            int num=Integer.parseInt(scanner.nextLine());
            switch (num){
                case 1:
                    management.showList();
                    break;
                case 2:
                    management.addStudent();
                    break;
                case 3:
                    management.updateStudent();
                    break;
                case 4:
                    management.deleteStudent();
                    break;
                case 5:
                    management.arrageList();
                    break;

                case 6:
                    management.loadFromFileAtStart();
                    break;
                case 7:
                    management.saveToFileBeforeExit();
                    break;
                case 8:
                    System.out.println("exiting...");
                    System.exit(8);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }



    }
    
}
