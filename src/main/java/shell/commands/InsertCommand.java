package shell.commands;

import shell.CommandAbstract;
import shell.Shell;
import shell.WorkerForm;
import structures.WorkerCollection;

public class InsertCommand extends CommandAbstract {
    public InsertCommand(Shell shell, WorkerCollection workerCollection){
        super(shell, workerCollection);
    }

    @Override
    public void execute() {
        WorkerForm workerForm = new WorkerForm(shell);
        workerCollection.put(getMatchGroup(1),workerForm.getWorker());
    }

    @Override
    protected String getCommandRegex() {
        return "insert\\s+(.+)";
    }
}
