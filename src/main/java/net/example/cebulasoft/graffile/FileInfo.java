package net.example.cebulasoft.graffile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class FileInfo {
    private String packageName; // paczka
    private String name; // nazwa pliku razem z rozszerzeniem.
    private HashMap<String, Integer> references; // przechowuje liste plików z których korzysta plik.

    public FileInfo(String path, String name) {
        this.packageName = path;
        this.name = name;
        this.references = new HashMap<String, Integer>();
    }

    public String getPackageName() {
        return packageName;
    }
    public String getFullName(){
        return packageName + '.' + name;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "packageName='" + packageName + '\'' +
                ", name='" + name + '\'' +
                ", references=" + references +
                '}';
    }

    public void addReference(String file){
        if(references.containsKey(file)){
            references.put(file, references.get(file) + 1);
        }
        else{
            references.put(file, 1);
        }
    }

    public Iterator<Map.Entry<String, Integer>> getIteratorToReferences(){
        return references.entrySet().iterator();
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int hashCode() {
        return Objects.hash(packageName, name);
    }
}

