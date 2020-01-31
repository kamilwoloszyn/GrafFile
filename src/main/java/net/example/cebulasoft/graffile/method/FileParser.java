package net.example.cebulasoft.graffile.method;

import net.example.cebulasoft.graffile.Java8Lexer;
import net.example.cebulasoft.graffile.Java8Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileParser {

    public ClassInfo parseFile(String filePath) {
        try {
            String fileContent = FileUtils.readFileToString(new File(filePath), String.valueOf(StandardCharsets.UTF_8));
            Java8Lexer java8Lexer = new Java8Lexer(CharStreams.fromString(fileContent));
            CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
            Java8Parser parser = new Java8Parser(tokens);

            ClassListener classListener = new ClassListener();
            new ParseTreeWalker().walk(classListener, parser.compilationUnit());
            ClassInfo parsedClass = classListener.getParsedClass();
            parsedClass.setAbsolutePath(filePath);
            parsedClass.setClassToMethods();

            return parsedClass;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ClassInfo();
    }
}
