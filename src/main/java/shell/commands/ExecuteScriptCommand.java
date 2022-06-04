package shell.commands;

import exceptions.RecursionException;
import shell.CommandAbstract;
import shell.Shell;
import structures.WorkerCollection;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class ExecuteScriptCommand extends CommandAbstract {
    public ExecuteScriptCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    static LinkedList<String> scriptList = new LinkedList<>();

    @Override
    public void execute() {
        try {
            Shell virtualShell = new Shell(shell, getMatchGroup(1));
            for (int i = 0; i < scriptList.size(); i++) {
                if (getMatchGroup(1).equals(scriptList.get(i))){
                    shell.printMessage("recursion");
                    throw new RecursionException();
                }
            }
            scriptList.addLast(getMatchGroup(1));
            virtualShell.run();
            scriptList.removeLast();
        } catch (FileNotFoundException | RecursionException e) {
            shell.printMessage("shell_incorrectFilename");
        }
    }

    protected String getCommandRegex() {
        return "execute_script\\s+(.+)";
    }
}
