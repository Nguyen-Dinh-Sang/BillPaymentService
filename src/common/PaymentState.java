package common;

public enum PaymentState {
    PROCESSED("PROCESSED"),
    PENDING("PENDING");
    private final String stateValue;

    PaymentState(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateValue() {
        return stateValue;
    }

    public static PaymentState getPaymentState(String stateValue) {
        for (PaymentState billState : values()) {
            if (billState.stateValue.equals(stateValue)) {
                return billState;
            }
        }

        return null;
    }
}
