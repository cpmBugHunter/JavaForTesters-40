package ru.pft40.bugHunter.Iterations;

import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Python", "Javascript"};
        itemizeArray(langs);
        List<String> languages = Arrays.asList("Java", "C#", "Python", "Javascript");
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
        int i = 1;
        for (String s : list) {
            System.out.printf("Язык №%d - %s;\n", i, s);
            i++;
        }
    }
}


