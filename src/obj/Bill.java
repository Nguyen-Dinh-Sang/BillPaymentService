package obj;

import common.BillState;

import java.time.LocalDate;

public class Bill {
    private final int billId;
    private String type;
    private Double amount;
    private LocalDate dueDate;
    private String state;
    private String provider;

    public Bill(int billId, String type, double amount, LocalDate dueDate, String provider) {
        this.billId = billId;
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.state = BillState.NOT_PAID.getStateValue();
        this.provider = provider;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getBillId() {
        return billId;
    }

    public String getType() {
        return type;
    }

    public String getProvider() {
        return provider;
    }

    public String getState() {
        return state;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "Bill No. " + billId + ". Type: " + type + ", Amount: " + amount +
                ", Due Date: " + dueDate + ", State: " + state + ", Provider: " + provider;
    }
}