package org.example.models;

public class PropertyInsuranceObligation extends Obligation{
    private final double propertyCost;


    public PropertyInsuranceObligation(String type, double risk, double value, int term, double propertyCost) {
        super(type, risk, value, term);
        this.propertyCost = propertyCost;

    }

    public double calculateInsuranceCost() {
        return propertyCost * risk ;
    }
}
