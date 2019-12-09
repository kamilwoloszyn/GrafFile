package net.example.cebulasoft.graffile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInformer {
    public static FileInfo getInfo(String fileName){
        File file= new File(fileName);
        FileInfo info = new FileInfo();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String[] lines=scanner.nextLine().split(";");
                for (String line: lines) { //WYCIAGANIE IMPUTOW
                    if(line.indexOf("import")!=-1) {
                        String address = line.replaceAll("import","").replaceAll(" ","");
                        int lastPointer = address.lastIndexOf(".");
                        String name = address.substring(lastPointer, address.length());
                        FileConnectionInfo connInfo = new FileConnectionInfo(address.substring(0,lastPointer), name);
                        info.add(connInfo);
                    }
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}

