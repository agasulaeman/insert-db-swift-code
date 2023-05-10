package com.permatabank.insert.swift.bank.schedullerjob;

import com.permatabank.insert.swift.bank.insertquery.InsertQuery;
import com.permatabank.insert.swift.bank.updatequery.UpdateQuery;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateSwiftBankJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        UpdateQuery updateQuery = new UpdateQuery();
        try {
            updateQuery.updateDataSwiftBank();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }  catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
