package net.example.cebulasoft.graffile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class FileInfo {
    private String path; // paczka
    private String name; // nazwa pliku razem z rozszerzeniem.
    private HashMap<String, Integer> references; // przechowuje liste plików z których korzysta plik.

    public FileInfo(String path, String name) {
        this.path = path;
        this.name = name;
        this.references = new HashMap<String, Integer>();
    }

    public String getPath() {
        return path;
    }

    public void addReference(String file){
        if(references.containsKey(file)){
            references.put(file, references.get(file) + 1);
        }
        else{
            references.put(file, 1);
        }
    }

    public Iterator<Integer> getIteratorToReferences(){
        return references.values().iterator();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int hashCode() {
        return Objects.hash(path, name);
    }


    public void setReferences(Iterable<Object> references) {
        this.references = references;
    }

}

