package com.zabinski;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ResourceFieldVerifier implements JsonVerifier{

    @Override
    public boolean verify(String filePath) throws IOException {

        try {
            FileValidator.validateFile(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNote = objectMapper.readTree(new File(filePath));
            JsonNode statements = rootNote.path("PolicyDocument").path("Statement");

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


        }catch (IllegalArgumentException | IOException e){
            System.err.println("Error in file verification");
            return false;
        }
    }


}
