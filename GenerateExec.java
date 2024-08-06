import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

import javax.naming.spi.Resolver;

public class GenerateExec {
    static String GENERATED_DIR = "./solutions";
    static String EXEC_DIR = "./testcases";

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: leetcode problem [script]");
            System.exit(64);
        } else if (args.length == 1) {
            generateFile(parseExec(args[0]));
        }
    }

    private static JSONObject parseExec(String execId) {
        try {
            return (JSONObject) new JSONParser().parse(new FileReader(EXEC_DIR + "/" + execId + ".json"));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.print("Parse exec error: ");
            e.printStackTrace();
        }
        return null;
    }

    private static void generateFile(JSONObject obj) throws IOException {
        String problemName = (String) obj.get("problemName");
        byte[] bytes = Files.readAllBytes(Paths.get("./problems/" + problemName + ".java"));

        JSONObject metadata = (JSONObject) obj.get("metadata");
        String filename = (String) metadata.get("filename");
        String genPath = GENERATED_DIR + "/" + filename + ".java";
        PrintWriter writer = new PrintWriter(genPath, "UTF-8");

        writer.println("package solutions;");
        writer.println("");
        writer.println("import java.util.*;");
        writer.println("import executables.templates.*;");
        writer.println("");
        writer.println(new String(bytes, Charset.defaultCharset()));
        writer.println("public class " + filename + " {");
        writer.println("\tpublic static void main() {");
        String className = (String) metadata.get("className");
        writer.println("\t\tObject output;");
        writer.println("\t\t" +
                className + " exec = new " + className + "();");
        JSONArray testCases = (JSONArray) obj.get("testCases");
        for (Object testcase : testCases) {
            writer.println("\t\tSystem.out.println(\"" + (String) ((JSONObject) testcase).get("name") + "\");");
            writer.print("\t\toutput = exec." + (String) metadata.get("mainMethod") + "(");
            writer.print(generateTestCase((JSONObject) testcase));
            writer.println(");");
            writer.println("\t\tSystem.out.println(\"OUTPUT: \" + output);");
        }
        writer.println("\t}");
        writer.println("}");
        writer.close();
    }

    private static String generateTestCase(JSONObject testcase) {
        String args = "";
        for (Object var : (JSONArray) testcase.get("variables")) {
            switch ((String) ((JSONObject) var).get("type")) {
                case "TreeNode":
                    args += "TreeNodeParser.parse(\""
                            + (String) ((JSONObject) var).get("value")
                            + "\").getTree()";
                    break;
                default:
                    break;
            }
        }
        return args;
    }
}