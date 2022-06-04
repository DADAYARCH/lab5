package shell.commands;

import shell.CommandAbstract;
import shell.Shell;
import structures.WorkerCollection;

public class ClearCommand extends CommandAbstract {
    public ClearCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    public void execute() {
        workerCollection.clear();
    }
}
