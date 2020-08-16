package com.tct.pbm;
//외부 프로그램 실행 ProcessBuilder

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExternalProcessBuilder {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        str= br.readLine();
        String[] strNums = str.split(" ");
        Process process = null;
        String[] cmd = new String[] {"sum.exe", strNums[0], strNums[1]};

        try {
            process = new ProcessBuilder(cmd).start();

            BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ( (str= stdOut.readLine()) != null ) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
