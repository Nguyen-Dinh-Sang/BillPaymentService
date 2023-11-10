package obj;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Customer {
    private final String customerId;
    private Double availableBalance;
    private List<Bill> billList;
    private List<Payment> paymentList;

    public Customer(String customerId, double initialBalance) {
        this.customerId = customerId;
        this.availableBalance = initialBalance;
        this.billList = new ArrayList<>();
        this.paymentList = new ArrayList<>();
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void deleteBill(int billId) {
        Optional<Bill> billToRemove = billList.stream().filter(bill -> bill.getBillId() == billId).findFirst();
        if (billToRemove.isPresent()) {
            Bill bill = billToRemove.get();
            billList.remove(bill);
            System.out.println("Bill deleted successfully for customer " + customerId + ".");
        } else {
            System.out.println("Bill not found. Unable to delete.");
        }
    }

    public void addFunds(double amount) {
        if (amount > 0) {
            availableBalance += amount;
            System.out.println("Your available balance: " + availableBalance);
        } else {
            System.out.println("Invalid amount. Please enter a positive value for adding funds.");
        }
    }

    public void viewTransactionHistory() {
        if (paymentList.isEmpty()) {
            System.out.println("No transaction history available.");
        } else {
            System.out.println("Transaction History:");
            for (Payment payment : paymentList) {
                System.out.println(payment);
            }
        }
    }

    public void addPayment(Payment payment) {
        paymentList.add(payment);
    }

    public void addBill(Bill bill) {
        billList.add(bill);
        System.out.println("Bill added successfully.");
    }
}