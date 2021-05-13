package com.rainpavilion;

import com.rainpavilion.entity.log.AdministratorOperationRecord;
import com.rainpavilion.mapper.AdminMapper;
import com.rainpavilion.service.AdministratorOperationRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test3_log {
    @Autowired
    private AdministratorOperationRecordService administratorOperationRecordService;

    //记录管理员操作日志
    @Test
    public void test1_add(){
        AdministratorOperationRecord administratorOperationRecord = new AdministratorOperationRecord();
        administratorOperationRecord.setUserName("11");
        administratorOperationRecord.setLogIp("22");
        administratorOperationRecord.setLogContent("33");
        administratorOperationRecord.setLogType("44");
        administratorOperationRecordService.add(administratorOperationRecord);

        List<AdministratorOperationRecord> administratorOperationRecords = administratorOperationRecordService.administratorOperationRecordList();
        administratorOperationRecords.forEach(System.err::println);

    }




}
