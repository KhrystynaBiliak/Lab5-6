package org.example.commands;

import org.example.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class Menu {
    private final Map<Integer, Commands> commands;
    private final List<Policy> policies;

    public Menu() {
        commands = new HashMap<>();
        policies = new ArrayList<>();
    }

    public void initializeMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Виберіть джерело даних:");
        System.out.println("1. Введення даних з клавіатури");
        System.out.println("2. Завантаження даних з файлу");
        System.out.print("Ваш вибір: ");
        int inputChoice = scanner.nextInt();
        scanner.nextLine();
        Client client = null;
        Policy policy = null;
        Derivative derivative = null;
        List<Obligation> obligations = new ArrayList<>();
        List<Derivative> derivatives = new ArrayList<>();
        String fileName = "";


        if (inputChoice == 1) {
            // Введення з клавіатури
            System.out.print("Введіть ім'я клієнта: ");
            String clientName = scanner.nextLine();

            System.out.print("Введіть вік клієнта: ");
            int clientAge = scanner.nextInt();
            scanner.nextLine();

            client = new Client(clientName, clientAge);

            System.out.print("Введіть номер поліса: ");
            int policyNumber = scanner.nextInt();
            scanner.nextLine();

            policy = new Policy(policyNumber, client);
            derivative = new Derivative(1, "Default Derivative", client);

            System.out.print("Введіть кількість зобов'язань: ");
            int obligationCount = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < obligationCount; i++) {
                System.out.print("Введіть тип зобов'язання: ");
                String obligationType = scanner.nextLine();

                System.out.print("Введіть ризиковий коефіцієнт зобов'язання: ");
                double riskCoefficient = scanner.nextDouble();

                System.out.print("Введіть максимальну суму зобов'язання: ");
                double maxAmount = scanner.nextDouble();

                System.out.print("Введіть тривалість зобов'язання в місяцях: ");
                int duration = scanner.nextInt();
                scanner.nextLine();

                obligations.add(new Obligation(obligationType, riskCoefficient, maxAmount, duration));
            }

        } else if (inputChoice == 2) {
            // Завантаження з файлу
            System.out.print("Введіть шлях файлу для завантаження зобов'язань: ");
            fileName = scanner.nextLine();

            LoadObligationFromFile loadFromFile = new LoadObligationFromFile(fileName);
            List<Obligation> loadedObligations = loadFromFile.loadObligations();

            if (!loadedObligations.isEmpty()) {
                obligations.addAll(loadedObligations);
                System.out.println("Дані успішно завантажені.");
            } else {
                System.out.println("Не вдалося завантажити дані з файлу.");
            }
        }

        policies.add(policy);
        commands.put(1, new SearchObligation(obligations));
        commands.put(2, new SortObligations(obligations));
        commands.put(3, new ViewObligations(obligations));
        commands.put(4, new ObligationServices(policy, derivative));
        commands.put(5, new CreatePolicy(policies));
        commands.put(6, new CreateDerivative(derivatives));
        commands.put(7, new ViewAllPolicies(policies));
        commands.put(8, new ViewAllDerivatives(derivatives));
        commands.put(9, new LoadObligationFromFile(fileName));
        commands.put(10,new IAmCommand());
    }

    public void showMenu() {
        initializeMenu();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Головне меню:");
            System.out.println("1. Знайти зобов'язання");
            System.out.println("2. Сортувати зобов'язання");
            System.out.println("3. Переглянути зобов'язання");
            System.out.println("4. Менеджер зобов'язань");
            System.out.println("5. Створити поліс");
            System.out.println("6. Створити дериватив");
            System.out.println("7. Переглянути всі поліси");
            System.out.println("8. Переглянути дериватив");
            System.out.println("9. Завантажити зобов'язання з файлу");
            System.out.println("0. Вихід");
            System.out.println();
            System.out.print("Виберіть дію: ");
            choice = scanner.nextInt();

            if (commands.containsKey(choice)) {
                commands.get(choice).execute();
            } else if (choice != 0) {
                System.out.println("Невірний вибір. Спробуйте ще раз.");
            }

        } while (choice != 0);

        System.out.println("Вихід із програми.");
    }
}
