package com.tct.pbm;

import java.io.IOException;

public class ExternalRuntime {
    public static void main(String[] args) throws IOException {
        Runtime runTime = Runtime.getRuntime();

        Process p = runTime.exec("notepad.exe");
    }
}
