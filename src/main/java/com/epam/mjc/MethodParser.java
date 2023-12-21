package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        // Regular expression to match the method signature parts
        Pattern pattern = Pattern.compile("^(private|protected|public)?\\s*([^\\s]+)\\s+([^\\s]+)\\s*\\(([^)]*)\\)$");
        Matcher matcher = pattern.matcher(signatureString.trim());

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid method signature format");
        }

        String accessModifier = matcher.group(1);
        String returnType = matcher.group(2);
        String methodName = matcher.group(3);
        String[] argumentParts = matcher.group(4).split(",");

        List<MethodSignature.Argument> arguments = new ArrayList<>();
        for (String arg : argumentParts) {
            arg = arg.trim();
            if (!arg.isEmpty()) {
                String[] argDetails = arg.split("\\s+");
                arguments.add(new MethodSignature.Argument(argDetails[0], argDetails[1]));
            }
        }

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}
