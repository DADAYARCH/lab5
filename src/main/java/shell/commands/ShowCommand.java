package shell.commands;

import shell.CommandAbstract;
import shell.Shell;
import structures.WorkerCollection;

public class ShowCommand extends CommandAbstract {
    public ShowCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    public void execute() {
        if (workerCollection.isEmpty()){
            shell.printMessage("showCommand_emptyCollection");
        } else{
            shell.pprint(workerCollection.getWorkers());
        }
    }
}
