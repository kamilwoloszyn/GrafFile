package net.example.cebulasoft.graffile.method;

import lombok.NonNull;

public class MethodDependencyValidator {

    public boolean checkIfValidCall(ClassInfo classInfo, MethodInfo methodInfo, MethodInfo method) {
        return methodInfo.getParameters().contains(method.getClassName()) ||
                methodInfo.getClassName().equals(method.getClassName()) ||
                classInfo.containsFieldWithMethodClassType(method.getClassName()) ||
                methodInfo.getBody().contains(method.getClassName());
    }

    public int countMethodCalls(@NonNull String firstMethodBody, @NonNull String secondMethodName) {
        int externalCallsNumber = StringUtils.countMatches(firstMethodBody, "." + secondMethodName + "(");
        int internalCallsNumber = StringUtils.countMatches(firstMethodBody, " " + secondMethodName + "(");
        return externalCallsNumber + internalCallsNumber;
    }
}
