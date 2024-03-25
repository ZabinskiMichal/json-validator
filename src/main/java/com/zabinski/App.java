package com.zabinski;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {

        if (args.length != 1){
            System.err.println("Please specify path to file to validate");
            return;
        }

        JsonVerifier verifyAsterisk = new ResourceFieldVerifier();

        try{
            boolean isValid = verifyAsterisk.verify(args[0]);
            System.out.println("Verification result: " + isValid + "\n");
        }catch (IllegalArgumentException | IOException e) {
            System.err.println("Error in file verification " + e.getMessage());
        }catch (Exception e){
            System.err.println("Other error: " + e.getMessage());
            e.getStackTrace();
        }

    }
}
