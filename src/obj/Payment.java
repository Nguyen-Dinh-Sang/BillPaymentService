package obj;

import java.time.LocalDate;

public class Payment {
    private final double amount;
    private final LocalDate paymentDate;
    private String state;
    private final int billId;

    public Payment(double amount, LocalDate paymentDate, String state, int billId) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.state = state;
        this.billId = billId;
    }

    @Override
    public String toString() {
        return "Amount: " + amount + ", Date: " + paymentDate + ", State: " + state + ", Bill Id: " + billId;
    }
}
