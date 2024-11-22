package org.example.models;
import java.util.List;
import java.util.ArrayList;

public class Policy {
    private final int policyNumber;
    private final Client client;
    private final List<Obligation> obligations;

    public Policy( int policyNumber, Client client) {
        this.policyNumber = policyNumber;
        this.client = client;
        this.obligations = new ArrayList<Obligation>();
    }

    public int getPolicyNumber() {
        return policyNumber;
    }

    public Client getClient() {
        return client;
    }

    public List<Obligation> getObligations() {
        return obligations;
    }

    public void addObligation(Obligation obligation) {
        obligations.add(obligation);
    }

    public void removeObligation(Obligation obligation) {
        obligations.remove(obligation);
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        for (Obligation obligation : obligations) {
            totalValue += obligation.value;
        }
        return totalValue;
    }

}
