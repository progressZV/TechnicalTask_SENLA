package com.senla.facade;

import com.senla.dao.FileDao;
import com.senla.entity.BankCard;
import com.senla.services.BankCardService;

import java.util.List;

public class ControlService
{
    private BankCardService bankCardService = new BankCardService();


    public void checkCard(String userValidNumber, String userPinCode){
            bankCardService.getAccess(userValidNumber, userPinCode);
    }

    public void checkBalance(){
        bankCardService.checkBalance();
    }

    public void cashOut(){
        bankCardService.cashOut();
    }

    public void topUpBalance(){
        bankCardService.topUpBalance();
    }

    public void exitApp(){
/*        StringBuilder sb = new StringBuilder();
        List<BankCard> bankCardInfo = bankCardService.convertToString();
        for(BankCard bankCard : bankCardInfo){
           sb = sb.append(bankCard.convertToString()).append(" ");
        }
        FileDao.writeFile(sb.toString());*/
        System.out.println("Process terminated.");
        System.exit(0);
    }

}
