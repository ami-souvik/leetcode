import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Arrays;

public class LeetCode {
    public static void main(String[] args) {
        // exec1161.main();
        File solutionDirPath = new File("./solutions");
        String contents[] = solutionDirPath.list();
        for (String file : contents) {
            String className = "solutions." + file.substring(0, file.length() - 5);
            try {
                Class<?> classObj = Class.forName(className);
                Method main = classObj.getMethod("main");
                main.invoke(null);
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("LeetCode processing error: ");
                e.printStackTrace();
            }
        }
    }
}