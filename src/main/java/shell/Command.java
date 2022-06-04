package shell;

public interface Command {
    boolean isThisCommand(String string);
    void execute();
    String getCommandName();
}
