
package org.example.commands;

import org.example.models.Client;
import org.example.models.Obligation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadObligationFromFile implements Commands {
    private String fileName;

    public LoadObligationFromFile(String fileName) {
        this.fileName = fileName;
    }


    public List<Client> loadClients() {
        List<Client> clients = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            Client currentClient = null;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();


                if (line.contains(",") && line.split(",").length == 2) {

                    if (currentClient != null) {
                        clients.add(currentClient);
                    }


                    String[] clientData = line.split(",");
                    String name = clientData[0].trim();
                    int age = Integer.parseInt(clientData[1].trim());
                    currentClient = new Client(name, age);
                } else if (line.contains(",") && line.split(",").length == 4) {

                    if (currentClient != null) {
                        String[] dataParts = line.split(",");
                        String type = dataParts[0].trim();
                        double riskCoefficient = Double.parseDouble(dataParts[1].trim());
                        double maxAmount = Double.parseDouble(dataParts[2].trim());
                        int duration = Integer.parseInt(dataParts[3].trim());


                        Obligation obligation = new Obligation(type, riskCoefficient, maxAmount, duration);
                        currentClient.addObligation(obligation);
                    } else {
                        System.out.println("Неправильний формат даних: Обов'язання не прив'язане до клієнта.");
                    }
                } else {
                    System.out.println("Неправильний формат даних: " + line);
                }
            }


            if (currentClient != null) {
                clients.add(currentClient);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + fileName);
        } catch (NumberFormatException e) {
            System.out.println("Помилка форматування числових даних у файлі: " + fileName);
        }

        return clients;
    }


    public List<Obligation> loadObligations() {
        List<Obligation> obligations = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                String[] dataParts = line.split(",");


                if (dataParts.length == 4) {
                    String type = dataParts[0].trim();
                    double riskCoefficient = Double.parseDouble(dataParts[1].trim());
                    double maxAmount = Double.parseDouble(dataParts[2].trim());
                    int duration = Integer.parseInt(dataParts[3].trim());

                    Obligation obligation = new Obligation(type, riskCoefficient, maxAmount, duration);
                    obligations.add(obligation);
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + fileName);
        } catch (NumberFormatException e) {
            System.out.println("Помилка форматування числових даних у файлі: " + fileName);
        }

        return obligations;
    }


    @Override
    public void execute() {
        List<Client> clients = loadClients();
        for (Client client : clients) {
            System.out.println("Завантажено клієнта: " + client);
            for (Obligation obligation : client.getObligations()) {
                System.out.println("    Зобов'язання: " + obligation);
            }
        }
    }
}