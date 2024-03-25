package com.zabinski;

import com.zabinski.exceptions.InvalidFileExtensionException;
import com.zabinski.fileValidation.FileValidator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;

public class FileValidatorTest {


    @Test
    public void nonExistingFileFileShouldThrowFileNotFoundException(){
        String nonExistingFilePath = "json-files/nonexistent.json";
        Assertions.assertThrows(FileNotFoundException.class, () -> FileValidator.validateFile(nonExistingFilePath),
                "FileNotFoundException should be thrown for non existent file");
    }


    @Test
    public void invalidFileExtensionShouldThrowInvalidFileExtensionException(){
        String invalidExtensionFilePath = "json-files/invalidExample1.jakson";
        Assertions.assertThrows(InvalidFileExtensionException.class, () -> FileValidator.validateFile(invalidExtensionFilePath),
                "InvalidFileExtensionException should be thrown if file is not .json");
    }

    @Test
    public void validJsonFileShouldNotThrowException(){
        String validJsonFilePath = "json-files/example1.json";
        Assertions.assertDoesNotThrow(() -> FileValidator.validateFile(validJsonFilePath),
                "No exception should be thrown for existing file with .json extension");


    }

}
