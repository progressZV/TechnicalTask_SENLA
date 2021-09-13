package com.senla.services;

import com.senla.console.actions.CheckCardDataInput;
import com.senla.dao.FileDao;
import com.senla.dao.Parser;
import com.senla.entity.BankCard;

import java.util.List;
import java.util.Scanner;


public class BankCardService {

    final double LIMIT = 1000000;


    CheckCardDataInput checkCardDataInput = new CheckCardDataInput();
    Parser parser = new Parser();

    List<BankCard> bankCardInfo = parser.parseToBankCardList();

    public void getAccess(String userValidNumber, String userPinCode) {
        final String cardRegex = "\\d{4}([-]\\d{4}){3}";
        final String pinRegex = "\\d{4}";
        boolean check = false;


        if (userValidNumber.matches(cardRegex) && userPinCode.matches(pinRegex)) {
            System.out.println("Validation is correct, Card number and pin-code are being checked.");
        } else {
            System.out.println("Invalid input. Check that the entry is correct");
            checkCardDataInput.execute();
            return;
        }

        for (BankCard bankCard : bankCardInfo) {
            if (bankCard.getValidNumber().equals(userValidNumber) && bankCard.getPinCode().equals(userPinCode)) {
                System.out.println("The check has been successfully passed. All data is correct.");
                check = true;
            }
        }

        if (!check) {
            System.out.println("Incorrect number or pin-code entered");
            checkCardDataInput.execute();
            return;
        }
    }

        public void checkBalance() {
        List<BankCard> bankCards = parser.parseToBankCardList();
            double moneyCount = bankCard.getBalance();
            System.out.println("The balance is " + moneyCount);
        }

        public void cashOut() {
            Scanner in = new Scanner(System.in);
     //       double moneyCount = bankCard.getBalance();
            double answer;

            do {
                System.out.println("Specify the amount to be withdrawn.");
                while(!in.hasNextDouble()) {
                    in.next();
                    System.out.println("Invalid input.");
                }
                answer = in.nextDouble();
            } while(answer <= 0);

        /*    if (answer <= moneyCount) {
                bankCard.setBalance(moneyCount - answer);
                //    fileDao.replaceLastWord(String.valueOf(account.getBalance()));
                System.out.println("The amount has been successfully withdrawn from your account.");
            } else {
                System.out.println("Not enough money on the account.");
            }*/
        }

        public void topUpBalance() {
            Scanner in = new Scanner(System.in);
        //    double moneyCount = bankCard.getBalance();
            double answer;
            do {
                System.out.println("Specify the correct recharge amount.");
                while(!in.hasNextDouble()) {
                    in.next();
                    System.out.println("Invalid input. Specify the correct recharge amount.");
                }
                answer = in.nextDouble();
            } while(answer <= 0);

            if (answer <= LIMIT && answer > 0) {
          //      bankCard.setBalance(moneyCount + answer);
                //     fileDao.replaceLastWord(String.valueOf(account.getBalance()));
                System.out.println("The balance has been successfully replenished.");
            } else if (answer > LIMIT) {
            //    System.out.println("The amount of the replenishment must not exceed " + moneyCount);
            } else {
                System.out.println("The amount of the replenishment must be greater than 0.");
            }
        }

        private BankCard getCurrentBankCard(String userValidNumber){
        String userValidNunmber =
            List<BankCard> bankCards = parser.parseToBankCardList();
            BankCard currentBankCard = bankCards.stream().filter(b -> b.getValidNumber().equals(userValidNumber)).findFirst().orElse(null);
            if(currentBankCard != null){
                return currentBankCard;
            }
            else{
                System.out.println("Can't find current BankCard.");
                return null;
            }

        }

}
