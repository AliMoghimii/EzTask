
package com.company;

        import java.util.Arrays;
        import java.util.Scanner;

        import static java.lang.Character.isDigit;

public class EmailTest {
    public static void main(String[] args) {
        Scanner Scan = new Scanner(System.in);

        boolean keepDoing = true;

        while(keepDoing)
        {
            System.out.println("Enter Email");
            int counterAt = 0;
            String Email = Scan.next();
//-------------------------------------------------first checker //has @ : aliyahoo.com
            if (!Email.contains("@")) {
                System.out.println("Invalid Email1 - has to have @");
                break;
            }
//-------------------------------------------------second checker //2 @'s is wrong : al@i@yahoo.com
            for (int i = 0; i < Email.length(); i++) {
                if (Email.charAt(i) == '@')
                    counterAt++;
                if (counterAt >= 2) {
                    System.out.println("Invalid Email2 - 2 @'s is wrong");
                    break;
                }

            }
//-------------------------------------------------third checker //starts with number : 1ali@yahoo.com
            if (isDigit(Email.charAt(0))) {
                System.out.println("Invalid Email3 - shouldn't start with number");
                break;
            }
//-------------------------------------------------fourth checker //domain has a number : ali@yahoo.com1
            String[] Email1 = Email.split("@");
            String[] Email2 = Email1[1].split("[.]");

            for (int i = 0; i < Email2[1].length() ; i++) {

                if ((isDigit(Email2[1].charAt(i)))) {
                    System.out.println("Invalid Email4 - address shouldn't have a number");
                    break;
                }
            }

        }
    }

}
