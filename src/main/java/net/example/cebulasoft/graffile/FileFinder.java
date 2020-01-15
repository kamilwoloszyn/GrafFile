package net.example.cebulasoft.graffile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileFinder {
    public static List<String> getListOfFile(String nameDirectory, String extension) {

        File folder = new File(nameDirectory);

        List<String> result = new LinkedList<>();

        search("(.*)." + extension, folder, result);

        return result;
    }

    public static void search(String pattern, File folder, List<String> result) {
        for (File file : folder.listFiles()) {

            if (file.isDirectory()) {
                search(pattern, file, result);
            }

            if (file.isFile()) {
                if (file.getName().matches(pattern)) {
                    result.add(file.getAbsolutePath());
                }
            }

        }
    }

}