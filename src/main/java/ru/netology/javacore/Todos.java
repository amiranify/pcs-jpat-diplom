package ru.netology.javacore;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Todos {
    private static final int MAX_TASKS = 7;
    List<String> todosList = new LinkedList<>();


    public void addTask(String task) {
        if (todosList.size() < MAX_TASKS) {
            todosList.add(task);
        }
    }

    public void removeTask(String task) {
        if (todosList.contains(task)) {
            todosList.remove(task);
        }
    }

    public String getAllTasks() {
        Collections.sort(todosList);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < todosList.size(); i++) {
            stringBuilder.append(todosList.get(i));
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}