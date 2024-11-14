public class Process {
    int id;
    int burstTime;
    int priority;
    int waitingTime;
    int turnaroundTime;

    public Process(int id, int burstTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.priority = priority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}