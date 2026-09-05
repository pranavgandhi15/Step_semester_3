package Step_semester_3.src.main.java.week2;

import java.util.*;

public class ATMPINValidator {

    static void checkPinLength(String pin) {

        int length = pin.length();

        if (length == 4)
            System.out.println("PIN length OK");
        else
            System.out.println("Invalid PIN — must be exactly 4 digits.");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        checkPinLength(pin);
    }
}
