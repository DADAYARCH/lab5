package shell.commands;

import org.jetbrains.annotations.NotNull;
import shell.Filter;
import shell.FilterCommandAbstract;
import shell.Shell;
import structures.WorkerCollection;

public class FilterContainsNameCommand extends FilterCommandAbstract {
    public FilterContainsNameCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    protected Filter getFilter() {
        String string = getMatchGroup(1);
        return worker -> worker.getName().contains(string);
    }

    @Override
    protected @NotNull String getCommandRegex(){
        return "filter_contains_name \\s+(.+)";
    }
}
