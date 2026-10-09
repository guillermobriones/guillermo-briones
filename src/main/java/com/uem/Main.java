package com.uem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    Searcher searcher = new Searcher();
    String wordToSearch = "banana";
        boolean found = searcher.searchWord(wordToSearch, List.of("apple", "banana", "cherry"));       
        System.out.println("Word found: " + found);
    }
}