package com.senla.dao;

import com.senla.entity.BankCard;

import java.util.ArrayList;
import java.util.List;

import static com.senla.dao.FileDao.readFile;

public class Parser {

    public List parseToBankCardList() {
        List bankCardInfo = new ArrayList<BankCard>();
        String fileData = readFile();
        String[] wordList = fileData.split("\\s");
        for(int i = 0; i< wordList.length; i+=4)
            bankCardInfo.add(new BankCard(wordList[i], wordList[i + 1],
                    Double.parseDouble(wordList[i+2]), Boolean.parseBoolean(wordList[i+3])));
        return bankCardInfo;
    }
}
