package org.example.models;

public class FreightObligation extends Obligation {private String freightType;
    private double freightWeight;
    private double minInsuranceValue;
    private double maxInsuranceValue;

    public FreightObligation(String type, double risk, double value, int term, String freightType, double freightWeight, double minInsuranceValue, double maxInsuranceValue) {
        super(type, risk, value, term);
        this.freightType = freightType;
        this.freightWeight = freightWeight;
        this.minInsuranceValue = minInsuranceValue;
        this.maxInsuranceValue = maxInsuranceValue;
    }

    public double calculateCompensation() {
        double compensation = Math.min(maxInsuranceValue, Math.max(minInsuranceValue, freightWeight * risk * 100));
        return compensation;
    }
}
