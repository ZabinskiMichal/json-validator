package com.zabinski;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        JsonVerifier verifyAsterisk = new ResourceFieldVerifier();

        boolean isValid = verifyAsterisk.verify(args[0]);
        System.out.printf("Verification result: " + isValid +  "\n");

    }
}
