package com.tct.pbm;


import java.util.Arrays;
import java.util.List;

public class StreamAPI {
    public static void main(String[] args) {
        List<String> test = Arrays.asList("abc", "efg", "1", "23", "3", "6");
        System.out.println("Test001");
        test.stream().filter((param)-> param == "1").forEach((item)->{
            System.out.println(item);
        });

    }
}
