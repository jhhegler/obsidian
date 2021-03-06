/**
 * Copyright 2013 Hunter Hegler
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.obsidian.equality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.obsidian.obsidianConstants.ClassNames;
import org.obsidian.obsidianConstants.Methods;
import org.obsidian.util.Helpers;

/**
 *
 * @author Hunter Hegler (jhhegler@gmail.com)
 *
 * Contributors: Michael Cole (micole.3@gmail.com)
 *
 */
public class GlobalEqualityMethodClass {

    private HashMap<String, GlobalEqualityMethod> methods;
    private String classPackage;
    private ArrayList<String> imports = new ArrayList<String>();
    private HashMap<String, String> primitiveFullName;

    public GlobalEqualityMethodClass(String classPackage) {

        //initialize HashMap
        methods = new HashMap<String, GlobalEqualityMethod>();

        //The primitive map in case anyone uses a primitive by calling the 
        //fully qualified class name
        primitiveFullName = new HashMap<String, String>();

        primitiveFullName.put("java.lang.String", "");
        primitiveFullName.put("java.lang.Byte", "");
        primitiveFullName.put("java.lang.Short", "");
        primitiveFullName.put("java.lang.Integer", "");
        primitiveFullName.put("java.lang.Long", "");
        primitiveFullName.put("java.lang.Float", "");
        primitiveFullName.put("java.lang.Double", "");
        primitiveFullName.put("java.lang.Boolean", "");
        primitiveFullName.put("java.lang.Character", "");

        //initialize classPackage
        this.classPackage = "package obsidian";

    }

    public void addMethod(String classType) {

        //If the method is a primitive type ignore it.
        if (!((Helpers.PRIMITIVE_OBJECT_NAMES.containsValue(classType))
                || (Helpers.PRIMITIVE_OBJECT_NAMES.containsKey(classType))
                || (primitiveFullName.containsKey(classType.trim())))) {

            if (!(methods.containsKey(classType))) {
                //add
                methods.put(classType, new GlobalEqualityMethod(classType));
            }
        }
    }

    public void addMethod(String classType, GlobalEqualityMethod method) {

        if (!((Helpers.PRIMITIVE_OBJECT_NAMES.containsValue(classType))
                || (Helpers.PRIMITIVE_OBJECT_NAMES.containsKey(classType))
                || (primitiveFullName.containsKey(classType.trim())))) {

            if (!(methods.containsKey(classType))) {
                //add
                methods.put(classType, new GlobalEqualityMethod(classType));
            }
        }
    }

    
    public void addImport(String Import) {
        boolean ignore = false;

        System.out.println("import: " + Import);

        //Make sure: not already in imports
        if (imports.contains(Import)) {
            ignore = true;
        }

        if (Import.compareToIgnoreCase("org.cirdles.obsidian.util.MethodMap") == 0) {
            ignore = true;
        }

        if (!ignore) {
            imports.add(Import);
        }

    }

    @Override
    public String toString() {
        StringBuilder equalityMethodsClass = new StringBuilder();


        //Package Declaration
        equalityMethodsClass.append(classPackage).append(";\n\n");

        //Add imports
        for (int i = 0; i < imports.size(); i++) {
            equalityMethodsClass.append("import ").
                    append(imports.get(i)).
                    append(";\n");
        }

        //Open Header Build
        int inSet = 3;//number of * before and after class name dataInputStream header

        String packageName = classPackage;
        String simplePackageName = "Obsidian Global";
        String headEM = "Equality Methods";

        int eMheaderLength = simplePackageName.length() + //length of simple package name
                (inSet * 2) + 3 + //an inset on either side plus a buffer space on either side and middle
                headEM.length();//length of the file name

        equalityMethodsClass.append("\n//");

        for (int i = 0; i < eMheaderLength; i++) {
            equalityMethodsClass.append("*");
        }

        equalityMethodsClass.append("\n//");

        for (int i = 0; i < eMheaderLength; i++) {
            equalityMethodsClass.append("*");
        }

        equalityMethodsClass.append("\n//");

        for (int i = 0; i < inSet; i++) {
            equalityMethodsClass.append("*");
        }

        equalityMethodsClass.append(" ");
        equalityMethodsClass.append(simplePackageName);
        equalityMethodsClass.append(" ");
        equalityMethodsClass.append(headEM);
        equalityMethodsClass.append(" ");

        for (int i = 0; i < inSet; i++) {
            equalityMethodsClass.append("*");
        }

        equalityMethodsClass.append("\n//");

        for (int i = 0; i < eMheaderLength; i++) {
            equalityMethodsClass.append("*");
        }

        equalityMethodsClass.append("\n//");

        for (int i = 0; i < eMheaderLength; i++) {
            equalityMethodsClass.append("*");
        }
        equalityMethodsClass.append("\n//");
        //end Header Build

        //class declaration
        equalityMethodsClass.append("\n\npublic class ");
        equalityMethodsClass.append(ClassNames.GLOBAL_EQUALITY_METHODS);
        equalityMethodsClass.append("{");

        //Equality Methods
        if (!(methods.containsKey("String"))) {
            this.addMethod("String", new GlobalEqualityMethod(
                    "String", Methods.DEFAULT_STRING_EQUALITY_METHOD));
        }
        
        // Primitives
        
            this.addMethod("byte", new GlobalEqualityMethod(
                    "byte", Methods.DEFAULT_BYTE_EQUALITY_METHOD));


        
            this.addMethod("short", new GlobalEqualityMethod(
                    "short", Methods.DEFAULT_SHORT_EQUALITY_METHOD));
        

        
            this.addMethod("int", new GlobalEqualityMethod(
                    "int", Methods.DEFAULT_INT_EQUALITY_METHOD));
        

        
            this.addMethod("long", new GlobalEqualityMethod(
                    "long", Methods.DEFAULT_LONG_EQUALITY_METHOD));
        

        
            this.addMethod("float", new GlobalEqualityMethod(
                    "float", Methods.DEFAULT_FLOAT_EQUALITY_METHOD));
        

        
            this.addMethod("double", new GlobalEqualityMethod(
                    "double", Methods.DEFAULT_DOUBLE_EQUALITY_METHOD));
        

        
            this.addMethod("boolean", new GlobalEqualityMethod(
                    "boolean", Methods.DEFAULT_BOOLEAN_EQUALITY_METHOD));
        

        
            this.addMethod("char", new GlobalEqualityMethod(
                    "char", Methods.DEFAULT_CHAR_EQUALITY_METHOD));
        

        //Primitive Objects
        
            this.addMethod("Byte", new GlobalEqualityMethod(
                    "Byte", Methods.DEFAULT_BYTE_OBJECT_EQUALITY_METHOD));
        

        
            this.addMethod("Short", new GlobalEqualityMethod(
                    "Short", Methods.DEFAULT_SHORT_OBJECT_EQUALITY_METHOD));
        

        
            this.addMethod("Integer", new GlobalEqualityMethod(
                    "Integer", Methods.DEFAULT_INTEGER_OBJECT_EQUALITY_METHOD));
        

        
            this.addMethod("Long", new GlobalEqualityMethod(
                    "Long", Methods.DEFAULT_LONG_OBJECT_EQUALITY_METHOD));
        

        
            this.addMethod("Float", new GlobalEqualityMethod(
                    "Float", Methods.DEFAULT_FLOAT_OBJECT_EQUALITY_METHOD));
        

        
            this.addMethod("Double", new GlobalEqualityMethod(
                    "Double", Methods.DEFAULT_DOUBLE_OBJECT_EQUALITY_METHOD));
        

        
            this.addMethod("Boolean", new GlobalEqualityMethod(
                    "Boolean", Methods.DEFAULT_BOOLEAN_OBJECT_EQUALITY_METHOD));
        

        
            this.addMethod("Character", new GlobalEqualityMethod(
                    "Character", Methods.DEFAULT_CHARACTER_OBJECT_EQUALITY_METHOD));
        

        
             this.addMethod("Exception", new GlobalEqualityMethod(
                     "Exception", Methods.DEFAULT_EXCEPTION_EQUALITY_METHOD));
        

        
            this.addMethod("Throwable", new GlobalEqualityMethod(
                    "Throwable", Methods.DEFAULT_THROWABLE_EQUALITY_METHOD));
        
        
        

        //Sort and add each method
        for (String s : this.sortMethods()) {

            equalityMethodsClass.append(methods.get(s).getContents());
        }
        
        equalityMethodsClass.append("\n\n");
        equalityMethodsClass.append(Methods.DEFAULT_SINGLETON_ARENOTEQUAL_METHOD);

        equalityMethodsClass.append("\n}");

        return equalityMethodsClass.toString();
    }

    private ArrayList<String> sortMethods() {
        String[] keys = methods.keySet().toArray(new String[0]);
        ArrayList<String> keysArrayList = new ArrayList<String>();
        Collections.addAll(keysArrayList, keys);

        for (int i = 1; i < keysArrayList.size(); i++) {
            int newIndex = i;

            for (int j = i - 1; j >= 0; j--) {
                if (keysArrayList.get(i).compareToIgnoreCase(
                        keysArrayList.get(j)) < 0) {
                    newIndex = j;
                }
            }

            String temp = keysArrayList.get(i);

            keysArrayList.remove(i);

            keysArrayList.add(newIndex, temp);
        }

        //return sorted arraylist
        return keysArrayList;


    }
}
