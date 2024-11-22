package org.example.commands;

import org.example.models.Policy;
import org.example.models.Obligation;
import java.util.List;

public class ViewAllPolicies  implements Commands {
    private final List<Policy> policies;

    public ViewAllPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    @Override
    public void execute() {
        if (policies.isEmpty()) {
            System.out.println("Немає створених полісів для перегляду.");
        } else {
            System.out.println("Список всіх створених полісів:");
            for (Policy policy : policies) {
                String clientName = policy.getClient().getName();
                System.out.println("Поліс №" + policy.getPolicyNumber() + " для клієнта: " + policy.getClient().getName());


                System.out.println("Зобов'язання, які входять у цей поліс:");
                List<Obligation> obligations = policy.getObligations();

                if (obligations.isEmpty()) {
                    System.out.println("  Немає зобов'язань.");
                } else {

                    for (Obligation obligation : obligations) {
                        System.out.println("  Тип: " + obligation.getType());
                        System.out.println("  Ризик: " + obligation.getRisk());
                        System.out.println("  Вартість: " + obligation.getValue());
                        System.out.println("  Термін: " + obligation.getTerm() + " місяців");
                        System.out.println("-----------------------");

                        System.out.println("Загальна вартість полісу: " + policy.calculateTotalValue());
                    }
                    System.out.println("===================================");
                    }

                }
            }
        }
    }
