package team.group3.codeanalysis.entity;

import java.util.ArrayList;
import java.util.List;

/***
 * Declaration主要记录类的函数声明
 * 类的函数声明包含函数声明所在包、所在类、函数名字、函数各个参数类型与参数名（多值属性）
 * @author szc
 * @version 1.0
 */
public class Declaration {

    //方法或是调用起始所在行
    public int line;

    public String declarationName;

    public List<FunctionParameter> params;

    public Declaration(int line, String declarationName, List<FunctionParameter> params) {
        this.line = line;
        this.declarationName = declarationName;
        this.params = params;
    }
}
