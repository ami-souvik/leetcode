package executables.utils;

public class StringUtil {
    public static boolean isNumeric(String str) { 
        try {
            Double.parseDouble(str);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }
    }
    public static boolean isNull(String str) {
        return str.equalsIgnoreCase("null");
  }
}
