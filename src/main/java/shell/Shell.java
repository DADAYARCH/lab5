package shell;

import exceptions.*;
import shell.commands.*;
import structures.WorkerCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Shell {
    private WorkerCollection workerCollection;
    private ArrayList<Command> availableCommands;
    final Scanner scanner;
    private boolean isShellActive = true;
    private boolean printHelpMessage = true;

    public Shell(Shell shell, String inputFilename) throws FileNotFoundException {
        printHelpMessage = false;
        workerCollection = shell.workerCollection;
        scanner = new Scanner(new File(inputFilename));
        prepareAvailableCommand();
    }

    public Shell(String workerCollectionJsonFilename) throws ParsingError, FileALotWayException {
        scanner = new Scanner(System.in);
        prepareWorkerCollection(workerCollectionJsonFilename);
        prepareAvailableCommand();

    }

    protected void prepareAvailableCommand() {
        availableCommands = new ArrayList<>();
        availableCommands.add(new InfoCommand(this,workerCollection));
        availableCommands.add(new HelpCommand(this,workerCollection));
        availableCommands.add(new ShowCommand(this,workerCollection));
        availableCommands.add(new InsertCommand(this,workerCollection));
        availableCommands.add(new UpdateCommand(this,workerCollection));
        availableCommands.add(new RemoveKeyCommand(this, workerCollection));
        availableCommands.add(new ClearCommand(this,workerCollection));
        availableCommands.add(new SaveCommand(this,workerCollection));
        availableCommands.add(new ExecuteScriptCommand(this,workerCollection));
        availableCommands.add(new ExitCommand(this,workerCollection));
        availableCommands.add(new RemoveGreaterCommand(this, workerCollection));
        availableCommands.add(new RemoveLowerCommand(this, workerCollection));
        availableCommands.add(new ReplaceIfLowerCommand(this, workerCollection));
        availableCommands.add(new FilterContainsNameCommand(this, workerCollection));
        availableCommands.add(new CountByStartDate(this, workerCollection));
        availableCommands.add(new PrintFieldDescendingOrganizationCommand(this, workerCollection));
    }


    public void prepareWorkerCollection(String varName) throws ParsingError, FileALotWayException {
        while(true) {
            String errorMessage = "shell_incorrectVarName";
            if (varName != null && !varName.isEmpty()) {
                String path = System.getenv(varName);
                if (path != null) {
                    String[] checkPath = path.split(";");
                    if (checkPath.length == 1) {
                        try {
                            File file = new File(path);
                            if (!file.exists()) {
                                throw new FileNotExistsException();
                            }if (!file.canRead()) {
                                throw new FileWrongPermissionsException("cannot read file");
                            }

                            workerCollection = new WorkerCollection();
                            workerCollection.loadFromJson(path);
                            return;
                        } catch (Exception var5) {
                            errorMessage = "shell_invalidFileFormat";
                        }
                    }else {
                        throw new FileALotWayException("variable includes more then one paths");
                    }
                }
            }

            System.out.println(Message.getMessage(errorMessage));
            varName = this.getUserInput();
        }
    }

    public void printMessage(String messageCode) {
        printMessage(messageCode, (Object[]) null);
    }

    public void printMessage(String messageCode, Object... objects) {
        printf(Message.getMessage(messageCode), objects);
    }

    public void printf(String format, Object... objects) {
        System.out.printf(format, objects);
    }

    public void pprint(Object object) {
        PrettyPrint.print(object, "|");
    }

    public String getUserInput() {
        return getUserInput("console_prefix");
    }

    public String getUserInput(String messageCode) {
        return getUserInput(messageCode, (Object[]) null);
    }

    public String getUserInput(String messageCode, Object... objects) {
        if (printHelpMessage) {
            printMessage(messageCode, objects);
        }
        try {
            return scanner.nextLine().trim();
        } catch (NoSuchElementException e) {
            stop();
            return "";
        }
    }

    private boolean executeCommand(String cmd) {
        if (isShellActive) {
            for (Command command : availableCommands) {
                if (command.isThisCommand(cmd)) {
                    command.execute();
                    return true;
                }
            }
        }
        return false;
    }

    public void run() {
        while (isShellActive) {
            try {
                String userInput = getUserInput();
                if (!executeCommand(userInput) && printHelpMessage) {
                    printMessage("available_command", userInput);
                }
            }catch (NoSuchElementException e){
                String userInput = getUserInput();
                executeCommand(userInput);
            }
        }
    }

    public void stop() {
        isShellActive = false;
    }



}
