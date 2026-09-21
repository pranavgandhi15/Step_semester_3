package Step_semester_3.src.main.java.week7;

public class pg6 {

    static abstract class PaymentMethod {
        private static int counter = 1000;
        private final String transactionId;

        PaymentMethod() {
            transactionId = "TXN-" + (++counter);
        }

        public String getTransactionId() {
            return transactionId;
        }

        public abstract String processPayment(double amount);

        public String processPayment(double amount, String note) {
            return processPayment(amount) + " (" + note + ")";
        }
    }

    static class CreditCardPayment extends PaymentMethod {
        private String cardNumberLastFour;

        CreditCardPayment(String cardNumberLastFour) {
            this.cardNumberLastFour = cardNumberLastFour;
        }

        public String processPayment(double amount) {
            return "Charged $" + amount
                    + " to card ending " + cardNumberLastFour
                    + " - Txn " + getTransactionId();
        }
    }

    static class CashPayment extends PaymentMethod {

        public String processPayment(double amount) {
            return "Received $" + amount
                    + " in cash - Txn " + getTransactionId();
        }
    }

    static void printConfirmation(PaymentMethod payment,
                                  double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {
        CreditCardPayment cc =
                new CreditCardPayment("4471");

        System.out.println(cc.processPayment(250.0));
        System.out.println(
                cc.processPayment(250.0, "Birthday gift"));

        CashPayment cash = new CashPayment();
        System.out.println(cash.processPayment(40.0));

        PaymentMethod ref = cc;
        printConfirmation(ref, 250.0);

        // PaymentMethod p = new PaymentMethod();
        // ERROR: abstract class cannot be instantiated
    }
}
