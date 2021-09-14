package com.senla.services;

import com.senla.console.actions.CheckCardDataInput;
import com.senla.dao.FileDao;
import com.senla.dao.Parser;
import com.senla.entity.BankCard;
import com.senla.entity.BankCards;

import java.util.List;
import java.util.Scanner;


public class BankCardService {

    final double LIMIT = 1000000;


    CheckCardDataInput checkCardDataInput = new CheckCardDataInput();


    public void getAccess(String userValidNumber, String userPinCode, List<BankCard> bankCardInfo) {
        final String cardRegex = "\\d{4}([-]\\d{4}){3}";
        final String pinRegex = "\\d{4}";
        boolean check = false;
        int counts = 3;

        if (userValidNumber.matches(cardRegex) && userPinCode.matches(pinRegex)) {
            System.out.println("Validation is correct, Card number and pin-code are being checked.");
        } else {
            System.out.println("Invalid input. Check that the entry is correct");
        //    checkCardDataInput.getCounter(--counts);
            checkCardDataInput.execute();
        }
        if (counts == 0) {
            System.out.println("ENOUGH");
        }

        for (BankCard bankCard : bankCardInfo) {
            if (bankCard.getValidNumber().equals(userValidNumber) && bankCard.getPinCode().equals(userPinCode)) {
                System.out.println("The check has been successfully passed. All data is correct.");
                bankCardInfo.set(bankCardInfo.indexOf(bankCard), bankCardInfo.get(0));
                bankCardInfo.set(0, bankCard);
                check = true;
            }
        }

        if (!check) {
            System.out.println("Incorrect number or pin-code entered");
            --counts;
            checkCardDataInput.execute();
        }
    }

    public void checkBalance(List<BankCard> bankCardInfo) {
        BankCard currentBankCard = bankCardInfo.get(0);
        double moneyCount = currentBankCard.getBalance();
        System.out.println("The balance is " + moneyCount);
    }

    public void cashOut(List<BankCard> bankCardInfo) {
        Scanner in = new Scanner(System.in);
        BankCard currentBankCard = bankCardInfo.get(0);
        double moneyCount = currentBankCard.getBalance();
        double answer;

        do {
            System.out.println("Specify the amount to be withdrawn.");
            while (!in.hasNextDouble()) {
                in.next();
                System.out.println("Invalid input.");
            }
            answer = in.nextDouble();
        } while (answer <= 0);

        if (answer <= moneyCount) {
            currentBankCard.setBalance(moneyCount - answer);
            //    fileDao.replaceLastWord(String.valueOf(account.getBalance()));
            bankCardInfo.set(0, currentBankCard);
            System.out.println("The amount has been successfully withdrawn from your account.");
        } else {
            System.out.println("Not enough money on the account.");
        }
    }

    public void topUpBalance(List<BankCard> bankCardInfo) {
        Scanner in = new Scanner(System.in);
        BankCard currentBankCard = bankCardInfo.get(0);

        double moneyCount = currentBankCard.getBalance();
        double answer;
        do {
            System.out.println("Specify the correct recharge amount.");
            while (!in.hasNextDouble()) {
                in.next();
                System.out.println("Invalid input. Specify the correct recharge amount.");
            }
            answer = in.nextDouble();
        } while (answer <= 0);

        if (answer <= LIMIT && answer > 0) {
            currentBankCard.setBalance(moneyCount + answer);
            bankCardInfo.set(0, currentBankCard);
            //     fileDao.replaceLastWord(String.valueOf(account.getBalance()));
            System.out.println("The balance has been successfully replenished.");
        } else if (answer > LIMIT) {
            System.out.println("The amount of the replenishment must not exceed " + moneyCount);
        } else {
            System.out.println("The amount of the replenishment must be greater than 0.");
        }
    }

    public void saveBankCardsHistory() {
        StringBuilder sb = new StringBuilder();
        List<BankCard> bankCardInfo = BankCards.getBankCards();
        for (BankCard bankCard : bankCardInfo) {
            sb.append(bankCard.convertToString()).append(" ");
        }
        FileDao.writeFile(sb.toString());
    }
}
