package org.example.commands;

import org.example.models.*;
import java.util.Scanner;
import java.util.List;

public class ObligationServices implements Commands {

    private Policy policy;
    private Derivative derivative;

    public ObligationServices(Policy policy, Derivative derivative) {
        this.policy = policy;
        this.derivative = derivative;
    }
    private Obligation inputObligationData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть тип зобов'язання: ");
        String type = scanner.nextLine();

        System.out.print("Введіть ризиковий коефіцієнт зобов'язання: ");
        double riskCoefficient = scanner.nextDouble();

        System.out.print("Введіть максимальну суму зобов'язання: ");
        double maxAmount = scanner.nextDouble();

        System.out.print("Введіть тривалість зобов'язання в місяцях: ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        return new Obligation(type, riskCoefficient, maxAmount, duration);
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Виберіть дію: 1 - Додати зобов'язання до поліса, 2 - Видалити зобов'язання з поліса, 3 - Додати зобов'язання до дериватива, 4 - Видалити зобов'язання з дериватива");
        int action = scanner.nextInt();
        scanner.nextLine();

        Obligation obligation = null;

        System.out.println("Виберіть джерело даних для зобов'язання:");
        System.out.println("1. Введення даних з клавіатури");
        System.out.println("2. Завантаження даних з файлу");
        System.out.print("Ваш вибір: ");
        int inputChoice = scanner.nextInt();
        scanner.nextLine();

        if (inputChoice == 1) {

            obligation = inputObligationData();
        } else if (inputChoice == 2) {

            System.out.print("Введіть ім'я файлу для завантаження зобов'язань: ");
            String fileName = scanner.nextLine();
            LoadObligationFromFile loader = new LoadObligationFromFile(fileName);
            List<Obligation> obligations = loader.loadObligations(); // Загружаем зобов’язання
            if (obligations.isEmpty())
             {
                System.out.println("Файл не містить зобов'язань або не вдалося завантажити.");
                return;
            }

        }


        if (obligation != null){
            switch (action) {
                case 1:
                    policy.addObligation(obligation);
                    System.out.println("Зобов'язання додано до поліса.");
                    break;
                case 2:
                    policy.removeObligation(obligation);
                    System.out.println("Зобов'язання видалено з поліса.");
                    break;
                case 3:
                    derivative.addObligation(obligation);
                    System.out.println("Зобов'язання додано до дериватива.");
                    break;
                case 4:
                    derivative.removeObligation(obligation);
                    System.out.println("Зобов'язання видалено з дериватива.");
                    break;
                default:
                    System.out.println("Неправильний вибір дії.");
                    break;
            }
        } else {
            System.out.println("Не вдалося отримати дані зобов'язання.");
        }

    }
}
