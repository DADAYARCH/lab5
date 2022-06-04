package shell;

import structures.Worker;

public interface Filter {
    boolean takeThis(Worker worker);
}
