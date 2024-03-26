package com.zabinski.FieldsValidation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zabinski.exceptions.ResourceFieldNotFoundException;
import com.zabinski.fileValidation.FileValidator;
import com.zabinski.exceptions.InvalidFileExtensionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ResourceFieldVerifier implements JsonVerifier {



    /**
     * Verifies the JSON file located at the specified filePath against the validation criteria.
     *
     * This method performs several validations:
     * 1. Validates the file existence and extension using FileValidator.
     * 2. Ensures the JSON structure contains a "PolicyDocument" with a "Statement" array.
     * 3. Checks each "Statement" for a "Resource" field. If the "Resource" field is missing,
     *    it throws a {@link ResourceFieldNotFoundException}.
     * 4. If any "Resource" field contains a single asterisk "*", the method returns false,
     *    indicating the JSON file does not meet the validation criteria.
     *
     * If all "Resource" fields are valid and no "*" is found, the method returns true.
     *
     * @param filePath The path to the JSON file being verified. Must not be null.
     * @return true if the JSON file meets the validation criteria, otherwise false.
     * @throws IOException If an I/O error occurs reading from the file.
     * @throws IllegalArgumentException If the JSON structure is invalid or a required node is missing.
     * @throws ResourceFieldNotFoundException If the "Resource" field is missing in any "Statement".
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
                if (resourceNode.isMissingNode()){
                    throw new ResourceFieldNotFoundException("Resource field not found in this file: " + filePath);
                }
                if(resourceNode.isTextual() && "*".equals(resourceNode.asText().trim())){
                    return false;
                }
            }
            return true;


        }catch (FileNotFoundException e){
            throw new IllegalArgumentException("File does not exists: " + filePath, e);
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No statement node: " + filePath, e);
        }catch (InvalidFileExtensionException e){
            throw new IllegalArgumentException("Invalid extension of file: " + filePath);
        }
    }


}
