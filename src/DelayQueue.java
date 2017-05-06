
import java.util.*;

/**
 * Created by qing on 3/19/17.
 */
public class DelayQueue {

    private int maxSlotNums;
    private Task task;
    private int currentSlotNums;
    private HashSet<Task> taskSets;
    private Timer timer = new Timer();
    private int timeDelay;
    private int intevalPeriod = 1;//second
    private Set<?>[] taskQueue;
    public DelayQueue(){
        this(3600);
    }
    public DelayQueue(int maxSlotNums){
        this(maxSlotNums,0);
    }

    public DelayQueue(int maxSlotNums,int timeDelay){
        this.maxSlotNums = maxSlotNums;
        this.timeDelay = timeDelay;
        this.taskQueue = new HashSet<?>[maxSlotNums];
    }
    public int getMaxSlotNums() {
        return maxSlotNums;
    }

    public void setMaxSlotNums(int maxSlotNums) {
        this.maxSlotNums = maxSlotNums;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    //Set taskSet = Collections.synchronizedSet(new HashSet<Task>());

    public int getCurrentSlotNums(){
        return currentSlotNums;
    }

    public void addTask(Task task){
        if(task == null){
            throw new IllegalArgumentException("task");
        }
        int triggerAfterSecond = task.getTriggerAfterSecond();
        int taskIndex = currentSlotNums + triggerAfterSecond;
        if(taskIndex > maxSlotNums - 1)
            taskIndex = taskIndex % maxSlotNums;
        HashSet<Task> taskSets = (HashSet<Task>)this.taskQueue[taskIndex];
        int cycleNum = triggerAfterSecond / maxSlotNums;
        task.setCycleNum(cycleNum);
        taskSets.add(task);

    }

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            HashSet<Task> currentTaskSets = (HashSet<Task>) taskQueue[currentSlotNums];
            for (Task task:currentTaskSets
                 ) {
                int taskCycleNum = task.getCycleNum();
                if(taskCycleNum == 0){
                    task.doTask();
                    currentTaskSets.remove(task);
                }
                else{
                    task.setCycleNum(--taskCycleNum);
                }
            }
            currentSlotNums++;
        }
    };


    public void Start(){
        timer.schedule(timerTask,timeDelay,intevalPeriod);
    }

}
