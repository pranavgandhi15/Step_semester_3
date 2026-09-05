package Step_semester_3.src.main.java.week2;

import java.util.*;

public class ProductInventoryCSVParser {

    static void parseInventoryRecord(String csvLine) {

        String[] parts = csvLine.split(",");

        if (parts.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.println("Product: " + parts[0] +
                    " | SKU: " + parts[1] +
                    " | Qty: " + parts[2]);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter inventory record: ");
        String csvLine = sc.nextLine();

        parseInventoryRecord(csvLine);
    }
}
