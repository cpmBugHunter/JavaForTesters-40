package ru.pft40.bugHunter.Iterations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Python", "Javascript"};
        itemizeArray(langs);
        List<String> languages = new ArrayList<>(Arrays.asList("Java", "C#", "Python", "Javascript"));
        languages.add("C++"); //checked if list still extensible
        itemizeList(languages);
    }

    private static void itemizeArray(String[] array) {
        int i = 1;
        for (String s : array) {
            System.out.printf("Язык №%d - %s;\n", i, s);
            i++;
        }
    }

    private static void itemizeList(List<String> list) {
        list.forEach(item -> System.out.printf("Язык №%d - %s;\n", list.indexOf(item)+1, item));
    }
}


