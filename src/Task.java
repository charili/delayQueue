/**
 * Created by qing on 3/19/17.
 */
public class Task {

    private int cycleNum;
    private IJob job;
    private int triggerAfterSecond;

    public int getTriggerAfterSecond() {
        return triggerAfterSecond;
    }

    public void setTriggerAfterSecond(int triggerAfterSecond) {
        this.triggerAfterSecond = triggerAfterSecond;
    }


    public int getCycleNum() {
        return cycleNum;
    }

    public void setCycleNum(int cycleNum) {
        this.cycleNum = cycleNum;
    }

    public IJob getJob() {
        return job;
    }

    public void setJob(IJob job) {
        this.job = job;
    }

    public void doTask(){
        this.job.DoJob();
    }
}
