package clone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    static String source = "Client";
    static String target = "Scope";

    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.out.println(" Please insert a valid param like: Scope");
            return;
        }

        target = args[0];
        seekFile(new File("./" + source.toLowerCase()));
    }

    // Filter files from directories
    private static void seekFile(File folder) throws IOException {

        for (File item : folder.listFiles()) {

            if (item.isDirectory()) {
                String dir = item.getPath()
                    .replace(source, target)
                    .replace(source.toLowerCase(), target.toLowerCase());

                new File(dir).mkdirs();

                seekFile(item);

                continue;
            }

            if (item.isFile()) {
                updateFile(item);
            }
        }
    }

    private static void updateFile(File sourceFile) throws IOException {
        String targetFileName = sourceFile.getPath()
            .replace(source, target)
            .replace(source.toLowerCase(), target.toLowerCase());

        File targetFile = new File(targetFileName);
        targetFile.createNewFile();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetFile));

        BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile));
        bufferedReader.lines().forEach(l -> replaceLines(bufferedWriter, l));

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void replaceLines(BufferedWriter bufferedWriter, String line) {
        line = line
            .replace(source, target)
            .replace(source.toLowerCase(), target.toLowerCase());

        try {
            bufferedWriter.write(line + "\r\n");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
