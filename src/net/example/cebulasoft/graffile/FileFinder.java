package net.example.cebulasoft.graffile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileFinder {
    public static void main(String[] args)
    {
        for (String s : getInfo("C:\\Users\\RZONCA\\IdeaProjects\\Projekcik\\Files") ) // sciezka do katalogu z plikami .java
        {
            System.out.println(s);

        }

    }
    public static List<String> getInfo(String nameDirectory)
    {

        File folder = new File(nameDirectory);

        List<String> result = new LinkedList<>();

        search(".*\\.java", folder, result);

        return result;
    }

    public static void search(String pattern, File folder, List<String> result)
    {
        for (File f : folder.listFiles())
        {

            if (f.isDirectory())
            {
                search(pattern, f, result);
            }

            if (f.isFile())
            {
                if (f.getName().matches(pattern))
                {
                    result.add(f.getAbsolutePath());
                }
            }

        }
    }

}