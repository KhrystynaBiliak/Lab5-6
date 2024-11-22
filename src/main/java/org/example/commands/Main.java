package org.example.commands;

import org.example.models.Client;
import org.example.models.Obligation;

import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.showMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до файлу із зобов'язаннями: ");
        String filePath = scanner.nextLine();



        LoadObligationFromFile loader = new LoadObligationFromFile(filePath);
        List<Client> obligations = loader.loadClients();
        for (Client client : obligations) {
            System.out.println(client.getClientInfo());
            for (Obligation obligation : client.getObligations()) {
                System.out.println("    Зобов'язання: " + obligation);
            }
        }
    }
}