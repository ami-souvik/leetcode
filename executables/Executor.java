package executables;

import java.util.List;
import executables.templates.*;
import solutions.*;

public class Executor {
    public static void main(String className, String methodName, List<Variable> vars) {
        Solution solution = new Solution();
        String[] testCases = new String[]{
            "[1,7,0,7,-8,null,null]",
            "[989,null,10250,98693,-89388,null,null,null,-32127]",
            "[-100,-200,-300,-20,-5,-10,null]"
        };
        for(String test:testCases) {
            try {
                TreeNodeParser ip = TreeNodeParser.parse(test);
                System.out.println("INPUT:" + ip);
                Object result = solution.maxLevelSum(ip.treeRoot());
                System.out.println("OUTPUT:" + result);
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Test Error: ");
                e.printStackTrace();
            }
        }
    }
}
