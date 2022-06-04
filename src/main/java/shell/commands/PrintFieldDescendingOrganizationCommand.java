package shell.commands;

import shell.CommandAbstract;
import shell.PrettyPrint;
import shell.Shell;
import structures.Organization;
import structures.Worker;
import structures.WorkerCollection;

import java.util.ArrayList;
import java.util.Collections;

public class PrintFieldDescendingOrganizationCommand extends CommandAbstract {
    public PrintFieldDescendingOrganizationCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    public void execute() {
        ArrayList<Organization> organizations = new ArrayList<>();
        for (Worker worker : workerCollection.getWorkers().values()){
            if (worker.getOrganization() != null){
                organizations.add(worker.getOrganization());
            }
        }
        Collections.sort(organizations);
        PrettyPrint.print(organizations);
    }
}
