package com.zabinski;

import com.zabinski.FieldsValidation.JsonVerifier;
import com.zabinski.FieldsValidation.ResourceFieldVerifier;
import com.zabinski.exceptions.ResourceFieldNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ResourceFieldVerifierTest {

    private final JsonVerifier verifier = new ResourceFieldVerifier();

    @Test
    public void jsonWithSingleAsteriskInResultShouldReturnFalse(){
        String filePath = "json-files/example1.json";
        try{
            boolean result = verifier.verify(filePath);
            Assertions.assertFalse(result, "This file should return false");
        }catch (IOException e){
            Assertions.fail("There should not be IOException thrown for this file " + filePath);
        }
    }

    @Test
    public void asteriskWithWhiteSignsShouldReturnFalse(){
        String filePath = "json-files/example2.json";
        try {
            boolean result = verifier.verify(filePath);
            Assertions.assertFalse(result, "* with white spaces should return false");
        }catch (IOException e){
            Assertions.fail("There should not be IOException thrown for this file " + filePath);
        }
    }

    @Test
    public void jsonWithoutResultFieldShouldThrowResourceFieldNotFoundException(){
        String filePath = "json-files/withoutResourceField.json";
        Assertions.assertThrows(ResourceFieldNotFoundException.class, () -> verifier.verify(filePath), "ResourceFieldNotFoundException should be thrown");
    }

    @Test
    public void anyOtherSignsExceptFromAsteriskShouldReturnTrue(){
        String filePath = "json-files/example3.json";
        try {
            boolean result = verifier.verify(filePath);
            Assertions.assertTrue(result, "anything except from * should return true");
        }catch (IOException e){
            Assertions.fail("There should not be IOException thrown for this file " + filePath);
        }
    }

    @Test
    public void emptyResourceFieldShouldReturnTrue(){
        String filePath = "json-files/example4.json";
        try {
            boolean result = verifier.verify(filePath);
            Assertions.assertTrue(result, "empty Resource field should return true");
        }catch (IOException e){
            Assertions.fail("There should not be IOException thrown for this file " + filePath);
        }
    }

    @Test
    public void emptyJsonShouldThrowIllegalArgumentException() throws IOException {
        String emptyJsonPath = "json-files/empty.json";
        Assertions.assertThrows(IllegalArgumentException.class, () -> verifier.verify(emptyJsonPath),
                "IllegalArgumentException should be thrown when file empty");
    }

}
