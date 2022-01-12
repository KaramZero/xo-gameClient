/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    
     private static final String ZERO_TO_255
            = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";
    private static final String IP_REGEXP
            = ZERO_TO_255 + "\\." + ZERO_TO_255 + "\\."
            + ZERO_TO_255 + "\\." + ZERO_TO_255;
    private static final Pattern IP_PATTERN
            = Pattern.compile(IP_REGEXP);
    
     private static final String Email_REGEXP = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
      private static final Pattern EMAIL_PATTERN
            = Pattern.compile(Email_REGEXP);
 
    // Return true when *address* is IP Address
    public static  boolean isValidIP(String address) {
        return IP_PATTERN.matcher(address).matches();
    }
    
        // Return true when *email* is email 
     public static  boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
