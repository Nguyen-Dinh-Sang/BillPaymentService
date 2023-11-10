package service;

import common.BillState;
import common.PaymentState;
import obj.Bill;
import obj.Customer;
import obj.Payment;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentService {

    public void createBill(Customer customer, int billId, String type, double amount, LocalDate dueDate, String provider) {
        Bill newBill = new Bill(billId, type, amount, dueDate, provider);
        customer.addBill(newBill);
        System.out.println("Bill created successfully for customer " + customer.getCustomerId() + ".");
    }

    public void deleteBill(Customer customer, int billId) {
        Optional<Bill> billToRemove = customer.getBillList().stream().filter(bill -> bill.getBillId() == billId).findFirst();
        if (billToRemove.isPresent()) {
            Bill bill = billToRemove.get();
            customer.deleteBill(billId);
            System.out.println("Bill deleted successfully.");
        } else {
            System.out.println("Bill not found. Unable to delete.");
        }
    }

    public void updateBill(Customer customer, int billId, String type, double amount, LocalDate dueDate, String provider) {
        Optional<Bill> billToUpdate = customer.getBillList().stream().filter(bill -> bill.getBillId() == billId).findFirst();
        if (billToUpdate.isPresent()) {
            Bill bill = billToUpdate.get();
            bill.setType(type);
            bill.setAmount(amount);
            bill.setDueDate(dueDate);
            bill.setProvider(provider);
            System.out.println("Bill updated successfully.");
        } else {
            System.out.println("Bill not found. Unable to update.");
        }
    }

    public void searchBillByService(Customer customer, String serviceType) {
        List<Bill> matchingBills = customer.getBillList().stream().filter(bill -> bill.getType().equalsIgnoreCase(serviceType))
                .collect(Collectors.toList());
        if (matchingBills.isEmpty()) {
            System.out.println("No bills found for the service type: " + serviceType);
        } else {
            System.out.println("Bills for the service type " + serviceType + ":");
            for (Bill bill : matchingBills) {
                System.out.println(bill);
            }
        }
    }

    public void searchBillByProvider(Customer customer, String provider) {
        List<Bill> billsByProvider = customer.getBillList().stream()
                .filter(bill -> bill.getProvider().equalsIgnoreCase(provider))
                .collect(Collectors.toList());
        if (billsByProvider.isEmpty()) {
            System.out.println("No bills found for the provider: " + provider);
        } else {
            System.out.println("Bills for the provider " + provider + ":");
            for (Bill bill : billsByProvider) {
                System.out.println(bill);
            }
        }
    }

    public void payBill(Customer customer, int billId) {
        Optional<Bill> billToPay = customer.getBillList().stream().filter(bill -> bill.getBillId() == billId).findFirst();
        if (billToPay.isPresent()) {
            Bill bill = billToPay.get();
            if (BillState.NOT_PAID.getStateValue().equals(bill.getState()) && bill.getAmount() <= customer.getAvailableBalance()) {
                processPayment(customer, bill);
                System.out.println("Payment has been completed for Bill with id " + bill.getBillId() + ".");
            } else {
                System.out.println("Unable to pay the bill. Either it is already paid or insufficient funds.");
            }
        } else {
            System.out.println("Bill not found. Unable to process payment.");
        }
    }

    private void processPayment(Customer customer, Bill bill) {
        double paymentAmount = bill.getAmount();
        LocalDate paymentDate = LocalDate.now();
        Payment payment = new Payment(paymentAmount, paymentDate, PaymentState.PROCESSED.getStateValue(), bill.getBillId());
        customer.setAvailableBalance(customer.getAvailableBalance() - bill.getAmount());
        customer.addPayment(payment);
    }

    public void payBills(Customer customer, List<Integer> billIdList) {
        List<Integer> billIdFilterList = customer.getBillList().stream()
                .filter(bill -> billIdList.contains(bill.getBillId()))
                .sorted(Comparator.comparing(Bill::getDueDate))
                .map(Bill::getBillId)
                .collect(Collectors.toList());

        for (Integer billId : billIdFilterList) {
            payBill(customer, billId);
        }
    }

    public void viewListBill(Customer customer) {
        for (Bill bill : customer.getBillList()) {
            System.out.println(bill.toString());
        }
    }
}