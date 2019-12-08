package net.example.cebulasoft.graffile;

import java.util.HashMap;
import java.util.List;

public class FileController {
    HashMap<String, FileInfo> filesCollection; // contain all information about files like: size, connections between files, name and path.

    public FileController(String catalog){
        filesCollection = new FileConnectionInfo();
        List<String> listFile = FileFinder.getListOfFile("java", catalog); // take all file with extension .java and set them all names to list.

        FileInformer.getInfo(filesCollection, listFile); //get information of file and put it in to the container.

        FileGrafAdapter graf = new FileGrafAdapter(filesCollection); // make graf
        graf.show(); // show graf

    }

}

