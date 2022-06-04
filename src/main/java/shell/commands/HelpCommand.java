package shell.commands;

import shell.CommandAbstract;
import shell.Shell;
import structures.WorkerCollection;

public class HelpCommand extends CommandAbstract {
    public HelpCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    public void execute() {
        shell.printMessage("helpCommand_text");
    }
}
