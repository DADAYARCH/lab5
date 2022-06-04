package shell.commands;

import exceptions.IdentifierDoesNotExistError;
import shell.CommandAbstract;
import shell.Shell;
import structures.WorkerCollection;

public class SaveCommand extends CommandAbstract {
    public SaveCommand(Shell shell, WorkerCollection workerCollection) {
        super(shell, workerCollection);
    }

    @Override
    public void execute() {
        try {
            workerCollection.dumpToJson();
        }catch (IdentifierDoesNotExistError.ParsingError e){
            shell.printf("Файл не сохранен");
        }

    }
}
