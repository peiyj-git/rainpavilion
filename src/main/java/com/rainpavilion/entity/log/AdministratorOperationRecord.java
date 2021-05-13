package com.rainpavilion.entity.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//管理员操作日志表
@Data
public class AdministratorOperationRecord {
    private int logId;
    private String userName;
    private String logIp;
    private String logContent;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date logDate;
    private String logType;
}
