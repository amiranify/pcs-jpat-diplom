package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    public static String pickRandom() {
        List<String> task = new ArrayList<>();
        task.add("Пойти на пробежку");
        task.add("Купить хлеб");
        task.add("Забрать посылку");
        Random random = new Random();
        int index = random.nextInt(task.size());
        return task.get(index);
    }
    public static void main(String... args) {
        try (Socket clientSocket = new Socket("localhost", 8989);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            writer.println("{ \"type\": \"ADD\", \"task\": \" task : " + pickRandom() + "\" }");
            System.out.println("type: " + TodoServer.Operation.Type.ADD  + in.readLine());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
