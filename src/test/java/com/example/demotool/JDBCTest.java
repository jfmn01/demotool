package com.example.demotool;

import com.example.demotool.entity.TbBorrowers;
import com.example.demotool.tool.Tool;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Log4j2
public class JDBCTest {
    private static final String inFilePath1 = "E:\\导数据\\20181207\\111.txt";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void selectTest1() {
        Long lUserId = 8305465L;
        Long tableId = lUserId / 1000000;
        String sql = "select count(1) countRow from tbBorrowerExtends"+tableId+" where lUserId = "+ lUserId;
        List<TbBorrowers> tbBorrowers = jdbcTemplate.queryForList(sql, TbBorrowers.class);
        System.out.println(tbBorrowers.size());
    }
    @Test
    public void selectTest2() {
        File file1 = new File(inFilePath1);
        try {
            //读取源文件数据
            String sql = "select * from (SELECT lUserId, tsRefreshTime, nAddedBetterCustomer  FROM tbCreditRecord  WHERE tsRefreshTime>'2018-10-26' AND nAddedBetterCustomer>0 ORDER BY lId desc) a GROUP BY lUserId";
            List<Map<String, Object>> tbBorrowers = jdbcTemplate.queryForList(sql);
            for (int i = 0; i < tbBorrowers.size(); i++) {
                Map tbCreditRecord = tbBorrowers.get(i);
                String strAddedBetterCustomer = "是";
                if ("2".equals(tbCreditRecord.get("nAddedBetterCustomer").toString())) {
                    strAddedBetterCustomer = "否";
                }
                //拼接sql
                String sql2 = tbCreditRecord.get("lUserId") + "," + tbCreditRecord.get("tsRefreshTime") + "," + strAddedBetterCustomer + "\n";
                Tool.contentToTxt(inFilePath1, sql2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void selectTest3() {
        File file1 = new File(inFilePath1);
        try {
            //读取源文件数据
            List<String> list1 = FileUtils.readLines(file1);
            for (int i = 0; i < list1.size(); i++) {
                String string = list1.get(i).toString();
                String sql = "select count(1)  coun from  tbDepartmentWhiteList where lDeptId = " + string;
                for (Map<String, Object> stringObjectMap : jdbcTemplate.queryForList(sql)) {
                    Long coun = (Long) stringObjectMap.get("coun");
                    if (coun >1) {
                        log.info(stringObjectMap.get("coun") + "             "+ string);
                    }
//                    BigInteger lId = (BigInteger) stringObjectMap.get("lId");
//                    String strDeptName = (String) stringObjectMap.get("strDeptName");
//                    System.out.println(lId + "                 " + strDeptName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
