import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AES_tool aes_tool = new AES_tool();
        Scanner sc = new Scanner(System.in);
        while(true) {
            aes_tool.help();
            System.out.print("번호를 입력하세요 >");
            int select = sc.nextInt();
            if(aes_tool.start(select) == -1) {
                System.out.println("종료.");
                return;
            }
        }
    }
}