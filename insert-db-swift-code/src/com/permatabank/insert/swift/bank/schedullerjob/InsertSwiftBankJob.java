package com.permatabank.insert.swift.bank.schedullerjob;

import com.permatabank.insert.swift.bank.insertquery.InsertQuery;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.sql.SQLException;

public class InsertSwiftBankJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        InsertQuery insertQuery = new InsertQuery();
        try {
            insertQuery.insertDataListSwiftBank();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
