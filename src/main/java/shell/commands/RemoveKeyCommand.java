package shell.commands;

import org.jetbrains.annotations.NotNull;
import shell.CommandAbstract;
import shell.Shell;
import structures.WorkerCollection;

public class RemoveKeyCommand extends CommandAbstract {
    public RemoveKeyCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    public String getCommandName(){
        return "remove_key";
    }

    @Override
    protected @NotNull String getCommandRegex(){
        return "^remove_key\\s+([\\w\\d]+)$";
    }

    @Override
    public void execute() {
        String key = getMatchGroup(1);
        workerCollection.remove(key);
    }
}
