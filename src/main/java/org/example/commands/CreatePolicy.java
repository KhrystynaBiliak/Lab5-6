package org.example.commands;

import org.example.models.Client;
import org.example.models.Derivative;
import org.example.models.Policy;
import org.example.models.Obligation;
import java.io.*;
import java.util.*;


public class CreatePolicy implements Commands {
    private final List<Policy> policies;

    public CreatePolicy(List<Policy> policies) {
        this.policies = policies;
    }

    private Client parseClient(String line) {
        String[] parts = line.split(":")[1].trim().split(",");
        String name = parts[0].trim();
        int age = Integer.parseInt(parts[1].trim());
        return new Client(name, age);
    }

    private Obligation parseObligation(String line) {
        String[] parts = line.split(",");
        String type = parts[0].trim();
        double risk = Double.parseDouble(parts[1].trim());
        double maxAmount = Double.parseDouble(parts[2].trim());
        int term = Integer.parseInt(parts[3].trim());
        return new Obligation(type, risk, maxAmount, term);
    }
    private List<Policy> loadPoliciesFromFile(Scanner fileScanner) {
        List<Policy> loadedPolicies = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();

            if (line.startsWith("Client:")) {
                Client client = parseClient(line);
                Policy policy = new Policy(generatePolicyNumber(),client);
                int policyNumber = generatePolicyNumber();

                while (fileScanner.hasNextLine()) {
                    line = fileScanner.nextLine().trim();
                    if (line.isEmpty() || line.startsWith("Client:")) break;

                    try {
                        Obligation obligation = parseObligation(line);
                        policy.addObligation(obligation);
                    } catch (NumberFormatException e) {
                        System.out.println("Помилка форматування зобов'язання: " + line);
                    }
                }

                loadedPolicies.add(policy);
                System.out.println("Поліс створено для клієнта: " + client.getName());
            }
        }
        return loadedPolicies;
    }


        private int generatePolicyNumber() {
        return policies.size() + 1;
    }

    private Policy createPolicyFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть ім'я клієнта:");
        String name = scanner.nextLine();
        System.out.println("Введіть вік клієнта:");
        int age = scanner.nextInt();
        scanner.nextLine();

        Client client = new Client(name, age);
        List<Obligation> obligations = new ArrayList<>();
        System.out.println("Створюємо список зобов'язань для полісу...");

        boolean addingObligations = true;
        while (addingObligations) {
            System.out.println("Введіть тип зобов'язання:");
            String type = scanner.nextLine();
            System.out.println("Введіть ризик (у відсотках):");
            double risk = scanner.nextDouble();
            System.out.println("Введіть максимальну вартість:");
            double maxAmount = scanner.nextDouble();
            System.out.println("Введіть термін (у місяцях):");
            int term = scanner.nextInt();
            scanner.nextLine(); // Пропускаємо нову лінію

            obligations.add(new Obligation(type, risk, maxAmount, term));
            System.out.println("Бажаєте додати ще одне зобов'язання? (так/ні)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("ні")) {
                addingObligations = false;
            }

        }

        int policyNumber = generatePolicyNumber();
        Policy policy = new Policy(policyNumber, client);
        System.out.println("Поліс успішно створено для клієнта: " + client.getName());
        return policy;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Виберіть спосіб створення полісу:");
        System.out.println("1. Завантажити з файлу");
        System.out.println("2. Ввести вручну");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Пропускаємо нову лінію

        switch (choice) {
            case 1:
                System.out.print("Введіть ім'я файлу для завантаження даних клієнтів і зобов'язань: ");
                String fileName = scanner.nextLine();

                try (Scanner fileScanner = new Scanner(new File(fileName))) {
                    List<Policy> loadedPolicies = loadPoliciesFromFile(fileScanner);
                    System.out.println("Завантажено полісів: " + loadedPolicies.size());
                    policies.addAll(loadPoliciesFromFile(fileScanner));
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не знайдено: " + fileName);
                } catch (NumberFormatException e) {
                    System.out.println("Помилка форматування числових даних у файлі: " + fileName);
                }
                break;
            case 2:
                Policy newPolicy = createPolicyFromInput();
                policies.add(newPolicy);
                break;
            default:
                System.out.println("Неправильний вибір. Будь ласка, спробуйте ще раз.");
                break;
        }
    }
}
