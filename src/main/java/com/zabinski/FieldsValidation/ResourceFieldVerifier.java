package com.zabinski.FieldsValidation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zabinski.fileValidation.FileValidator;
import com.zabinski.exceptions.InvalidFileExtensionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ResourceFieldVerifier implements JsonVerifier {


    /**
     *
     * @param filePaths
     * @return
     * should return false only when "Resource": "*"
     * @throws IOException
     */
    @Override
    public boolean verify(String filePath) throws IOException {

        try {
            FileValidator.validateFile(filePath);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNote = objectMapper.readTree(new File(filePath));
            JsonNode statements = rootNote.path("PolicyDocument").path("Statement"); //PolicyDocument is required field in IAM Role Policy format

            if (!statements.isArray()){
                throw new IllegalArgumentException("Statement element is expected to be an array");
            }

            for (JsonNode statement : statements){
                JsonNode resourceNode = statement.path("Resource");
                if(resourceNode.isTextual() && "*".equals(resourceNode.asText().trim())){
                    return false;
                }
            }
            return true;

        }catch (FileNotFoundException e){
            throw new IllegalArgumentException("File does not exists: " + filePath, e);
        }catch (IOException e) {
            throw new IllegalArgumentException("Error in reading file: " + filePath, e);
        }catch (InvalidFileExtensionException e){
            throw new IllegalArgumentException("Invalid extension of file: " + filePath);
        }
    }


}
