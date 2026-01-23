public class Task {
    private final String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public void setCompletion(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                             this.isCompleted ? "X" : " ",
                             this.name);
    }
}