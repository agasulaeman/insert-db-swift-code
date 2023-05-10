import com.permatabank.insert.swift.bank.schedullerjob.InsertSwiftBankJob;
import com.permatabank.insert.swift.bank.schedullerjob.UpdateSwiftBankJob;
import com.permatabank.insert.swift.bank.updatequery.UpdateQuery;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

public class MainUpdateSwiftCode {
    public static void main(String[] args) {
        UpdateQuery updateQuery = new UpdateQuery();

        try {
            SchedulerFactory schedFact = new StdSchedulerFactory();

            // Get a scheduler instance
            Scheduler scheduler = schedFact.getScheduler();

            // Define the job and tie it to our MyJob class
            JobDetail job = JobBuilder.newJob(UpdateSwiftBankJob.class)
                    .withIdentity("schedullerjob", "com.permatabank.insert.swift.bank")
                    .build();

            // Test Define a trigger that repeats every 5 seconds
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("schedullerjob", "com.permatabank.insert.swift.bank")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * 1-12 *"))
                    .forJob("UpdateSwiftBankJob", "com.permatabank.insert.swift.bank.schedullerjob")
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
