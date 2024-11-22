package org.example.models;


import java.util.ArrayList;
import java.util.List;

public class Derivative {
    private static int currentDerivativeCount = 0;
    private final int id;
    private String name;
    private List<Obligation> obligations;
    private double totalValue;
    private Client client;



    public Derivative(int id, String name, Client client) {
        this.id = currentDerivativeCount ++;
        this.name = "Derivative " + this.id;
        this.client = client;
        this.obligations = new ArrayList<Obligation>();
    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }
    public void setName(String name) {

        this.name = name;
    }
    public Client getClient() {

        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public void addObligation(Obligation obligation) {

        obligations.add(obligation);
    }

    public void removeObligation(Obligation obligation) {

        obligations.remove(obligation);
    }

    public double calculateTotalValue() {
        totalValue = 0.0;
        for (Obligation obligation : obligations) {
            totalValue += obligation.getValue();
        }
        return totalValue;
    }

    public List<Obligation> getObligations() {

        return obligations;
    }

    public static void resetDerivativeCounter() {
        currentDerivativeCount = 0;
    }
    public static void initializeCounter(int currentCount) {
        currentDerivativeCount = currentCount;
    }
}
