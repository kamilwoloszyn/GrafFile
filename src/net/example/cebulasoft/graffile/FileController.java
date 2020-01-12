package net.example.cebulasoft.graffile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FileController {
    private FilesConnectionInfo filesCollection; // contain all information about files like: size, connections between files, name and path.

    public FileController(String catalog){
        filesCollection = new FilesConnectionInfo();
        List<String> listFile = FileFinder.getListOfFile(catalog,"java"); // take all file with extension .java and set them all names to list.

        FileInformer.getInfo(listFile, filesCollection); //get information of file and put it in to the container.

        System.out.println(Arrays.toString(filesCollection.keySet().toArray()));
    }

    public static void main(String... args){
        System.out.println(Arrays.toString(args));

        boolean local = true;
        boolean isChoosePath = false;
        String path = null;

        for (int parameter = 0; parameter < args.length; parameter++) {
            switch (args[parameter]){
                case "-g":
                    local = false; // zmiana sposobu podawania ścieżki
                case "-l":
                    if(isChoosePath){
                        throw new RuntimeException("Path was choose before " + (local ? "-l": "-g" + path));
                    }else {
                        isChoosePath = true;
                        if (++parameter < args.length) // zmien o jeden i spradz czy jest wiekszy od długości argumentów
                            path = args[parameter];
                        else
                            throw new RuntimeException("Expected Path, get nothing.");
                    }
                    break;
                case "-file":
                    break;
            }
        }

        FileController controller = new FileController(path);
    }

}

