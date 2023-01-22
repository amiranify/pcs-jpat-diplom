package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    static class Operation {
        public enum Type {
            ADD, REMOVE, RESTORE
        }

        Type type;
        String task;

        public Operation(Type type, String task) {
            this.type = type;
            this.task = task;
        }

        @Override
        public String toString() {
            return "type: " + type + " task: " + task;
        }
    }

    protected int port;
    protected Todos todos;
    Gson gson = new Gson();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {
                    String request = in.readLine();
                    Operation operation = gson.fromJson(request, Operation.class);
                    switch (operation.type) {
                        case ADD:
                            System.out.println("Добавлена задача: " + operation.task);
                            todos.addTask(operation.task);
                            break;
                        case REMOVE:
                            System.out.println("Удалена задача: " + operation.task);
                            todos.removeTask(operation.task);
                            break;
                    }
                    String result = todos.getAllTasks();
                    out.println(result);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}