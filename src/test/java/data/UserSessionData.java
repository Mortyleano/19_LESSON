package data;

import com.codeborne.selenide.WebDriverRunner;

public class UserSessionData {

    private static final String TOKEN = extractValueFromCookieString("token");
    private static final String USER_ID = extractValueFromCookieString("userID");

    public static String getToken() {
        return TOKEN;
    }

    public static String getUserId() {
        return USER_ID;
    }

    private static String extractValueFromCookieString(String value) {
        String cookieValue = String.valueOf(WebDriverRunner.getWebDriver().manage().getCookieNamed(value));
        return cookieValue.substring(cookieValue.indexOf("=") + 1, cookieValue.indexOf(";"));
    }
}