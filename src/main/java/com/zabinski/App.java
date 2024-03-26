package com.zabinski;

import com.zabinski.FieldsValidation.JsonVerifier;
import com.zabinski.FieldsValidation.ResourceFieldVerifier;

import java.io.IOException;

public class App 
{
    // TODO add read me
    // TODO add dosc
    //TODO add instruction how to run
    //TODO check edge cases

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
        }catch (Exception e){
            System.err.println("Other error: " + e.getMessage());
            e.getStackTrace();
        }

    }
}
