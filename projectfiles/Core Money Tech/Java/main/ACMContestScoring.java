package week3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ACMContestScoring {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Map<String, TaskState> tasks = new HashMap<>();
        String[] input = in.nextLine().split(" ");
        int time;
        while ((time = Integer.parseInt(input[0])) != -1) {
            TaskState taskState = new TaskState(input[2], time);

            if (tasks.containsKey(input[1])) {
                TaskState taskState1 = tasks.get(input[1]);

                if (taskState.state.equals("right")) {
                    if (taskState1.state.equals("wrong")) {
                        taskState1.penalties++;
                        taskState1.time = taskState.time;
                        taskState1.state = "right";
                    }
                } else {
                    if (taskState1.state.equals("wrong")) {
                        taskState1.penalties++;
                        taskState1.time = taskState.time;
                    }
                }
            } else {
                tasks.put(input[1], taskState);
            }

            input = in.nextLine().split(" ");
        }

        int solved = 0;
        int points = 0;

        for (TaskState task : tasks.values()) {
            if (task.state.equals("right")) {
                solved++;
                points += task.time + (task.penalties * 20);
            }
        }

        System.out.println(solved + " " + points);
    }

    private static class TaskState{
        public int penalties;
        public String state;
        public int time;

        public TaskState(String state, int time) {
            this.state = state;
            this.time = time;
            this.penalties = 0;
        }
    }
}
