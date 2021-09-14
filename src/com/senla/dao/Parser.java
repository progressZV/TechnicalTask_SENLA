package com.senla.dao;

import com.senla.entity.BankCard;

import java.util.ArrayList;
import java.util.List;

import static com.senla.dao.FileDao.readFile;

public class Parser {

    public List<BankCard> parseToBankCardList() {
        List<BankCard> bankCardInfo = new ArrayList<>();
        String fileData = readFile();
        String[] wordList = fileData.split("\\s");
        for (int i = 0; i < wordList.length; i += 5) {
                bankCardInfo.add(new BankCard(wordList[i], wordList[i + 1],
                        Double.parseDouble(wordList[i + 2]), Long.parseLong(wordList[i + 3]), Boolean.parseBoolean(wordList[i + 4])));
        }
        return bankCardInfo;
    }
}
