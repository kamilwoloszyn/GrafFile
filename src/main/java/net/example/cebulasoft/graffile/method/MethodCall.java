package net.example.cebulasoft.graffile.method;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@ToString
public class MethodCall {

    private String className;
    private String methodCalledName;
    private int numberOfCalls;
}
