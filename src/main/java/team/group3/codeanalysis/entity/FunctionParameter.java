package team.group3.codeanalysis.entity;

/***
 * FunctionParameter记录函数声明或是函数调用传入参数的类型以及名字和所在行数
 * @author szc
 * @version 1.0
 */

public class FunctionParameter {
    //留一手，万一解析不出来类型就用UNKOWN_TYPE
    public static final String UNKOWN_TYPE="unknown";
    public String paramType;
    public String paramName;

    public int line;

    public FunctionParameter(String paramType, String paramName, int line) {
        this.paramType = paramType;
        this.paramName = paramName;
        this.line = line;
    }
}
