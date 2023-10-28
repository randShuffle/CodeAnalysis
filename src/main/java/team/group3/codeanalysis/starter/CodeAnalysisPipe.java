package team.group3.codeanalysis.starter;
import team.group3.codeanalysis.algo.InvokeGraph;
import team.group3.codeanalysis.entity.Declaration;

/***
 * CodeAnalysisPipe主要负责对其他几个包里类的调度
 * 需要了解整个业务、算法实现的流程（不用具体的实现）
 * 能够成功调用各个类提供的接口即可
 * 注意异常处理，保证robustness
 * @author szc
 * @version 1.0
 */

//team.group3.codeanalysis.starter.CodeAnalysisPipe    main(String[])
//team.group3.codeanalysis.starter.CodeAnalysisPipe    main(int)





public class CodeAnalysisPipe {
    public InvokeGraph invokeGraph;

    public CodeAnalysisPipe(String dir)
    {
        invokeGraph=new InvokeGraph(dir);
    }


    public static void main(String[] args)
    {
        //cmd interface
        System.out.println("------------------");
        //plus:所有调用接口获得的entity对象或是entity对象数组都需经过Vo展示给前端
        //eg:这里new Declaration（）乱写的，其实是通过调invokeGraph的接口实现
        // demoVo=new DemoVo(new Declaration());
        //System.out.println(demoVo.name+demoVo.line);
    }



}
