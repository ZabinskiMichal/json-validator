package com.zabinski.fileValidation;

import com.zabinski.exceptions.InvalidFileExtensionException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileValidator {

    public static void validateFile(String filePath) throws InvalidFileExtensionException{

        if(!Files.exists(Paths.get(filePath))){
            throw new IllegalArgumentException("File does not exists: " + filePath);
        }

        if(!filePath.endsWith(".json")){
            throw new InvalidFileExtensionException("It's not a json file" + filePath);
        }
    }
}
