import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskManager {

    private Map<Integer, Task> taskMap = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private  int taskCounter = 1;
    private  User currentUser ;

    public void login(User user){
        this.currentUser = user;
    }

    public void start(){
        while (true){
            System.out.println("\n1. Creat Task\n2. Update\n3. Delete \n4. View One\n5. View All\n0. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice){
                case 1:
                    createTask();
                    break;
                case 2:
                    updateTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    viewTask();
                    break;
                case 5:
                    viewAllTasks();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong choice !!!");
            }
        }
    }
    private void  createTask(){
        System.out.println("Task type (1-Limited, 2-Repetable, 3-Basic");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.println("Title: ");
        String title = scanner.nextLine();

        System.out.println("Description: ");
        String desc = scanner.nextLine();

        Task task = null;

        switch(type){
            case 1:
                System.out.println("Deadline: ");
                LocalDateTime deadline = LocalDateTime.parse(scanner.nextLine());
                task = new LimitedTimeTask(taskCounter ++, title,desc, currentUser.getUsername(), deadline);
                break;
            case 2:
                System.out.print("Repeatable pattern: ");
                String pattern = scanner.nextLine();
                task = new RepeatableTask(taskCounter++, title, desc, currentUser.getUsername(), pattern);
                break;
            case 3:
                task = new BasicTask(taskCounter++, title, desc, currentUser.getUsername());
                break;

        }
        taskMap.put(task.getId(), task);
        System.out.println("Created Task!");
    }

    private void updateTask() {
        System.out.print("Task's title: ");
        String title = scanner.nextLine();
        Task task = findTask(title);

        if (task == null) {
            System.out.println("Task does not exist!");
            return;
        }

        if (!canAccess(task)) return;

        System.out.print("New description: ");
        task.setDescription(scanner.nextLine());

        if (task instanceof LimitedTimeTask) {
            System.out.print("New deadline: ");
            ((LimitedTimeTask) task).setDeadline(LocalDateTime.parse(scanner.nextLine()));
        }

        System.out.println("Task updated");
    }
    private void deleteTask() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        Task task = findTask(title);

        if (task == null) {
            System.out.println("Task does not exist.");
            return;
        }

        if (!canAccess(task)) return;

        taskMap.remove(task.getId());
        System.out.println("Delete");
    }

    private void viewTask() {
        System.out.print("Task's title: ");
        String title = scanner.nextLine();
        Task task = findTask(title);

        if (task == null) {
            System.out.println("Task does not exist.");
            return;
        }

        System.out.println(task);
    }
    private void viewAllTasks() {
        if (taskMap.isEmpty()) {
            System.out.println("Tasks do not exist.");
            return;
        }

        boolean found = false;
        for (Task task : taskMap.values()) {
            if (task.getOwnerUsername().equals(currentUser.getUsername()) ||
                    currentUser.getRole() == User.Role.ADMIN) {
                System.out.println("---------------");
                System.out.println(task);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching tasks found.");
        }
    }


    private Task findTask(String title) {
        for (Task t : taskMap.values()) {
            if (t.getTitle().equalsIgnoreCase(title)) return t;
        }
        return null;
    }

    private boolean canAccess(Task task) {
        if (!task.getOwnerUsername().equals(currentUser.getUsername()) &&
                currentUser.getRole() != User.Role.ADMIN) {
            System.out.println("You do not have access.");
            return false;
        }
        return true;
    }

}
