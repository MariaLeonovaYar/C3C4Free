package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public static void writeAnswerToFile(Solution solution) throws IOException {
        FileWriter writer = new FileWriter("solution.txt");
        writer.write(solution.toString());
        writer.close();
    }
}
