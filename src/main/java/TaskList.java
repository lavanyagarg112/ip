import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasklist;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public void executeCommand(String command) {
        if (command.equals("list")) {
            this.list();
        } else {
            String[] splitwords = command.split(" ", 2);
            String firstCommand = splitwords[0];
            if (firstCommand.equals("mark")) {
                try {
                    int index = Integer.valueOf(splitwords[1]);
                    this.markTask(index);
                } catch (Exception e) {
                    System.out.println("Invalid task number given. Type list to find out the task number");
                }
            } else if (firstCommand.equals("unmark")) {
                try {
                    int index = Integer.valueOf(splitwords[1]);
                    this.unmarkTask(index);
                } catch (Exception e) {
                    System.out.println("Invalid task number given. Type list to find out the task number");
                }
                
            } else if (firstCommand.equals("deadline")) {
                if (splitwords.length == 1) {
                    System.out.println("The description of a deadline cannot be empty");
                    return;
                }
                String[] taskparts = splitwords[1].split(" /by ", 2);
                String name = taskparts[0];
                if (taskparts.length == 1) {
                    System.out.println("The /by of a deadline cannot be empty");
                    return;
                }
                String deadline = taskparts[1];
                if (name.trim().equals("")) {
                    System.out.println("The description of a deadline cannot be empty");
                    return;
                }
                Task task = new Deadline(name, deadline);
                this.add(task);
            } else if (firstCommand.equals("event")) {
                if (splitwords.length == 1) {
                    System.out.println("The description of an event cannot be empty");
                    return;
                }
                String[] taskParts = splitwords[1].split(" /from ", 2);
                if (taskParts.length == 1) {
                    System.out.println("The from and to of an event cannot be empty");
                    return;
                }
                String[] deadlines = taskParts[1].split(" /to ", 2);
                String name = taskParts[0];
                String from = deadlines[0];
                if (deadlines.length == 1) {
                    System.out.println("The from or to of an event cannot be empty");
                    return;
                }
                String to = deadlines[1];
                if (name.trim().equals("")) {
                    System.out.println("The description of an event cannot be empty");
                    return;
                }
                Task task = new Event(name, from, to);
                this.add(task);
            } else if (firstCommand.equals("todo")) {
                if (splitwords.length == 1) {
                    System.out.println("The description of a todo cannot be empty");
                    return;
                }
                String name = splitwords[1];
                if (name.trim().equals("")) {
                    System.out.println("The description of a todo cannot be empty");
                    return;
                }
                Task task = new Todo(name);
                this.add(task);
            } else {
                System.out.println("Invalid command, please try again");
            }
        }
    }

    public void list() {
        System.out.println(this.toString());
    }

    private void add(Task task) {
        this.tasklist.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + this.tasklist.size() + " tasks in the list.");
    }

    private void markTask(int index) {
        Task task = this.tasklist.get(index-1);
        task.mark();
        System.out.println(" Nice! I've marked this task as done: \n" + task);
    }

    private void unmarkTask(int index) {
        Task task = this.tasklist.get(index-1);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet: \n" + task);
    }

    @Override
    public String toString() {
        String tasks = "Here are the tasks in your list: \n";
        for (int i=0; i < tasklist.size(); i++) {
            tasks += (i+1) + ". " + tasklist.get(i) + "\n";
        }
        return tasks;
    }

}
