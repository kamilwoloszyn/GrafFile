package net.example.cebulasoft.graffile.method;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MethodDependenciesResolver {

    private List<String> filePaths;
    private MethodDependencyValidator dependencyConstraintChecker;
    private FileParser fileParser;
    private List<ClassInfo> classInfos;

    public MethodDependenciesResolver(List<String> filePaths, MethodDependencyValidator dependencyConstraintChecker, FileParser fileParser) {
        this.filePaths = filePaths;
        this.dependencyConstraintChecker = dependencyConstraintChecker;
        this.fileParser = fileParser;
    }

    public List<MethodInfo> findDependencies() {
        classInfos = parseFiles();

        classInfos.forEach(classInfo -> findMethodCalls(classInfo, extractAllMethods(classInfos)));

        return classInfos.stream().map(ClassInfo::getMethods)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ClassInfo> parseFiles() {
        return filePaths.stream()
                .map(filePath -> fileParser.parseFile(filePath))
                .collect(Collectors.toList());
    }

    private Set<MethodInfo> extractAllMethods(List<ClassInfo> classInfos) {
        return classInfos.stream()
                .map(ClassInfo::getMethods)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private void findMethodCalls(ClassInfo classInfo, Set<MethodInfo> methods) {
        classInfo.getMethods().forEach(
                classMethod -> methods.forEach(method -> {
                    if (dependencyConstraintChecker.checkIfValidCall(classInfo, classMethod, method)) {
                        int totalCalls = dependencyConstraintChecker.countMethodCalls(classMethod.getBody(), method.getName());
                        if (totalCalls > 0) {
                            classMethod.addCall(method.getClassName(), method.getName(), totalCalls);
                        }
                    }
                })
        );
    }

    public List<ClassInfo> getClassInfos() {
        return classInfos;
    }

    @Override
    public String toString() {
        return "MethodDependenciesResolver{" +
                "classInfos=" + classInfos +
                '}';
    }
}
