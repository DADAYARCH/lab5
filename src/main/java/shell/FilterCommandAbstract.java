package shell;

import structures.WorkerCollection;
import shell.Shell;

abstract public class FilterCommandAbstract extends CommandAbstract{
    public FilterCommandAbstract(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    abstract protected Filter getFilter();

    public void execute(){
        WorkerCollection filteredWorkerCollection = workerCollection.filter(getFilter());
        //todo: message empy
        PrettyPrint.print(filteredWorkerCollection.getWorkers());
    }
}
