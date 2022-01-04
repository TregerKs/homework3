package com.kseniya.homework3;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String addressFile;
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите адрес файла");
            addressFile = scanner.nextLine().trim();

        } else {
            addressFile = args[0];
        }

        Scanner scannerFile;
        try {
            scannerFile = new Scanner(new File(addressFile));
            Path pathToFile = Paths.get(String.valueOf(scannerFile));
            addressFile = pathToFile.toAbsolutePath().toString();
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + addressFile + " не найден");
            return;
        }
        scannerFile.useDelimiter("[\n\r\s\t,!.:;?]+");
        List<String> list = new ArrayList<>();
        while (scannerFile.hasNext()) {
            list.add(scannerFile.next().toLowerCase(Locale.ROOT));
        }
        if (list.size() == 0) {
            System.out.println("В файле нет ни одного слова");
            return;
        }
        Collections.sort(list);
        System.out.println(list);

        Map<String, Integer> countWords = new TreeMap<>();

        for (int i = 0; i < list.size(); i++) {
            if (countWords.containsKey(list.get(i))) {
                Integer count = countWords.get(list.get(i));
                countWords.put(list.get(i), count + 1);
            } else {
                countWords.put(list.get(i), 1);
            }
        }
        for (Map.Entry<String, Integer> entry : countWords.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            float periodicity = (float) value / (float) list.size() * 100;
            System.out.println(key + " занимает " + periodicity + " % в карте");
        }

        Integer max = countWords.entrySet().iterator().next().getValue();
        for (Map.Entry<String, Integer> entry : countWords.entrySet()) {
            if (max < entry.getValue()) {
                max = entry.getValue();
            }
        }

        for (Map.Entry<String, Integer> entry : countWords.entrySet()) {
            if (max.equals(entry.getValue())) {
                System.out.println(entry.getKey() + " встречается чаще остальных, целых " + max + " раз");
            }
        }
    }
}
