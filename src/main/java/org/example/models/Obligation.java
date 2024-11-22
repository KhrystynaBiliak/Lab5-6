package org.example.models;

public class Obligation {
    protected String type;
    protected double risk;
    protected double value;
    protected int term;

    public Obligation(String type, double risk, double value, int term) {
        this.type = type;
        this.risk = risk;
        this.value = value;
        this.term = term;
    }

    public double getRisk() {
        return risk;
    }
     public void setRisk(double risk) {
        this.risk = risk;
     }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

}
