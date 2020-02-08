package net.example.cebulasoft.graffile.method;

import net.example.cebulasoft.graffile.Java8BaseListener;
import net.example.cebulasoft.graffile.Java8Parser;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ClassListener extends Java8BaseListener {

    private ClassInfo parsedClass = new ClassInfo();

    @Override
    public void enterClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
        ctx.normalClassDeclaration().classBody().classBodyDeclaration()
                .forEach(classBodyDeclarationContext -> {
                    if (Objects.nonNull(classBodyDeclarationContext.classMemberDeclaration())) {
                        if (Objects.nonNull(classBodyDeclarationContext.classMemberDeclaration().fieldDeclaration()))
                            parsedClass.addFieldType(classBodyDeclarationContext.classMemberDeclaration().fieldDeclaration().unannType().getText());
                    }
                });
        String className = ctx.normalClassDeclaration().Identifier().getText();
        parsedClass.setName(className);
    }

    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        parsedClass.addMethod(
                ctx.methodHeader().methodDeclarator().Identifier().getText(),
                StringUtils.substringBetween(ctx.methodHeader().methodDeclarator().getText(), "(", ")"),
                ctx.methodBody().getText());
    }

    public ClassInfo getParsedClass() {
        return parsedClass;
    }
}
