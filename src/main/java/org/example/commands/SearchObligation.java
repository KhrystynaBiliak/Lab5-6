package org.example.commands;

import org.example.models.Obligation;

import java.util.List;
import java.util.Scanner;

public class SearchObligation  implements Commands {
    private final List<Obligation> obligations;

    public SearchObligation(List<Obligation> obligations) {
        this.obligations = obligations;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть тип зобов'язання для пошуку: ");
        String type = scanner.nextLine();

        obligations.stream()
                .filter(obligation -> obligation.getType().equalsIgnoreCase(type))
                .forEach(obligation -> System.out.println("Знайдено зобов'язання: " + obligation.getType() + " " + obligation.getRisk() + " " + obligation.getTerm() + " " + obligation.getValue()));
    }
}
