package common;

public enum BillState {
    NOT_PAID("NOT_PAID"),
    PAID("PAID");
    private final String stateValue;

    BillState(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateValue() {
        return stateValue;
    }

    public static BillState getBillState(String stateValue) {
        for (BillState billState : values()) {
            if (billState.stateValue.equals(stateValue)) {
                return billState;
            }
        }

        return null;
    }
}
