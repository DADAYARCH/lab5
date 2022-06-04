package shell;


import shell.Command;
import org.jetbrains.annotations.NotNull;
import structures.WorkerCollection;
import shell.Shell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


abstract public class CommandAbstract implements Command {
    protected WorkerCollection workerCollection;
    protected Shell shell;
    protected Matcher commandMatcher;

    public CommandAbstract(Shell shell, WorkerCollection workerCollection) {
        this.shell = shell;
        this.workerCollection = workerCollection;
    }

    public String getMatchGroup(int groupNumber) {
        if (commandMatcher != null) {
            return commandMatcher.group(groupNumber);
        } else {
            throw new RuntimeException("Unexpected Error");
        }
    }

    public boolean isThisCommand(String string) {
        Pattern pattern = Pattern.compile(getCommandRegex(), Pattern.CASE_INSENSITIVE);
        commandMatcher = pattern.matcher(string);
        return commandMatcher.matches();
    }

    protected @NotNull String getCommandRegex() {
        return getCommandName();
    }

    @Override
    public String getCommandName() {
        String className = this.getClass().getTypeName();
        Matcher matcher = Pattern.compile("([\\d\\w]+)Command$").matcher(className);
        if (!matcher.find()) {
            throw new RuntimeException("Rename command class or override getCommandName() method");
        }
        String commandName = matcher.group(1);
        commandName = commandName.replaceAll(
                "(?<=[A-Z])(?=[A-Z][a-z])|(?<=[^A-Z])(?=[A-Z])|(?<=[A-Za-z])(?=[^A-Za-z])", "_");
        return commandName.toLowerCase();
    }

    public abstract void execute();
}
