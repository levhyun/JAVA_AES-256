import java.util.Scanner;

class AES_tool {
    static AES aes = new AES();
    static Scanner sc = new Scanner(System.in);
    static final int listMAX = 100;
    static String[] list = new String[listMAX];
    static int cnt = 0;

    static int listSave(String s, String result){
        if (s.equals("y") || s.equals("Y")) {
            if (cnt < listMAX) {
                list[cnt] = result;
                System.out.println("저장 성공");
                cnt+=1;
                return 1;
            }
            else {
                System.out.println("리스트가 꽉 차있어 암호화 결과를 저장할 수 없습니다.");
                return 0;
            }
        }
        else if (s.equals("n") || s.equals("N")) {
            return 0;
        }

        return -1;
    }
    static void encrypt(){
        String input;
        String temp;
        String select;

        System.out.print("암호화할 텍스트를 입력하세요 >");
        input = sc.next();

        temp = aes.encrypt_AES(input);
        System.out.println("[암호화]\n" + "입력:" + input + "\n결과:" + temp);

        while(true) {
            System.out.print("암호화 결과를 리스트에 저장하시겠습니까? [y/n] >");
            select = sc.next();
            int res = listSave(select, temp);
            if (res > -1) {
                break;
            }
        }
    }
    static void decrypt(){
        String input;
        System.out.print("복호화할 텍스트를 입력하세요 >");
        input = sc.next();
        System.out.println("[복호화]\n" + "입력:" + input + "\n결과:" + aes.decrypt_AES(input));
    }
    static void listDelete(int index){
        list[index] = null;
        for(int i = index; i < cnt-1; i++) {
            list[i] = list[i+1];
        }
        cnt -= 1;
    }
    static int listEdit(int index) {
        int select;

        while (true) {
            System.out.println("1.복호화  2.삭제  3.나가기");
            System.out.print("명령 번호를 입력하세요 >");
            select = sc.nextInt();
            if (select == 1) {
                System.out.println("[복호화]\n" + "입력:" + list[index] + "\n결과:" + aes.decrypt_AES(list[index]));
            }
            else if (select == 2) {
                listDelete(index);
                break;
            }
            else if (select == 3) {
                break;
            }
        }
        return 0;
    }
    static void listSelect(){
        int number;
        int index;

        while (true) {
            listView();
            System.out.print("리스트 번호를 입력하세요 >");
            number = sc.nextInt();
            if (number > 0 && number <= cnt) {
                index = number-1;
                System.out.println("[선택 완료] " + list[index]);
                if(0 == listEdit(index) || cnt == 0) {
                    break;
                }
            }
            else {
                System.out.println("리스트에 없는 번호입니다.");
            }
        }
    }
    static void listView(){
        System.out.println("---------------------------------list---------------------------------");
        if(cnt != 0) {
            for (int i = 1; i <= cnt; i++) {
                System.out.println("  [" + i + "] " + list[i - 1]);
            }
        }
        else {
            System.out.println("                          리스트가 비었습니다.                           ");
        }
        System.out.println("----------------------------------------------------------------------");
    }
    static int list_back(String s) {
        if (s.equals("y") || s.equals("Y")) {
            return 1;
        }
        return 0;
    }
    static void list() {
        int select;

        while(true) {
            listView();
            if(cnt == 0) {
                while(true) {
                    System.out.print("나가시겠습니까?[y/n]");
                    String s = sc.next();
                    if(1 == list_back(s)) {
                        return;
                    }
                }
            }
            System.out.println("1.리스트 선택  2.나가기");
            System.out.print("번호를 입력하세요 >");
            select = sc.nextInt();
            if (select == 1) {
                listSelect();
            }
            else if (select == 2) {
                break;
            }
        }
    }
    void help() {
        System.out.println("----------------------------------------------------------");
        System.out.println("1.암호화  2.복호화  3.암호화된 리스트 보기 4.프로그램 정보  5.종료");
        System.out.println("----------------------------------------------------------");
    }
    static void information() {
        System.out.println("--------------------");
        System.out.println("Name : AES-256\n");
        System.out.println("Version : 1.0.0\n");
        System.out.println("made by GUJAHYUN");
        System.out.println("--------------------");

        while(true) {
            System.out.println("1. 나가기");
            System.out.print("번호를 입력하세요. >");
            int num = sc.nextInt();
            if(num == 1){
                break;
            }
        }
    }
    int start(int select) {
        switch (select) {
            case 1:
                encrypt();
                break;
            case 2:
                decrypt();
                break;
            case 3:
                list();
                break;
            case 4:
                information();
                break;
            case 5:
                System.out.println("프로그램을 종료중..");
                return -1;
            default:
                System.out.println("명령번호에 없는 번호있니다.");
                break;
        }
        return 0;
    }
}