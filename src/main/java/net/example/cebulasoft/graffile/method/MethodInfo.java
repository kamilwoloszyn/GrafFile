package net.example.cebulasoft.graffile.method;

import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MethodInfo {

    @NonNull
    private String name;

    @NonNull
    private String parameters;

    @NonNull
    private String body;

    public String getClassName() {
        return className;
    }

    private String className;

    @NonNull
    private List<MethodCall> methodsUsed;

    public void addCall(String className, String methodName, int i) {
        this.methodsUsed.add(MethodCall.of(className, methodName, i));
    }

    @Override
    public String toString() {
        return "MethodInfo{" +
                "name='" + name + '\'' +
                ", methodsUsed=" + methodsUsed +
                '}';
    }
}