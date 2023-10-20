package org.example;

import org.example.entities.Contract;
import org.example.entities.Installment;
import org.example.services.ContractService;
import org.example.services.OnlinePaymentService;
import org.example.services.PaypalService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre com os dados do contrato");
        System.out.print("Numero: ");
        String contractNumber = sc.nextLine();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.nextLine(), fmt);
        System.out.println("Valor do contrato: ");
        double value = sc.nextDouble();
        Contract contract = new Contract(contractNumber, date, value);

        System.out.println("Numero de parcelas: ");
        int n = sc.nextInt();
        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract, n);
        for (Installment installment : contract.getInstallments()){
            System.out.println(installment);
        }
        sc.close();

    }
}