package simplebot.utils.task;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
public class TaskService {
    private Map<Integer, Task> taskMap = new HashMap<>();
    private final Gson gson = new GsonBuilder().create();

    private static final String taskFileName = "tasks.json";

    public void addTask(String description) {
        Integer index = taskMap
                .keySet()
                .stream()
                .max(Integer::compareTo)
                .orElse(0) + 1;
        taskMap.put(index, new Task(description));
        save();
    }


    public void save() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(taskFileName))) {
            bufferedWriter.write(gson.toJson(taskMap));
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(String s) {
        if (s == null || s.isEmpty())
            return;

        Integer index = Integer.parseInt(s);
        taskMap.remove(index);
        save();
    }

    public void read() {
        try (Stream<String> lines = Files.lines(Paths.get(taskFileName))) {
            Type typeOfHashMap = new TypeToken<Map<Integer, Task>>() {
            }.getType();
            taskMap = gson.fromJson(lines.collect(Collectors.joining()), typeOfHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        if (taskMap.size() == 0)
            return "Список пустой";

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry entry : taskMap.entrySet()) {
            stringBuilder.append(String.format("%s . %s\n", entry.getKey(), entry.getValue().toString()));
        }
        return stringBuilder.toString();
    }
}
