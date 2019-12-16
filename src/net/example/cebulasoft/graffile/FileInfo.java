package net.example.cebulasoft.graffile;

import java.util.Objects;

public class FileInfo {
   private String path; // paczka
   private String name; // nazwa pliku razem z rozszerzeniem.
   private int references; // ilość odwołań

    public FileInfo(String path, String name) {
        this.path = path;
        this.name = name;
        this.references = 0;
    }

    public String getPath() {
        return path;
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

    public int getReferences() {
        return references;
    }

    public void addReference() {
        this.references++;
    }
    @Override
    public int hashCode() {
        return Objects.hash(path, name, references);
    }
}

