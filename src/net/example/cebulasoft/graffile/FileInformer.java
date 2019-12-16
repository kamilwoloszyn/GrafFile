package net.example.cebulasoft.graffile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FileInformer {
    // filesNames {FileInfo.java, FileInformer.java, FileFinder.java, etc...}
    public static void getInfo(List<String> filesNames, FilesConnectionInfo connections){
        for (String fileName: filesNames) {
            File file= new File(fileName);
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()){
                    String[] lines=scanner.nextLine().split(";");
                    for (String line: lines) {

                    }
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}

