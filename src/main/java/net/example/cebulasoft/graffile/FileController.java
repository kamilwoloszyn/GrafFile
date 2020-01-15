package net.example.cebulasoft.graffile;

import net.example.cebulasoft.graffile.method.FileParser;
import net.example.cebulasoft.graffile.method.MethodDependenciesResolver;
import net.example.cebulasoft.graffile.method.MethodDependencyValidator;
import net.example.cebulasoft.graffile.method.MethodInfo;

import java.util.List;

public class FileController {
    FilesConnectionInfo filesCollection; // contain all information about files like: size, connections between files, name and path.

    public FileController(String catalog) {
        filesCollection = new FilesConnectionInfo();
        List<String> listFile = FileFinder.getListOfFile(catalog, "java"); // take all file with extension .java and set them all names to list.

        //FileInformer.getInfo(listFile, filesCollection); //get information of file and put it in to the container.
        //FileGrafAdapter graf = new FileGrafAdapter(filesCollection); // make graf
        //graf.show(); // show graf
        MethodDependenciesResolver methodDependenciesResolver = new MethodDependenciesResolver(
                listFile,
                new MethodDependencyValidator(),
                new FileParser());
        List<MethodInfo> dependencies = methodDependenciesResolver.findDependencies();
        dependencies.forEach(System.out::println);

    }

    public static void main(String... args) {
//        FilesConnectionInfo i= new FilesConnectionInfo();
//        FileGrafAdapter graf = new FileGrafAdapter(i); // make graf
//        graf.show(); // show graf
//        System.out.print("działa?");
        FileController fileController = new FileController("C:\\Users\\kakk\\IdeaProjects\\wimiip\\GrafFileTest");
    }

}

