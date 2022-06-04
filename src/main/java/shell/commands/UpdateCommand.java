package shell.commands;

import exceptions.IdentifierDoesNotExistError;
import shell.CommandAbstract;
import shell.Shell;
import shell.WorkerForm;
import structures.Worker;
import structures.WorkerCollection;

public class UpdateCommand extends CommandAbstract {
    public UpdateCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    protected Integer getID() throws IdentifierDoesNotExistError {
        try {
            return Integer.parseInt(getMatchGroup(1));
        } catch (NumberFormatException e) {
            throw new IdentifierDoesNotExistError();
        }
    }

    protected Worker getWorkerByID(Integer id) throws IdentifierDoesNotExistError {
        for (Worker worker : workerCollection.getWorkers().values()) {
            if (worker.getId().equals(id)) {
                return worker;
            }
        }
        throw new IdentifierDoesNotExistError();
    }

    @Override
    protected String getCommandRegex() {
        return "update\\s+(.+)";
    }

    @Override
    public void execute() {
        try {
            Worker worker = getWorkerByID(getID());
            WorkerForm workerForm = new WorkerForm(shell, worker);
            workerForm.updateBoundWorker();
        } catch (IdentifierDoesNotExistError e) {
            shell.printMessage("identifier_does_not_exist");
        }
    }
}

