package org.example.commands;

import org.example.models.Obligation;

import java.util.Collections;
import java.util.List;

public class SortObligations implements Commands {
    private  final List<Obligation> obligations;

    public SortObligations(List<Obligation> obligations) {
        this.obligations = obligations;
    }

    public void execute() {
        Collections.sort(obligations, (o1, o2) -> Double.compare(o1.getRisk(), o2.getRisk()));
        System.out.println("Зобов'язання відсортовано за ризиком:");
        obligations.forEach(obligation -> System.out.println(obligation.getRisk() + " "  + obligation.getType()));
    }
}
