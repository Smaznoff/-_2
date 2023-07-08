import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("введите пример:");
        Scanner x = new Scanner(System.in);
        String o= String.valueOf(0);
        int oper_index = 0;
        String a = String.valueOf(0);
        String b = String.valueOf(0);
        String z = "";
        String g = "";
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+","-","/","\\*"};
        do {
            int ks=0;
            int num_index=0;
            String s = x.nextLine();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '\"') {
                    ks+=1;
                    if (ks==2) {
                        num_index=i;
                    }
                }
            }
            if ((ks != 2) && (ks != 4) && (ks!=0))  {
                throw new IOException();
            }
            g=s;
            s = s.replace("\"","");
            for (int i = 0; i < s.length(); i++) {
                if ((s.charAt(i) == '+') || (s.charAt(i) == '-') || (s.charAt(i) == '/') || (s.charAt(i) == '*')) {
                    o += 1;
                }
            }
            for (int i = 0; i < actions.length; i++) {
                if (s.contains(actions[i])) {
                    oper_index = 1;
                }
            }
            if (s.equals("exit")) {
                break;
            }
            z=String.valueOf(s.charAt(num_index));
            a=s.substring(0,num_index-1);
            b=s.substring(num_index+2,s.length());
            if ((a.length()>10) || (b.length()>10)) {
                throw new IOException();
            }
            if ((g.charAt(0)!='\"') || ((ks!=2) && ((z=="*") || (z=="/")))) {
                throw new IOException();
            }
            String k = operation(a, b, z);
            if (k=="++++") {
                throw new IOException();
            }
            System.out.println("\""+ k + "\"");
        }
        while (true) ;

    }
    private static String operation (String a, String b, String z) throws IOException {
        switch (z) {
            case "*": {
                int d=0;
                String f="";
                d = Integer.parseInt(b);
                if ((d<=0) || (d>10)) {
                    throw new IOException();
                }
                f=a.repeat(d);
                if (f.length()>40) {
                    return f.substring(0,40)+"...";
                } else {
                    return  f;
                }
            }
            case "+":
                return a + b;
            case "-":
                return a.replace(b,"");
            case "/": {
                int d=0;
                d = Integer.parseInt(b);
                if ((d<=0) || (d>10)) {
                    throw new IOException();
                }
                return a.substring(0,a.length()/d);
            }
            default:
                return "++++";
        }
    }
}