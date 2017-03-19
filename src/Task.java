import java.util.UUID;

/**
 * Created by qing on 3/19/17.
 */
public class Task {

    private int cycleNum;
    private IJob job;
    private int triggerAfterSecond;
    private String identifier;
    private UUID uuid;

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

    @Override
    public String toString(){

        return this.uuid.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof Task){
            Task task = (Task)obj;
            return this.uuid == task.uuid;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return uuid.hashCode();
    }

}
