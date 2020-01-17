package net.example.cebulasoft.graffile.method;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ClassInfo {

    private String name;

    private String absolutePath;

    private List<MethodInfo> methods = new ArrayList<>();

    private List<String> fieldTypes = new ArrayList<>();

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public void addMethod(String name, String parameters, String body) {
        this.methods.add(new MethodInfo(name, parameters, body, new ArrayList<>()));
    }

    public void setName(String className) {
        this.name = className;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "name='" + name + '\'' +
                ", methods=" + methods +
                '}';
    }

    public void setClassToMethods() {
        getMethods().forEach(methodInfo -> methodInfo.setClassName(this.name));
    }

    public void addFieldType(String fieldType) {
        fieldTypes.add(fieldType);
    }

    public boolean containsFieldWithMethodClassType(String className) {
        return fieldTypes.contains(className);
    }
}
