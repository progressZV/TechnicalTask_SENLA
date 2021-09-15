package com.senla.services;

import com.senla.console.actions.CheckCardDataInput;
import com.senla.dao.FileDao;
import com.senla.entity.BankCard;
import com.senla.entity.BankCards;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class BankCardService {

    final double LIMIT = 1000000;
    static int counts = 2;
    static String previousValidNumber = "";
    static long diffInHours = 0;

    CheckCardDataInput checkCardDataInput = new CheckCardDataInput();


    public void getAccess(String userValidNumber, String userPinCode, List<BankCard> bankCardInfo) {
        final String cardRegex = "\\d{4}([-]\\d{4}){3}";


        if (userValidNumber.matches(cardRegex)) {
            System.out.println("Validation number is correct, Card number is being checked.");
        }


        BankCard bankCard = bankCardInfo.stream().filter(bk -> bk.getValidNumber().equals(userValidNumber) &&
                bk.getPinCode().equals(userPinCode)).findFirst().orElse(null);

        if (counts == 0 && bankCard == null) {
            blockUserCard(userValidNumber, bankCardInfo);
            System.out.println("Your card is blocking now. Try it again after 1 day.");
            System.exit(0);
        }

        if (!previousValidNumber.equals(userValidNumber)) {
            counts = 2;
        }
        previousValidNumber = userValidNumber;

        if(bankCard!= null) {

                boolean status = isLocked(userValidNumber, userPinCode, bankCardInfo);

                if (status) {
                    System.out.println("Your card is still blocked. Try later, after " + diffInHours + " hour(s)");
                    System.exit(0);
                }

                System.out.println("The check has been successfully passed. All data is correct.");
                --counts;
                bankCardInfo.set(bankCardInfo.indexOf(bankCard), bankCardInfo.get(0));
                bankCardInfo.set(0, bankCard);
            }
            else {
                    --counts;

                    checkCardDataInput.execute();
            }


      //  return;
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

    private void blockUserCard(String userValidNumber, List<BankCard> bankCardInfo) {

        Date currentDate = new Date();
        long currentDateTime = currentDate.getTime();
        long currentDateInHours = (currentDateTime / (60 * 60 * 1000));


        for (BankCard bankCard : bankCardInfo) {
            if (bankCard.getValidNumber().equals(userValidNumber)) {
                bankCard.setStatus(false);
                bankCard.setBlockDate(currentDateInHours);

            }
        }
        saveBankCardsHistory();
    }

    private Boolean isLocked(String userValidNumber, String userPinCode, List<BankCard> bankCardInfo) {

            for (BankCard bankCard : bankCardInfo) {
                if (bankCard.getValidNumber().equals(userValidNumber) && bankCard.getPinCode().equals(userPinCode)) {
                    if (bankCard.getStatus()) {
                        return false;
                    } else {
                        Date currentDate = new Date();
                        long currentDateTime = currentDate.getTime();
                        long currentDateInHours = (currentDateTime / (60 * 60 * 1000));

                        long timeOfCardBlockingInHours = bankCard.getBlockDate();

                        diffInHours = currentDateInHours - timeOfCardBlockingInHours;
                        if (diffInHours >= 24) {
                            bankCard.setStatus(true);
                            bankCard.setBlockDate(0);
                            return false;
                        }
                    }
                }
            }
        return true;
    }

}
