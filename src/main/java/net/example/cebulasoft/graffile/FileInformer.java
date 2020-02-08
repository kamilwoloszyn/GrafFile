package net.example.cebulasoft.graffile;

import jdk.nashorn.internal.runtime.regexp.RegExp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileInformer {
    // filesNames {FileInfo.java, FileInformer.java, FileFinder.java, etc...}

    private void addLine(List<String> list, StringBuilder builder) {
        if (builder.length() != 0) {
            list.add(builder.toString());
            builder.setLength(0);
        }
    }

    private String removeText(String line){
        String cos = "s';e\" m\' D\\";
        StringBuilder builder = new StringBuilder();
        boolean isText = false;
        boolean isSpecialChar = false;
        boolean isChar = false;
        char c;
        for (int i = 0; i < line.length(); i++) {
            c = line.charAt(i);

            switch (c){
                case '\'':
                    if(!isText) {
                        if (isChar) {
                            if (isSpecialChar) {
                                isSpecialChar = false;
                                break;
                            }
                            isChar = false;
                            break;
                        }
                        isChar = true;
                        builder.append(c);
                    }
                    break;

                case '"':
                    if(!isChar) {
                        if(isText) {
                            if (isSpecialChar) {
                                isSpecialChar = false;
                                break;
                            }
                            isText =false;
                            break;
                        }
                        isText = true;
                        builder.append(c);
                    }
                    break;
                case '\\':
                    if (isText || isChar)
                        isSpecialChar = !isSpecialChar;
                    break;
                default:
                    isSpecialChar =false;
            }

            if (!isText && !isChar)
                builder.append(c);
        }
        return builder.toString();
    }

    private boolean isLargeComment = false;
    private String[] splitLine(String line) {
        StringBuilder builder = new StringBuilder();
        List<String> stringList = new LinkedList<>();

        boolean isSpecialChar = false;
        boolean isText = false;
        boolean isWhiteChar = false;
        boolean isChar = false;
        boolean isComment = false;
        char c;
        for (int i = 0; i < line.length(); i++) {
            if (isLargeComment) {
                int j = line.indexOf("*/");
                if (j < 0)
                    break;
                i = j + 1;
                isLargeComment = false;
                continue;
            }
            c = line.charAt(i);
            switch (c) {
                case '\'':
                    if(!isText) {
                        if (isChar) {
                            if (isSpecialChar) {
                                isSpecialChar = false;
                                break;
                            }
                            isChar = false;
                            break;
                        }
                        isChar = true;
                    }
                    break;

                case '"':
                    if (isSpecialChar) {
                        isSpecialChar = false;
                        break;
                    }
                    if(!isChar) {
                        isText = !isText;
                        builder.append(c);
                        continue;
                    }
                    break;
                case '\\':
                    if (isText || isChar)
                        isSpecialChar = !isSpecialChar;
                    break;
                case ';':
                    if (!isText && !isChar) {
                        builder.append(c);
                        addLine(stringList, builder);
                        continue;
                    }
                    break;
                case ' ':
                case '\t':
                    if(!isText && !isChar) {
                        if (isWhiteChar)
                            continue;
                        if (builder.length() == 0) {
                            isWhiteChar = true;
                            continue;
                        }
                        isWhiteChar = true;
                        c = ' ';
                    }
                    break;
                case '/':
                    if (!isText) {
                        if (isComment) {
                            builder.setLength(builder.length() - 1);
                            addLine(stringList, builder);
                            return stringList.toArray(new String[0]);
                        }
                        isComment = true;
                    }
                    break;
                case '*':
                    if (!isText) {
                        if (isComment) {
                            isComment = false;
                            isLargeComment = true;
                            builder.setLength(builder.length() - 1);
                            continue;
                        }
                    }
                    break;
                default:
                    isSpecialChar = false;
                    isWhiteChar = false;
            }
            builder.append(c);
        }
        addLine(stringList, builder);
        return stringList.toArray(new String[0]);
    }

    private void basicInfo(List<String> filesNames, FilesConnectionInfo connections) {
        loopFiles:
        for (String fileName : filesNames) {
            File file = new File(fileName);
            try {
                isLargeComment = false;
                Scanner scanner = new Scanner(file);

                String packageName = "";
                String classNameFile = "";
                while (scanner.hasNextLine()) {
                    String[] lines = splitLine(scanner.nextLine());
                    for (String line : lines) {
                        line = removeText(line);
                        if (line.matches("^package\\s[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*\\s?;")) {
                            packageName = line.replaceAll("^package\\s([a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*)\\s?;", "$1");
                        }

                        if (line.matches("[a-zA-Z0-9_\\s]*(class|interface) [a-zA-Z0-9_ ]+.*")) {
                            String className = line.replaceAll("[[a-zA-Z0-9_]\\s]*(class|interface) ([a-zA-Z0-9_]+).*", "$2");
                            //Wyszukiwanie class jest ograniczone ze względu na uporszczenia całego projektu (plik moze zawierać wiecej klas)
                            if (fileName.contains(className)) {
                                classNameFile = className;
                            }
                        }
                        if (classNameFile.length() != 0 && packageName.length() != 0) {
                            connections.put(fileName, new FileInfo(packageName, classNameFile));
                            continue loopFiles;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void relationBetweenFiles(List<String> filesNames, FilesConnectionInfo connections) {
        for (String fileName : filesNames) {
            File file = new File(fileName);
            try {
                Scanner scanner = new Scanner(file);
                isLargeComment = false;
                while (scanner.hasNextLine()) {
                    String[] lines = splitLine(scanner.nextLine());
                    for (String line : lines) {
                        line = removeText(line);
                        for (FileInfo fileInfo: connections.values()) {
                            if (!fileName.contains(fileInfo.getName()) && line.contains(fileInfo.getName())) {
                                connections.get(fileName).addReference(fileInfo.getFullName());
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void getInfo(List<String> filesNames, FilesConnectionInfo connections) {
        basicInfo(filesNames, connections);
        relationBetweenFiles(filesNames, connections);
    }
}