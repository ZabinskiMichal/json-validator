package com.zabinski.fileValidation;

import com.zabinski.exceptions.InvalidFileExtensionException;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileValidator {


    /**
     * Validates if the specified file exists and is a JSON file.
     *
     * This method performs two main validations:
     * 1. Checks if the file at the provided path exists in the file system.
     *    If the file does not exist, it throws a {@link FileNotFoundException}.
     * 2. Checks if the file has a .json extension.
     *    If the file does not have a .json extension, it throws an {@link InvalidFileExtensionException}.
     *
     * @param filePath The path to the file being validated. Must not be null.
     * @throws FileNotFoundException If the file at the specified path does not exist.
     * @throws InvalidFileExtensionException If the file does not have a .json extension.
     */
    public static void validateFile(String filePath) throws FileNotFoundException, InvalidFileExtensionException {

        if(!Files.exists(Paths.get(filePath))){
            throw new FileNotFoundException("File does not exists: " + filePath);
        }

        if(!filePath.endsWith(".json")){
            throw new InvalidFileExtensionException("It's not a json file" + filePath);
        }
    }
}
