package shell.commands;

import shell.CommandAbstract;
import shell.Shell;
import structures.WorkerCollection;

public class ExitCommand extends CommandAbstract {
    public ExitCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    public void execute() {
        shell.stop();

    }
}
