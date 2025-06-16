
public class Main {
    public static void main(String[] args) {

        TaskManager tm = new TaskManager();
        User user = new User("Alex", User.Role.USER);
        tm.login(user);
        tm.start();
    }
}