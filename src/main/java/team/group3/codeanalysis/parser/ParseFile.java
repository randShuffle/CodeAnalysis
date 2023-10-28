package team.group3.codeanalysis.parser;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import team.group3.codeanalysis.entity.*;
import team.group3.codeanalysis.utils.Files;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.types.ResolvedType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @author szc
 * @version 1.0
 */


//public class ParseFile {
//
//    public Files fileUtil;
//
//    //String:eg://parser.ParseFile
//    public HashMap<String,CompilationUnit> compilationUnits=new HashMap<>();
//
//
//    //String eg:parser.ParseFile(class)
//    HashMap<String,List<Declaration>> declarationHashMap=new HashMap<>();
//
//    private void storeCompilationUnitSingle(String path) throws Exception {
//
//        CompilationUnit compilationUnit=StaticJavaParser.parse(path);
//        String package_name=getPackage(compilationUnit);
//        List<ClassOrInterfaceDeclaration> classDeclarations = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);
//        for (ClassOrInterfaceDeclaration classDeclaration : classDeclarations)
//        {
//            String class_name=classDeclaration.getNameAsString();
//            List<MethodDeclaration> methodDeclarations = classDeclaration.getMethods();
//            for (MethodDeclaration methodDeclaration : methodDeclarations) {
//                String methodName = methodDeclaration.getNameAsString();
//                List<FunctionParameter> funcParams = getDeclarationParams(methodDeclaration);
//                String whole_name=package_name+"."+class_name;
//                if (declarationHashMap.containsKey(whole_name)) {
//                    List<Declaration> declarationList = declarationHashMap.get(whole_name);
//                    declarationList.add(new Declaration(methodDeclaration.getBegin().get().line,
//                            whole_name+"."+methodName,funcParams));
//                }
//                else
//                {
//                   ;
//
//                }
//
//            }
//
//        }
//
//
//
//
//
//
//
//    }
//
//

//
//
//    //就是要找到声明
//    private String findParamType(String paramName,MethodDeclaration method)
//    {
//        //先在函数内的方法寻找
//        List<Statement> statements = method.getBody().get().getStatements();
//        for (Statement statement : statements) {
//            if (statement instanceof ExpressionStmt) {
//                Expression expression = ((ExpressionStmt) statement).getExpression();
//                if (expression instanceof VariableDeclarationExpr) {
//                    VariableDeclarationExpr variableDeclaration = (VariableDeclarationExpr) expression;
//                    NodeList<VariableDeclarator> variables = variableDeclaration.getVariables();
//                    for (VariableDeclarator variable : variables) {
//                        if (variable.getNameAsString().equals(paramName)) {
//                            return variable.getType().asString();
//                        }
//                    }
//                }
//            }
//        }
//
//
//        //然后在函数声明中找
//        NodeList<Parameter> parameters = method.getParameters();
//        for (Parameter parameter : parameters) {
//            if (parameter.getName().asString().equals(paramName)) {
//                return parameter.getType().asString();
//            }
//        }
//
//        //最后在类的成员或静态变量里找
//        ClassOrInterfaceDeclaration classDeclaration = (ClassOrInterfaceDeclaration) method.getParentNode().get();
//        List<FieldDeclaration> fieldDeclarations = classDeclaration.getFields();
//        for (FieldDeclaration fieldDeclaration : fieldDeclarations) {
//            for (VariableDeclarator variable : fieldDeclaration.getVariables()) {
//                if (variable.getNameAsString().equals(paramName)) {
//                    return variable.getType().asString();
//                }
//            }
//        }
//
//
//
//
//
//        return null;
//
//    }
//
//



//

//
//    private List<Variable> getClassMemberVariables(ClassOrInterfaceDeclaration classDeclaration)
//    {
//        List<Variable>variableList=new ArrayList<>();
//        // 获取类的成员变量声明
//        List<FieldDeclaration> fieldDeclarations = classDeclaration.getFields();
//        for (FieldDeclaration fieldDeclaration : fieldDeclarations) {
//            // 获取变量的名称和类型和初始值
//            List<VariableDeclarator> variables = fieldDeclaration.getVariables();
//            for (VariableDeclarator variable : variables) {
//                String variableName = variable.getNameAsString();
//                String variableType = variable.getType().toString();
//                String variableValue = variable.getInitializer().toString();
//
//                //true???
//                variableList.add(new Variable(variableType,variableName,variableValue,
//                        false,true,variable.getBegin().get().line));
//            }
//        }
//        return variableList;
//
//    }
//
//
//

//}

public class ParseFile
{
    public Files fileUtil;

    public ParseFile(String dir) {
        fileUtil=new Files(dir);
    }

    //package.class
    public HashMap<String,ClassOrInterfaceDeclaration>classCompileInfo;

    public HashMap<Declaration,List<Declaration>> declarations;

    //List<Declaration>里可能会有重复元素,在algo里自己处理
    public HashMap<Declaration,List<Declaration>> getDeclarationsInvokes()
    {
        return null;
    }


    private void storeClassCompileInfo() throws FileNotFoundException {
        List<String> paths=fileUtil.getFilesName();


        for(int i=0;i<paths.size();i++)
        {
            CompilationUnit compilationUnit=StaticJavaParser.parse(paths.get(i));
            List<PackageDeclaration> packageDeclarations = compilationUnit.findAll(PackageDeclaration.class);
            String package_name="";
            for (PackageDeclaration packageDeclaration : packageDeclarations) {
                package_name = packageDeclaration.getNameAsString();
                break;
            }

            List<ClassOrInterfaceDeclaration> classDeclarations = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);
            for (ClassOrInterfaceDeclaration classDeclaration : classDeclarations)
            {
                String package_and_class_name=package_name+"."+classDeclaration.getNameAsString();
                classCompileInfo.put(package_and_class_name,classDeclaration);
            }

        }
    }


    private void storeDeclarationsWithoutInvokes()
    {
        for (Map.Entry<String,ClassOrInterfaceDeclaration> entry : classCompileInfo.entrySet()) {
            String key = entry.getKey();
            ClassOrInterfaceDeclaration value = entry.getValue();
            storeDeclarationsWithoutInvokesInOneClass(value,key);
        }
    }

    private void storeDeclarationsWithoutInvokesInOneClass(ClassOrInterfaceDeclaration classOrInterfaceDeclaration,String packageAndClassName)
    {
        List<MethodDeclaration> methods = classOrInterfaceDeclaration.getMethods();
        //一个类中的所有的函数声明
        for (MethodDeclaration method : methods) {
            String methodName = method.getNameAsString();
            int line=method.getBegin().get().line;
            String declarationName=packageAndClassName+"."+methodName;
            List<Parameter> parameters = method.getParameters();
            List<FunctionParameter>functionParameters=new ArrayList<>();
            //函数声明参数
            for (Parameter parameter : parameters) {
                String functionParamName=parameter.getNameAsString();
                String functionParamType=parameter.getTypeAsString();
                int param_line=parameter.getBegin().get().line;
                functionParameters.add(new FunctionParameter(functionParamType,functionParamName,param_line));
            }
            Declaration declaration=new Declaration(line,declarationName,functionParameters);

            declarations.put(declaration,null);
        }



    }


    private void travel()
    {
        for (Map.Entry<String,ClassOrInterfaceDeclaration> entry : classCompileInfo.entrySet()) {
            ClassOrInterfaceDeclaration classOrInterfaceDeclaration = entry.getValue();
            List<MethodDeclaration> methods = classOrInterfaceDeclaration.getMethods();
            //遍历该类下的所有函数声明
            for (MethodDeclaration method : methods) {
                List<MethodCallExpr> methodCalls = method.findAll(MethodCallExpr.class);
                //遍历该函数声明下的所有函数调用
                for (MethodCallExpr methodCall : methodCalls) {
                    //函数调用的名字
                    String methodCallName = methodCall.getNameAsString();
                    List<String> argumentNames = new ArrayList<>();
                    //函数调用参数的名字
                    NodeList<Expression> arguments = methodCall.getArguments();
                    for (Expression argument : arguments) {
                        if (argument.isNameExpr()) {
                            String argumentName = ((NameExpr) argument).getNameAsString();
                            argumentNames.add(argumentName);
                        }
                    }
                }





            }




        }
    }









}
