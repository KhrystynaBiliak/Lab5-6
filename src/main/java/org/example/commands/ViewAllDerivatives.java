package org.example.commands;

import org.example.models.Derivative;
import org.example.models.Obligation;
import java.util.List;

public class ViewAllDerivatives implements Commands {
    private final List<Derivative> derivatives;

    public ViewAllDerivatives(List<Derivative> derivatives) {
        this.derivatives = derivatives;
    }

    @Override
    public void execute() {
        if (derivatives.isEmpty()) {
            System.out.println("Немає створених деривативів для перегляду.");
        } else {
            System.out.println("Список всіх створених деривативів:");

            for (Derivative derivative : derivatives) {
                System.out.println("Дериватив: " + derivative.getName());
                System.out.println("Клієнт: " + derivative.getClient().getName());
                System.out.println("Зобов'язання, які входять у цей дериватив:");

                List<Obligation> obligations = derivative.getObligations();
                if (obligations.isEmpty()) {
                    System.out.println("  Немає зобов'язань.");
                } else {
                    for (Obligation obligation : obligations) {
                        System.out.println("  Тип: " + obligation.getType());
                        System.out.println("  Ризик: " + obligation.getRisk() + "%");
                        System.out.println("  Максимальна вартість: " + obligation.getValue());
                        System.out.println("  Термін: " + obligation.getTerm() + " місяців");
                        System.out.println("-----------------------");
                    }
                }

                System.out.println("Загальна вартість деривативу: " + derivative.calculateTotalValue());
                System.out.println("===================================");
            }
        }
    }
}
