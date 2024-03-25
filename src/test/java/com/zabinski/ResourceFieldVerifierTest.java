package com.zabinski;

import com.zabinski.FieldsValidation.JsonVerifier;
import com.zabinski.FieldsValidation.ResourceFieldVerifier;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class ResourceFieldVerifierTest {

    private final JsonVerifier verifier = new ResourceFieldVerifier();

    @Test
    public void jsonWithoutResultFieldShouldReturnTrue(){
        String validJsonPath = "json-files/withoutResourceField.json";
        try {
            boolean result = verifier.verify(validJsonPath);
            Assertions.assertTrue(result, "File should be valid");
        } catch (IOException e){
            Assertions.fail("IOException should not be thrown for this file");
        }
    }
}