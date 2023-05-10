import com.permatabank.insert.swift.bank.schedullerjob.InsertSwiftBankJob;
import com.permatabank.insert.swift.bank.schedullerjob.UpdateSwiftBankJob;
import com.permatabank.insert.swift.bank.updatequery.UpdateQuery;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

public class MainUpdateSwiftCode {
    public static void main(String[] args) {

        try {
            SchedulerFactory schedFact = new StdSchedulerFactory();

            // Get a scheduler instance
            Scheduler scheduler = schedFact.getScheduler();

            // Define the job and tie it to our MyJob class
            JobDetail job = JobBuilder.newJob(UpdateSwiftBankJob.class)
                    .withIdentity("schedullerjob", "com.permatabank.insert.swift.bank.schedullerjob")
                    .build();

            // Test Define a trigger that repeats every At 00:00 in every month from January through December. 0 0 * 1-12 *
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.monthlyOnDayAndHourAndMinute(10,17,49))
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
