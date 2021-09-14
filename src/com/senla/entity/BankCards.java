package com.senla.entity;

import com.senla.dao.Parser;

import java.util.ArrayList;
import java.util.List;

public class BankCards {
    private static List<BankCard> bankCards= null;

    public static List<BankCard> getBankCards(){
        Parser parser = new Parser();
        if(bankCards == null)
            bankCards = parser.parseToBankCardList();
        return bankCards;
    }
}
