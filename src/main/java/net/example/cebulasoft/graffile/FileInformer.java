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
                        if(line.indexOf("package")!=-1){
                            String path= line.replace("package", "").replaceAll(" ","");
                            int lastPointer= fileName.lastIndexOf(".");
                            String name= fileName.substring(0, lastPointer);
                            connections.put(name, new FileInfo(path, fileName));
                        }
                    }
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        for (String fileName: filesNames) {
            File file= new File(fileName);
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()){
                    String[] lines=scanner.nextLine().split(";");
                    for (String line: lines) {
                        for (String className: connections.keySet()){
                            if(line.indexOf(className)!=-1){
                                connections.get(className).addReference(); //TODO: Naprawic polaczenie miedzy plikami(jedno dla wszystkich)
                            }
                        }
                    }
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
