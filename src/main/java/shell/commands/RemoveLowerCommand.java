package shell.commands;

import shell.CommandAbstract;
import shell.Shell;
import shell.WorkerForm;
import structures.Worker;
import structures.WorkerCollection;

public class RemoveLowerCommand extends CommandAbstract {
    public RemoveLowerCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    public void execute() {
        WorkerForm workerForm = new WorkerForm(shell);
        Worker newWorker = workerForm.getWorker();
        for (String key : workerCollection.keySet()){
            if (newWorker.isLowerThan(workerCollection.getWorker(key))){
                workerCollection.remove(key);
            }
        }
    }
}
