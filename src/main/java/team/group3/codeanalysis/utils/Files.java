package team.group3.codeanalysis.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Files类主要是用于遍历一个文件目录下所有以.java结尾的文件
 * 遍历的过程中注意需要递归遍历
 * @author szc
 * @version 1.0
 * test already passed
 */


public class Files {
    //表示以“.java”结束的文件
    public final String suffix = ".java";
    //要遍历的目录名称
    public String directory;
    List<String> allFilePath=new ArrayList<>();
    //constructor
    public Files(String dir)
    {
        directory=dir;
    }
    //返回改文件夹下所有的文件名
    public List<String> getFilesName() throws FileNotFoundException {
        File folder = new File(directory);
        if (folder.exists() && folder.isDirectory()) {
            allFilePath.clear();
            listFilesRecursively(folder);
            return allFilePath;
        } else {
            throw new FileNotFoundException();
        }

    }

    public void listFilesRecursively(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFilesRecursively(file); // 递归遍历子文件夹
                } else if (file.isFile() && file.getName().endsWith(suffix)) {
                    allFilePath.add(file.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Files f=new Files("C:\\Users\\Administrator\\Desktop\\javatest");
        var x=f.getFilesName();
        for(int i=0;i<x.size();i++)
        {
            System.out.println(x.get(i));
        }
    }





}
