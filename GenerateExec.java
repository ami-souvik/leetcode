import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import javax.naming.spi.Resolver;

public class GenerateExec {
    static String GENERATED_DIR = "./solutions";
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: leetcode problem [script]");
            System.exit(64); 
        } else if (args.length == 1) {
            generateFile(args[0]);
        }
    }

    private static void generateFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("./problems/"+ path));

        String genPath = GENERATED_DIR + "/" + path;
        PrintWriter writer = new PrintWriter(genPath, "UTF-8");

        writer.println("package solutions;");
        writer.println("");
        writer.println("import java.util.*;");
        writer.println("import executables.templates.*;");
        writer.println("");
        writer.println(new String(bytes, Charset.defaultCharset()));
        writer.close();
    }
}