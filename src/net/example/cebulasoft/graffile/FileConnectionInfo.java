package net.example.cebulasoft.graffile;

import java.util.Objects;

public class FileConnectionInfo {
   private String path;
   private String name;
   private String references;

    public FileConnectionInfo() {
    }

    public FileConnectionInfo(String path, String name, String references) {
        this.path = path;
        this.name = name;
        this.references = references;
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

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileConnectionInfo that = (FileConnectionInfo) o;
        return Objects.equals(path, that.path) &&
                Objects.equals(name, that.name) &&
                Objects.equals(references, that.references);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, name, references);
    }
}

