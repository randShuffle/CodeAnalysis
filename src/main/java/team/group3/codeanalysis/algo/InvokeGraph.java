package team.group3.codeanalysis.algo;
import team.group3.codeanalysis.entity.*;
import team.group3.codeanalysis.parser.ParseFile;

import java.util.ArrayList;
import java.util.HashMap;

/***
 * InvokeGraph主要是以邻接表的形式存储各个函数之间的调用关系（depth=1）
 * 随后通过bfs进行depth的搜索
 * @author szc
 * @version 1.0
 */

public class InvokeGraph {

    //顶点的顺序表
    public ArrayList<Declaration> declarations;

    //使用hashmap提升效率：空间换时间（要不了多少空间的）
    HashMap<Declaration,Integer> vertexIndexInDeclarations;

    public ArrayList<ClassInfo> classInfos;

    //有向图的邻接表
    //比如说link【3】=【5，7，8】
    //意思就是declarations【3】这个顶点与declarations【5】，declarations【7】，declarations【8】相连
    public ArrayList<Integer> link;

    //有向图转置的邻接表
    public ArrayList<Integer> reverseLink;

    public ParseFile parseFile;

    //生成ParseFile对象，调用这个对象提供的接口
    //hashmap的生成我写掉了，后面link和reverselink的初始化还要写！
    //link和reverselink的初始化分两个private函数写，不要全堆在constructor里！

    public InvokeGraph(String dir)
    {
//        parseFile=new ParseFile(dir);
//        //initialize declarations
//        declarations=parseFile.getDeclarations();
//        //initialize hashmap
//        vertexIndexInDeclarations = new HashMap<>();
//        for (int i = 0; i < declarations.size(); i++) {
//            vertexIndexInDeclarations.put(declarations.get(i), i);
//        }
//        classInfos=parseFile.getClassInfos();
//        //remain realizing...
//        //注意：在生成link的过程当中，一定要注意重复性的检查！！！
//        //因为某个函数声明的body里可能不止一次调用了同一个函数！

    }





    //bfs遍历某一个函数调用或被调用
    //用或被调用本质上只有是遍历link还是traverselink的区别
    //故加入isTraverse使得该函数可以复用
    //private:仅让自己类内的函数调用，不对外提供
    //此处可以调用java包内的队列来协助完成bfs
    //这个return的type是类似【depth，list【declarations】】的list或map
    private void bfs(int depth,boolean isTraverse)
    {

    }


    //寻找某个函数的所有调用，为了防止递归调用，这里函数里可以限制depth的最大深度为10
    //这个函数是为了给问题b用的
    private void getAllInvokes()
    {

    }




    public void getOriSourceParams(String param,int depth)
    {

    }






}
