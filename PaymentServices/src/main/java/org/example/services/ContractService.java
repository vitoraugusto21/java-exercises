package org.example.services;

import org.example.entities.Contract;
import org.example.entities.Installment;

import java.time.LocalDate;

public class ContractService{
    private OnlinePaymentService paymentService;

    public ContractService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processContract(Contract contract, Integer months){
        for( int i = 1; i <= months; i++){
            double installmentValue = (contract.getTotalValue()/months);
            double interest = paymentService.interest(installmentValue, i);
            double fee = paymentService.paymentFee(installmentValue + interest);
            installmentValue += interest + fee;

            contract.getInstallments().add(new Installment(contract.getDate().plusMonths(i), installmentValue));
        }
    }
}
