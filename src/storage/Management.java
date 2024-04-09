package storage;

import model.Student;
import file.Regex;


import java.io.*;
import java.util.*;

public class Management {
    private List<Student> students;

   private static final Scanner scanner = new Scanner(System.in);

    private static final String PathFile="/Volumes/DATA/Module_2/ket_thuc_module2/src/studentList.csv";





    public Management() {
        students =new ArrayList<>();


    }
    public void showList(){
        if(students.isEmpty()){
            System.out.println("danh sach hoc sinh dang trong. ");
            return;
        }

        boolean exit=false;
        int index=0;
        do {
            System.out.println("Nhan Enter de xem hoc sinh tiep theo hoac nhap exit de thoat.");
            String input=scanner.nextLine();
            if(input.equals("")){
                if(index< students.size()){
                    int endIndex=Math.min(index+5, students.size());
                    for (int i=index;i<endIndex;i++){
                        System.out.println(students.get(i));
                    }
                    index=endIndex;
                }
                else {
                    System.out.println("Da hien thi het danh sach.");
                    exit=true;
                }
            }
            else if (input.equalsIgnoreCase("exit")){
                System.out.println("thoat chuong trinh");
                exit=true;
            }
            else {
                System.out.println("lua chon khong phu hop.");
            }
        } while (! exit);
    }
    public void addStudent(){
        System.out.println("Nhap ma hoc sinh:");
        String id = Regex.checkRegexId();
        System.out.println("Nhap ten hoc sinh: ");
        String name = Regex.checkRegexChar();
        System.out.println("Nhap tuoi hoc sinh:");
        int age = Integer.parseInt (Regex.checkRegexNum());
        System.out.println("Nhap gioi tinh hoc sinh:");
        String sex = Regex.checkRegexChar();
        System.out.println("Nhap dia chi hoc sinh:");
        String add = Regex.checkRegexChar();
        System.out.println("Nhap diem trung binh:" );
        double avgPoint = Double.parseDouble(Regex.checkRegexNum()) ;

        Student student=new Student(id,name,age,sex,add,avgPoint);
        students.add(student);
        System.out.println("Hoc sinh "+name+" da duoc them vao. ");

    }
    public void updateStudent(){
        if(students.isEmpty()){
            System.out.println("danh sach hoc sinh dang trong. ");
            return;
        }
        boolean check=true;
        do{
            boolean found=false;
            System.out.println("Nhap ma hoc sinh muon chinh sua:");
            String id=scanner.nextLine();
        for (Student student: students){
            if(student.getId().equals(id)){
                found=true;
                System.out.println("Nhap ma hoc sinh: "+ student.getId());
                String newId = Regex.checkRegexId();
                System.out.println("Nhap ten hoc sinh: "+student.getName());
                String newName = Regex.checkRegexChar();
                System.out.println("Nhap tuoi hoc sinh: "+ student.getAge());
                int newAge = Integer.parseInt (Regex.checkRegexNum());
                System.out.println("Nhap gioi tinh hoc sinh: "+student.getSex());
                String newSex = Regex.checkRegexChar();
                System.out.println("Nhap dia chi hoc sinh: "+student.getAdd());
                String newAdd = Regex.checkRegexChar();
                System.out.println("Nhap diem trung binh: "+student.getAvgPoint() );
                double newAvgPoint = Double.parseDouble(Regex.checkRegexNum()) ;
                student.setAdd(newId);
                student.setName(newName);
                student.setAge(newAge);
                student.setSex(newSex);
                student.setAdd(newAdd);
                student.setAvgPoint(newAvgPoint);
                System.out.println("thong tin hoc sinh da duoc chinh sua.");
                break;


            }
        }
        if (!found){
            System.out.println("ma ban nhap khong dung vui long nhap lai.");
        }
        else {
            check=false;
        }
        }while (check);
    }
    public void deleteStudent() {
        if(students.isEmpty()) {
            System.out.println("Danh sách học sinh đang trống.");
            return;
        }

        boolean isIdFound = false;

        while (!isIdFound) {
            System.out.println("Nhập mã học sinh bạn muốn xóa:");
            String inputId = scanner.nextLine();

            Iterator<Student> iterator = students.iterator();
            while (iterator.hasNext()) {
                Student student = iterator.next();
                if (student.getId().equals(inputId)) {
                    System.out.println(student.toString());
                    System.out.println("Nhập 'y' để xóa học sinh, 'n' để thoát chương trình:");
                    String inputChar = scanner.nextLine();
                    if (inputChar.equals("y")) {
                        iterator.remove();
                        System.out.println("Đã xóa thành công.");
                    } else if (inputChar.equalsIgnoreCase("n")) {
                        System.out.println("Thoát chương trình.");
                        return;
                    } else {
                        System.out.println("Lựa chọn không hợp lệ.");
                    }
                    isIdFound = true;
                    break;
                }
            }

            if (!isIdFound) {
                System.out.println("Mã bạn nhập không đúng.");
            }
        }
    }

    public void arrageList() {
        if(students.isEmpty()){
            System.out.println("danh sach hoc sinh dang trong. ");
            return;
        }
        System.out.println("Nhap '1' de sap xep tang dan, '2' de sap xep giam dan, '3' de thoat chuong trinh. ");
        String input = scanner.nextLine();
        if (input.equals("1")) {
            students.sort(new Comparator<Student>() {
                @Override
                public int compare(Student student, Student t1) {
                    return Double.compare(student.getAvgPoint(), t1.getAvgPoint());
                }
            });
            showList();
        }
        else if (input.equals("2")){
            students.sort(new Comparator<Student>() {
                @Override
                public int compare(Student product, Student t1) {
                    return Double.compare(t1.getAvgPoint(), product.getAvgPoint());
                }
            });
            showList();

        }
        else if(input.equalsIgnoreCase("3")){
            System.out.println("thoat chuong trinh.");

        }

    }

    public void saveToFileBeforeExit() {
        writeToFile();
    }


    public void loadFromFileAtStart() {
        readFromFile();
    }
    public void readFromFile() {
        System.out.println("Lựa chọn này sẽ ghi đè toàn bộ danh sách sản phẩm hiện tại. Bạn có muốn tiếp tục? (yes/no)");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PathFile))) {
                students = (List<Student>) objectInputStream.readObject();
                System.out.println("Đã cập nhật danh sách sản phẩm từ file.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Đã xảy ra lỗi khi đọc file: " + e.getMessage());
            }
        } else {
            System.out.println("Không có thay đổi nào được thực hiện.");
        }
    }
    public void writeToFile() {
        System.out.println("Lựa chọn này sẽ ghi đè toàn bộ nội dung file. Bạn có muốn tiếp tục? (yes/no)");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(PathFile))) {
                objectOutputStream.writeObject(students);
                System.out.println("Đã ghi danh sách sản phẩm vào file.");
            } catch (IOException e) {
                System.out.println("Đã xảy ra lỗi khi ghi vào file: " + e.getMessage());
            }
        } else {
            System.out.println("Không có thay đổi nào được thực hiện.");
        }
    }


}
