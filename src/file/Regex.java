package file;

import java.util.Scanner;

public class Regex {
    private static final String REGEX_ID = "^ST[a-zA-Z0-9]*$";
    private static final String REGEX_CHAR = "^[a-zA-Z][a-zA-Z0-9]*$";
    private static final String REGEX_NUM = "^\\d+(\\.\\d+)?$";
    private static final Scanner scanner=new Scanner(System.in);
    public static String checkRegexId(){
        String value = scanner.nextLine();
        while (! String.valueOf(value).matches(REGEX_ID)){
            System.out.println("ma ban nhap khong hop le,vui long nhap lai.");
            value = scanner.nextLine();
        }
        return value;
    }
   public static String checkRegexChar(){
        String value = scanner.nextLine();
        while (! String.valueOf(value).matches(REGEX_CHAR)){
            System.out.println("thong tin ban nhap khong hop le");
            value = scanner.nextLine();
        }
        return value;
   }
    public static String checkRegexNum(){
        String value = scanner.nextLine();
        while (! String.valueOf(value).matches(REGEX_NUM)){
            System.out.println("thong tin ban nhap khong hop le");
            value = scanner.nextLine();
        }
        return value;
    }


}
