import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Process> processes = new ArrayList<Process>();
        processes.add(new Process(1,2,2));
        processes.add(new Process(2,1,1));
        processes.add(new Process(3,8,4));
        processes.add(new Process(4,4,2));
        processes.add(new Process(5,5,3));
        List<Process> fcfsProcesses = new ArrayList<>(processes);
        calculateFCFS(fcfsProcesses);
        printResults(fcfsProcesses, "FCFS");

        List<Process> sjfProcesses = calculateSJF(new ArrayList<>(processes));
        printResults(sjfProcesses, "SJF");
    }

    public static void calculateFCFS(List<Process> processes) {
        int currentTime = 0;
        for(Process p : processes) {
            p.waitingTime = currentTime;
            p.turnaroundTime = currentTime + p.burstTime;
            currentTime += p.burstTime;
        }
    }

    public static List<Process> calculateSJF(List<Process> processes) {
        List<Process> temp = new ArrayList<>(processes);
        List<Process> result = new ArrayList<>();
        int currentTime = 0;

        while(!temp.isEmpty()) {
            int shortestIndex = 0;
            for(int i = 1; i < temp.size(); i++) {
                if(temp.get(i).burstTime < temp.get(shortestIndex).burstTime) {
                    shortestIndex = i;
                }
            }

            Process shortestProcess = temp.get(shortestIndex);
            shortestProcess.waitingTime = currentTime;
            shortestProcess.turnaroundTime = currentTime + shortestProcess.burstTime;
            currentTime += shortestProcess.burstTime;
            result.add(shortestProcess);
            temp.remove(shortestIndex);
        }
        return result;
    }

    public static void printResults(List<Process> processes, String algorithm) {
        System.out.println("--------------- " + algorithm + " ---------------");
        System.out.println("Process ID | Waiting Time | Turnaround Time");

        double avgWait = 0, avgTurnaround = 0;
        for(Process p : processes) {
            System.out.printf("    %d     |      %d      |       %d%n",
                    p.id, p.waitingTime, p.turnaroundTime);
            avgWait += p.waitingTime;
            avgTurnaround += p.turnaroundTime;
        }

        System.out.printf("%nAverage Waiting Time: %.2f%n", avgWait/processes.size());
        System.out.printf("Average Turnaround Time: %.2f%n", avgTurnaround/processes.size());
    }
}