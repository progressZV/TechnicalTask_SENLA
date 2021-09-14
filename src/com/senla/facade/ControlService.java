package com.senla.facade;

import com.senla.dao.FileDao;
import com.senla.entity.BankCard;
import com.senla.entity.BankCards;
import com.senla.services.BankCardService;

import java.text.ParseException;
import java.util.List;

public class ControlService
{
    private final BankCardService bankCardService = new BankCardService();


    public void checkCard(String userValidNumber, String userPinCode)  {
            bankCardService.getAccess(userValidNumber, userPinCode, BankCards.getBankCards());
    }

    public void checkBalance(){
        bankCardService.checkBalance(BankCards.getBankCards());
    }

    public void cashOut(){
        bankCardService.cashOut(BankCards.getBankCards());
    }

    public void topUpBalance(){
        bankCardService.topUpBalance(BankCards.getBankCards());
    }

    public void exitApp(){
        bankCardService.saveBankCardsHistory();
        System.out.println("Process terminated.");
        System.exit(0);
    }

}
