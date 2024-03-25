package com.zabinski.FieldsValidation;

import java.io.IOException;

public interface JsonVerifier {
    boolean verify(String filePath) throws IOException;
}
