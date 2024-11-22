package org.example.models;

public class LifeInsuranceObligation extends Obligation {
    private int age;
    private double insuranceAmount;

    public LifeInsuranceObligation(String type, double risk, double value, int term, int age, double insuranceAmount) {
        super(type, risk, value, term);
        this.age = age;

        this.insuranceAmount = insuranceAmount;
    }

    public double calculateLifeInsurance() {

        double ageRiskFactor = age > 50 ? 1.2 : 1.0;
        double lifeInsuranceCost = insuranceAmount * risk  * ageRiskFactor;
        return lifeInsuranceCost;
    }
}
