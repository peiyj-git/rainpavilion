package com.rainpavilion.mapper;

import com.rainpavilion.entity.log.AdministratorOperationRecord;

import java.util.List;

public interface AdministratorOperationRecordMapper {
    //添加操作日志
    void add(AdministratorOperationRecord administratorOperationRecord);

    //查询日志
    List<AdministratorOperationRecord> administratorOperationRecordList();

}
