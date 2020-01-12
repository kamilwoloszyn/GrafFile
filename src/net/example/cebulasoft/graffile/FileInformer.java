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
                        if(line.contains("package")){
                            String pack= line.replace("package", "").replaceAll(" ",""); // Wyłusujemy nazwę pakietu
                            System.out.println(pack);
                            int lastPointer= fileName.lastIndexOf(".");
                            String name= fileName.substring(0, lastPointer);
                            connections.put(name, new FileInfo(pack, fileName));
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
                            if(line.contains(className)){
                                connections.get(className).addReference(); //TODO: Naprawic polaczenie miedzy plikami(jedno dla wszystkich)
                                System.out.println("tu");
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
