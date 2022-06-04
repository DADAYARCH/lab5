package shell.commands;

import org.jetbrains.annotations.NotNull;
import shell.CommandAbstract;
import shell.Shell;
import shell.WorkerForm;
import structures.Worker;
import structures.WorkerCollection;

public class ReplaceIfLowerCommand extends CommandAbstract {
    public ReplaceIfLowerCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    public void execute() {
        WorkerForm workerForm = new WorkerForm(shell);
        String workerKey = getMatchGroup(1);
        Worker oldWorker = workerCollection.getWorker(workerKey);
        Worker newWorker = workerForm.getWorker();
        if (newWorker.isLowerThan(oldWorker)){
            workerCollection.put(workerKey, newWorker);
        }
    }

    @Override
    protected @NotNull String getCommandRegex() {
        return "replace_if_lowe\\s+(.+)";
    }
}
