package com.zabinski;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileValidator {

    public static void validateFile(String filePath){

        if(!Files.exists(Paths.get(filePath))){
            throw new IllegalArgumentException("File does not exists: " + filePath);
        }

        if(!filePath.endsWith(".json")){
            throw new IllegalArgumentException("This is not json file: " + filePath);
        }
    }
}
