package common;

public enum BillType {
    ELECTRIC("ELECTRIC"),
    WATER("WATER"),
    INTERNET("INTERNET");
    private final String stateValue;

    BillType(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateValue() {
        return stateValue;
    }

    public static BillType getBillType(String stateValue) {
        for (BillType billState : values()) {
            if (billState.stateValue.equals(stateValue)) {
                return billState;
            }
        }

        return null;
    }
}
