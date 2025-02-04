package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        Random randomizer = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean botRunning = true;
        boolean botAnswerFlag = true;
        List<String> log = new ArrayList<>();
        String answer;
        while (botRunning) {
            String question = scanner.nextLine();
            log.add(question);
            switch (question) {
                case OUT -> botRunning = false;
                case CONTINUE -> botAnswerFlag = true;
                case STOP -> botAnswerFlag = false;
                default -> {
                    if (botAnswerFlag) {
                        answer = phrases.get(randomizer.nextInt(phrases.size()));
                        log.add(answer);
                        System.out.println(answer);
                    }
                }
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(botAnswers))) {
            input.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, false))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/logConsole.txt", "data/answers.txt");
        consoleChat.run();
    }
}