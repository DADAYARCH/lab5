package shell.commands;

import shell.CommandAbstract;
import shell.Shell;
import structures.WorkerCollection;

public class InfoCommand extends CommandAbstract {
    public InfoCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    public void execute() {
        shell.printMessage("infoCommand_format",
                workerCollection.getLastSaveDate(), workerCollection.getSize(), workerCollection.getLoadedFrom());
    }
}
