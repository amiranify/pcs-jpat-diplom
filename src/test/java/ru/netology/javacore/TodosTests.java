package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TodosTests {
    Todos todos = new Todos();

    @BeforeEach
    public void openConnection() {
        try {
            Socket socket = new Socket("localhost", 8989);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addTask() {
        todos.addTask("Пойти на пробежку");
        todos.addTask("Купить хлеб");
        todos.addTask("Забрать посылку");
        String addResult = todos.getAllTasks();
        Assertions.assertEquals(addResult, "Забрать посылку Купить хлеб Пойти на пробежку ");

    }

    @Test
    void removeTask() {
        todos.addTask("Пойти на пробежку");
        todos.addTask("Купить хлеб");
        todos.addTask("Забрать посылку");
        todos.removeTask("Купить хлеб");
        String removeResult = todos.getAllTasks();
        Assertions.assertEquals(removeResult, "Забрать посылку Пойти на пробежку ");


    }

    @Test
    void getAllTasks() {
        todos.addTask("Пойти на пробежку");
        todos.addTask("Купить хлеб");
        todos.addTask("Забрать посылку");
        String result = todos.getAllTasks();
        System.out.println(result);
        Assertions.assertEquals(result, "Забрать посылку Купить хлеб Пойти на пробежку ");
    }
}