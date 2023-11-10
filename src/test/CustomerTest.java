package test;

import common.PaymentState;
import obj.Customer;
import obj.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @Test
    public void addFundsPositiveAmount() {
        Customer customer = new Customer("123", 1000.0);
        customer.addFunds(500.0);
        assertEquals(Double.valueOf(1500.0), customer.getAvailableBalance());
    }

    @Test
    public void addFunds_negativeAmount() {
        Customer customer = new Customer("123", 1000.0);
        customer.addFunds(-500.0);
        assertEquals(Double.valueOf(1000.0), customer.getAvailableBalance());
    }

    @Test
    public void viewTransactionHistoryEmptyHistory() {
        Customer customer = new Customer("123", 1000.0);
        customer.viewTransactionHistory();
    }

    @Test
    public void viewTransactionHistoryNotEmptyHistory() {
        Customer customer = new Customer("123", 1000.0);
        LocalDate paymentDate = LocalDate.of(2023, 1, 1);

        Payment payment1 = new Payment(500.0, paymentDate, PaymentState.PENDING.getStateValue(), 1);
        Payment payment2 = new Payment(300.0, paymentDate.plusDays(1), PaymentState.PROCESSED.getStateValue(),
                2);

        customer.addPayment(payment1);
        customer.addPayment(payment2);

        customer.viewTransactionHistory();

        String expectedOutput = "Transaction History:" + System.lineSeparator() +
                "Amount: 500.0, Date: 2023-01-01, State: PENDING, Bill Id: 1" + System.lineSeparator() +
                "Amount: 300.0, Date: 2023-01-02, State: PROCESSED, Bill Id: 2" + System.lineSeparator();

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}