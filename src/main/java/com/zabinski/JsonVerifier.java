package com.zabinski;

import java.io.IOException;

public interface JsonVerifier {
    boolean verify(String filePath) throws IOException;
}
