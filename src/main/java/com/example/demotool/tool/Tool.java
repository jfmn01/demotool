package com.example.demotool.tool;


import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class Tool {
    private static final String inFilePath1 = "E:\\导数据\\20181213\\111.txt";
    private static final String inFilePath2 = "E:\\导数据\\20181213\\导流B客户.sql";
    private static final String inFilePath3 = "E:\\导数据\\20181213\\画像A客户.sql";
    private static final String inFilePath4 = "E:\\导数据\\20181213\\画像B客户.sql";
    private static final String inFilePath5 = "E:\\导数据\\20181213\\画像C客户.sql";
    private static final String inFilePath6 = "E:\\导数据\\20181213\\画像D客户.sql";

    public static void main(String[] args) {
        //导流标识
//        genFile(inFilePath2, "B");
//        genFile(inFilePath3, "C");
//        genFile(inFilePath4, "D");
//        genFile(inFilePath5, "E");

        //画像标识
//        genFileWith(inFilePath3, "A");
//        genFileWith(inFilePath4, "B");
//        genFileWith(inFilePath5, "C");
        genFileWith(inFilePath6, "D");
    }

    /**
     * 生成导流数据sql
     * @param inFilePath 目标文件
     * @param strGuideCust 导流标识 （B, C, D, E）
     */
    public static void genFile(String inFilePath, String strGuideCust) {
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
                String sql = "INSERT INTO tbBorrowerExtends" + tableId + " (lUserId, strGuideCust, nReserveField5)VALUES( " + lUserId + ", '" + strGuideCust + "', 2)on duplicate key update strGuideCust=VALUES(strGuideCust), nReserveField5=VALUES(nReserveField5);\n";
                contentToTxt(inFilePath, sql);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成客户画像
     * @param inFilePath 目标文件
     * @param strReserveField1 画像标识(A, B, C, D)
     */
    public static void genFileWith(String inFilePath, String strReserveField1) {
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
                String sql = "INSERT INTO tbBorrowerExtends" + tableId + " (lUserId, strReserveField1, strReserveField2)VALUES( " + lUserId + ", '" + strReserveField1 + "', '"+lUserId+"')on duplicate key update strReserveField1=VALUES(strReserveField1), strReserveField2=VALUES(strReserveField2);\n";
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
