package shell.commands;

import org.jetbrains.annotations.NotNull;
import shell.CommandAbstract;
import shell.Shell;
import structures.WorkerCollection;


public class CountByStartDate extends CommandAbstract {
    public CountByStartDate(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    public void execute() {
        int countDate = 0;
        String string = getMatchGroup(1);
        for(String key : workerCollection.keySet()){
            if(string.equals(workerCollection.getWorker(key).getStartDate().toString())){
                countDate = countDate+1;
            }
        }
        if(countDate > 0){
            shell.printMessage("countByStartDate");
            System.out.println(countDate + "\n");
        }else {
            shell.printMessage("countByStartDate_fail");
        }
    }

    @Override
    protected @NotNull String getCommandRegex() {
        return "count_by_start_date\\s+(.+)";
    }
}
