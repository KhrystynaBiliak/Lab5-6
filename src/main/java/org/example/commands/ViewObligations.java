package org.example.commands;

import org.example.models.Obligation;

import java.util.List;

public class ViewObligations  implements Commands {
    private final List<Obligation> obligations;

    public ViewObligations(List<Obligation> obligations) {
        this.obligations = obligations;
    }

    public void execute() {
        System.out.println("Список зобов'язань:");
        obligations.forEach(obligation -> System.out.println(obligation.getType() + " - " + obligation.getRisk() + " " + obligation.getTerm() + " " + obligation.getValue()));
    }
}
