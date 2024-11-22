package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String healthStatus;


    private int age;
    private List<Obligation> obligations;
    private List<Policy> policies;
    public Client(String name, int age) {
        this.name = name;
        this.age = age;
        this.obligations = new ArrayList<>();
        this.policies = new ArrayList<>();
        this.healthStatus = healthStatus;
    }
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public List<Obligation> getObligations() {
        return obligations;
    }
    public List<Policy> getPolicies() {
        return policies;
    }

    public void addObligation(Obligation obligation) {
        obligations.add(obligation);
    }


    public void removeObligation(Obligation obligation) {
        obligations.remove(obligation);
    }


    public String getClientInfo() {
        return "Клієнт: " + name + ", Вік: " + age + ", Кількість зобов'язань: " + obligations.size();
    }

    @Override
    public String toString() {
        return "Клієнт{" + "ім'я='" + name + '\'' + ", вік=" + age + ", зобов'язання=" + obligations + ", поліси=" + policies + '}';
    }
}
