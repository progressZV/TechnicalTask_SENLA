package com.senla.dao;

import java.io.*;


public class FileDao {
    private static String path = "data.txt";

    public FileDao(String path) {
        this.path = path;
    }

    public static String readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String str;
            StringBuilder fileData = new StringBuilder();
            while ((str = br.readLine()) != null)
                fileData.append(str).append(" ");
            if (fileData.isEmpty()) {
                return "Dont have any information in file.";
            }
            return fileData.toString();
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }

    public static void writeFile(String str){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write(str);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}