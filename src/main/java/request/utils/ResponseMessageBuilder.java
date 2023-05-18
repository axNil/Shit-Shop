package request.utils;

import java.util.HashMap;

public class ResponseMessageBuilder {
    public static HashMap<String, String> createErrorMessage(String errorMsg) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", errorMsg);
        response.put("status", "error");
        return response;
    }
}
