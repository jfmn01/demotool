package com.example.demotool.tool;

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class NewTool {
    private static final String inFilePath1 = "E:\\导数据\\20181129\\111.txt";
    private static final String inFilePath2 = "E:\\导数据\\20181129\\222.txt";

    public static void main(String[] args) {
        //导流标识
        genFileTwo(inFilePath2);
//        genFile(inFilePath2);

    }

    public static void genFile(String inFilePath) {
        File file1 = new File(inFilePath1);
        try {
            contentToTxt(inFilePath, "use UserCenter;\n");
            //读取源文件数据
            List<String> list1 = FileUtils.readLines(file1);
            for (int i = 0; i < list1.size(); i++) {
                Long lUserId = Long.parseLong(list1.get(i));
                //对应表ID
                Long tableId = lUserId / 1000000;
                //拼接sql
                String sql = "UPDATE tbBorrowerExtends" + tableId + " SET tsRefreshTime = NOW() where lUserId =  " + lUserId + ";\n";
                contentToTxt(inFilePath, sql);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void genFileTwo(String inFilePath) {
        File file1 = new File(inFilePath1);
        try {
            //读取源文件数据
            List<String> list1 = FileUtils.readLines(file1);
            for (int i = 0; i < list1.size(); i++) {
                Long lUserId = Long.parseLong(list1.get(i));
                //拼接sql
                String sql = "UPDATE tbDepartmentWhiteList  SET nState = 1 WHERE  nType = 0 AND lDeptId = " + lUserId + ";\n";
                contentToTxt(inFilePath, sql);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 跟据内容生成目标文件
     * @param filePath 目标文件
     * @param content 内容
     */
    public static void contentToTxt(String filePath, String content) {
        // 内容更新
        String s1 = new String();
        try {
            File f = new File(filePath);
            if (f.exists() == false) {
                f.createNewFile();// 不存在则创建
            }
            s1 = content + "";
            BufferedWriter output = new BufferedWriter(new FileWriter(f, true));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
