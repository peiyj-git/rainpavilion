package com.rainpavilion.service.impl;

import com.rainpavilion.entity.log.AdministratorOperationRecord;
import com.rainpavilion.mapper.AdministratorOperationRecordMapper;
import com.rainpavilion.service.AdministratorOperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorOperationRecordServiceImpl implements AdministratorOperationRecordService {
    @Autowired
    private AdministratorOperationRecordMapper administratorOperationRecordMapper;

    @Override
    public void add(AdministratorOperationRecord administratorOperationRecord) {
        administratorOperationRecordMapper.add(administratorOperationRecord);
    }


    @Override
    public List<AdministratorOperationRecord> administratorOperationRecordList() {
        return administratorOperationRecordMapper.administratorOperationRecordList();
    }

}


