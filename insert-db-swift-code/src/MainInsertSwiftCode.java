import com.permatabank.insert.swift.bank.insertquery.InsertQuery;
import com.permatabank.insert.swift.bank.schedullerjob.InsertSwiftBankJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.sql.SQLException;

public class MainInsertSwiftCode {

    public static void main(String[] args) {

        try {
            SchedulerFactory schedFact = new StdSchedulerFactory();

            // Get a scheduler instance
            Scheduler scheduler = schedFact.getScheduler();

            // Define the job and tie it to our MyJob class
            JobDetail job = JobBuilder.newJob(InsertSwiftBankJob.class)
                    .withIdentity("schedullerjob", "com.permatabank.insert.swift.bank")
                    .build();
            // Test Define a trigger that repeats At 00:00 in every month from January through December.
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("schedullerjob", "com.permatabank.insert.swift.bank")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * 1-12 *"))
                    .forJob("InsertSwiftBankJob", "com.permatabank.insert.swift.bank.schedullerjob")
                    .build();
            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            // Start the scheduler
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}