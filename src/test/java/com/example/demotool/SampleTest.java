package com.example.demotool;

import com.cdoframework.cdolib.security.AES;
import com.example.demotool.dao.UserMapper;
import com.example.demotool.entity.User;
import com.example.demotool.utils.FileOperation;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    private static final String inFilePath1 = "E:\\导数据\\20181116\\111.txt";
    private static final String inFilePath2 = "E:\\导数据\\20181116\\222.txt";

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        File file1 = new File(inFilePath1);
        try {
            //读取源文件数据
            List<String> list1 = FileUtils.readLines(file1);
            for (int i = 0; i < list1.size(); i++) {
                String string = list1.get(i).toString();
                System.out.println(("----- queryByStrMobile method test ------"));
                List<User> plainUsers = userMapper.queryByStrMobile(string);
                Assert.assertEquals(1, plainUsers.size());
                plainUsers.forEach(System.out::println);
                String content = "";
                for (User plainUser : plainUsers) {
                    content += plainUser.getLId() + "\n";
                }
                FileOperation.contentToTxt(inFilePath2,  content);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void batchDec() {
        AES a=new AES();
        a.init("DECSECURITYKEYABCDEFG", "EiJPWIgQQDgoJXlRy91SZncpdZgwQEHi");

        batchDecVersion2(a);
    }

    /**
     * 第二版解密（批量）
     * @param a
     */
    public static void batchDecVersion2(AES a){

        try {
            File file1 = new File(inFilePath1);
            List<String> list1 = FileUtils.readLines(file1);

            for(int i=0;i<list1.size();i++){
                String[] array = list1.get(i).split(",");
                System.out.println("array[0]:"+array[0]);
                System.out.println("array[0]:"+array[0].equals(""));
                String desc = "";
                if(!array[0].equals("")) {
                    desc = a.decode(array[0]);
                }
                FileOperation.contentToTxt(inFilePath2, desc + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
