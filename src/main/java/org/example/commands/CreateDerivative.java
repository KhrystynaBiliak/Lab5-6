package org.example.commands;

import org.example.models.Client;
import org.example.models.Derivative;
import org.example.models.Obligation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateDerivative implements Commands {
    private List<Derivative> derivatives;

    public CreateDerivative(List<Derivative> derivatives) {
        this.derivatives = derivatives;
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

    private List<Derivative> loadDerivativesFromFile(Scanner fileScanner) {
        List<Derivative> loadedDerivatives = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();

            if (line.startsWith("Client:")) {
                Client client = parseClient(line);

                Derivative derivative = new Derivative(generateDerivativeId(), "Default Derivative", client);

                while (fileScanner.hasNextLine()) {
                    line = fileScanner.nextLine().trim();
                    if (line.isEmpty() || line.startsWith("Client:")) break;

                    try {
                        Obligation obligation = parseObligation(line);
                        derivative.addObligation(obligation);
                    } catch (NumberFormatException e) {
                        System.out.println("Помилка форматування зобов'язання: " + line);
                    }
                }

                loadedDerivatives.add(derivative);
                System.out.println("Дериватив створено для клієнта: " + client.getName());
            }
        }
        return loadedDerivatives;
    }

    private Derivative createDerivativeFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть ім'я клієнта:");
        String name = scanner.nextLine();
        System.out.println("Введіть вік клієнта:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Пропустити нову лінію

        Client client = new Client(name, age);
        Derivative derivative = new Derivative(generateDerivativeId(), "Manual Derivative", client);

        boolean addingObligations = true;
        System.out.println("Створюємо список зобов'язань для деривативу...");

        while (addingObligations) {
            System.out.println("Введіть тип зобов'язання:");
            String type = scanner.nextLine();
            System.out.println("Введіть ризик (у відсотках):");
            double risk = scanner.nextDouble();
            System.out.println("Введіть максимальну вартість:");
            double maxAmount = scanner.nextDouble();
            System.out.println("Введіть термін (у місяцях):");
            int term = scanner.nextInt();
            scanner.nextLine(); // Пропустити нову лінію

            Obligation obligation = new Obligation(type, risk, maxAmount, term);
            derivative.addObligation(obligation);

            System.out.println("Бажаєте додати ще одне зобов'язання? (так/ні)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("ні")) {
                addingObligations = false;
            }
        }

        System.out.println("Дериватив успішно створено для клієнта: " + client.getName());
        return derivative;
    }

    private int generateDerivativeId() {
        return derivatives.size() + 1;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Виберіть спосіб створення деривативу:");
        System.out.println("1. Завантажити з файлу");
        System.out.println("2. Ввести вручну");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Введіть ім'я файлу для завантаження даних клієнтів і зобов'язань: ");
                String fileName = scanner.nextLine();

                try (Scanner fileScanner = new Scanner(new File(fileName))) {
                    derivatives.addAll(loadDerivativesFromFile(fileScanner));
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не знайдено: " + fileName);
                } catch (NumberFormatException e) {
                    System.out.println("Помилка форматування числових даних у файлі: " + fileName);
                }
                break;
            case 2:
                Derivative newDerivative = createDerivativeFromInput();
                derivatives.add(newDerivative);
                break;
            default:
                System.out.println("Неправильний вибір. Будь ласка, спробуйте ще раз.");
                break;
        }
    }
}
