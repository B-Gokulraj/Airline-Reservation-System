package service;

public class PaymentService {

// Simulates processing a UPI payment
public boolean processUPIPayment(String upiId) {
    // Simulate basic validation for UPI ID
    if (upiId == null || upiId.trim().isEmpty()) {
        System.out.println("UPI ID cannot be empty.");
        return false;
    }

    // Simulate successful UPI payment
    System.out.println("UPI Payment successful for UPI ID: " + upiId);
    return true;
}

// Simulates processing a Card payment
public boolean processCardPayment(String cardNumber, String cardHolderName, String cvv) {
    // Validate card number
    if (cardNumber == null || cardNumber.length() != 16 || !cardNumber.matches("\\d+")) {
        System.out.println("Invalid card number.");
        return false;
    }

    // Validate card holder name
    if (cardHolderName == null || cardHolderName.trim().isEmpty()) {
        System.out.println("Card holder name cannot be empty.");
        return false;
    }

    // Validate CVV
    if (cvv == null || cvv.length() != 3 || !cvv.matches("\\d+")) {
        System.out.println("Invalid CVV.");
        return false;
    }

    // Simulate successful Card payment
    System.out.println("Card Payment successful for card number: " + cardNumber);
    return true;
}

}