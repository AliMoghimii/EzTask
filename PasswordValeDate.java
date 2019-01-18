import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Scanner;

import static java.lang.Character.isDigit;


class InvalidPassWordException extends Exception{
    InvalidPassWordException(String massage){
        super(massage);
    }
}

public class TestString {

    public static boolean passwordValidCheck(String password){

        int numberUpper = 0, numberLower = 0, numberDigit = 0;

        if (password.length() < 8)
            return false;


            for (int i = 0; i < password.length(); i++){

                if (isDigit(password.charAt(i))) {
                    numberDigit++;
                }

                else if ( password.charAt(i) <= 'z' && password.charAt(i) >= 'a') {
                    numberLower++;
                }

                else if ( password.charAt(i) <= 'Z' && password.charAt(i) >= 'A' ) {
                    numberUpper++;
                }

            }

        if (numberDigit == 0 || numberLower == 0 || numberUpper == 0)
            return false;
        else
            return true;
    }

    public static void main(String[] args)  throws InvalidPassWordException {

        Scanner s = new Scanner(System.in);
        String password;

        while (true){

            try {

                System.out.println("Enter password: ");
                password = s.next();

                if (!passwordValidCheck(password)) {
                    throw new InvalidPassWordException("password is invalid!");
                }
            }catch (InvalidPassWordException e){
                System.out.println(e);
            }

        }
    }
}
