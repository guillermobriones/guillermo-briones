package com.uem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(2, 3);
        System.out.println("Result: " + result);

        Searcher searcher = new Searcher();
        
        String wordToSearch = "banana";
        boolean found = searcher.searchWord(wordToSearch, List.of("apple", "pear", "cherry"));
        System.out.println("Found '" + wordToSearch + "': "+ found);
        
        

        
    }
}