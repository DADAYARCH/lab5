package structures;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import exceptions.IdentifierDoesNotExistError;
import shell.Filter;
import structures.Worker;
import java.io.*;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Set;

public class WorkerCollection {
    private Hashtable<String, Worker> workers = new Hashtable<>();
    private String loadedFrom;
    private LocalDateTime lastSaveDate = LocalDateTime.now();

    public String getLoadedFrom() {
        return loadedFrom;
    }

    public Hashtable<String, Worker> getWorkers() {
        return workers;
    }

    public boolean isEmpty() {
        return workers.isEmpty();
    }

    public Worker getWorker(String key) {
        return workers.get(key);
    }

    public Set<String> keySet() {
        return workers.keySet();
    }

    public void put(String key, Worker worker) {
        // todo: check for init
        workers.put(key, worker);
    }

    public Worker remove(String key) {
        return workers.remove(key);
    }

    public void clear() {
        workers.clear();
    }

    public int getSize() {
        return workers.size();
    }

    public LocalDateTime getLastSaveDate() {
        return lastSaveDate;
    }

    public void setCurrentDateAsLastSaveDate() {
        lastSaveDate = LocalDateTime.now();
    }

    public WorkerCollection filter(Filter filter) {
        WorkerCollection filteredWorkerCollection = new WorkerCollection();

        for (String key : workers.keySet()) {
            Worker worker = workers.get(key);
            if (filter.takeThis(worker)) {
                filteredWorkerCollection.put(key, worker);
            }
        }

        return filteredWorkerCollection;
    }

    public void dumpToJson() throws IdentifierDoesNotExistError.ParsingError {
        dumpToJson(loadedFrom);
    }

    public void dumpToJson(String filename) throws IdentifierDoesNotExistError.ParsingError {
        // todo: exception message
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            JSON.writeJSONString(printWriter, workers, SerializerFeature.PrettyFormat, SerializerFeature.IgnoreNonFieldGetter);
        } catch (IOException e) {
            throw new IdentifierDoesNotExistError.ParsingError();
        }
        setCurrentDateAsLastSaveDate();
    }

    protected Hashtable<String, Worker> parseJsonFile(String filename) throws IdentifierDoesNotExistError.ParsingError {
        // todo: exception
        try (FileInputStream file = new FileInputStream(filename)) {
            return JSON.parseObject(file, (new TypeReference<Hashtable<String, Worker>>() {}).getType());
        } catch (IOException | JSONException e ) {
            throw new IdentifierDoesNotExistError.ParsingError();
        }
    }

    public void loadFromJson(String filename) throws IdentifierDoesNotExistError.ParsingError {
        Hashtable<String, Worker> workers = parseJsonFile(filename);
        if (workers == null){
            workers = new Hashtable<String, Worker>();
        }
        for (Worker worker : workers.values()) {
            if (!worker.initialize()) {
                throw new IdentifierDoesNotExistError.ParsingError("Переданы поврежденные данные");
            }
        }
        this.workers = workers;
        loadedFrom = filename;
        setCurrentDateAsLastSaveDate();
    }
}
