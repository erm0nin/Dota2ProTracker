package com.example.mymmr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

        public class HelloApplication extends Application {
            private static final String FILE_PATH = "previous_mmr.txt";
            private Label previousMMRLabel;
            private TextField currentMMRTextField;

            public static void main(String[] args) {
                launch(args);
            }

            @Override
            public void start(Stage primaryStage) {
                primaryStage.setTitle("MMR Tracker");

                previousMMRLabel = new Label("Предыдущий MMR: " + readPreviousMMR());
                Label currentMMRLabel = new Label("Текущий MMR: ");
                currentMMRTextField = new TextField();
                Button calculateButton = new Button("Рассчитать");
                Label resultLabel = new Label();

                calculateButton.setOnAction(e -> {
                    int previousMMR = readPreviousMMR();
                    int currentMMR = Integer.parseInt(currentMMRTextField.getText());

                    previousMMRLabel.setText("Предыдущий MMR: " + previousMMR);
                    resultLabel.setText("");

                    if (currentMMR > previousMMR) {
                        int increase = currentMMR - previousMMR;
                        resultLabel.setText("Ваш MMR увеличился на " + increase);
                    } else if (currentMMR < previousMMR) {
                        int decrease = previousMMR - currentMMR;
                        resultLabel.setText("Ваш MMR уменьшился на " + decrease);
                    } else {
                        resultLabel.setText("Ваш MMR остался неизменным.");
                    }

                    saveCurrentMMR(currentMMR);
                });

                GridPane gridPane = new GridPane();
                gridPane.setPadding(new Insets(10));
                gridPane.setVgap(10);
                gridPane.setHgap(10);
                gridPane.add(previousMMRLabel, 0, 0, 2, 1);
                gridPane.add(currentMMRLabel, 0, 1);
                gridPane.add(currentMMRTextField, 1, 1);
                gridPane.add(calculateButton, 0, 2, 2, 1);
                gridPane.add(resultLabel, 0, 3, 2, 1);

                VBox vbox = new VBox(gridPane);
                vbox.setPadding(new Insets(20));
                Scene scene = new Scene(vbox);
                primaryStage.setScene(scene);
                primaryStage.show();
            }

            private int readPreviousMMR() {
                try {
                    File file = new File(FILE_PATH);
                    if (file.exists()) {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line = reader.readLine();
                        reader.close();

                        return Integer.parseInt(line);
                    }
                } catch (IOException e) {
                    System.out.println("Ошибка при чтении предыдущего MMR: " + e.getMessage());
                }

                return 0; // Если файл не существует или чтение не удалось, возвращаем значение по умолчанию
            }

            private void saveCurrentMMR(int currentMMR) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
                    writer.write(String.valueOf(currentMMR));
                    writer.close();

                    System.out.println("Текущий MMR сохранен.");
                } catch (IOException e) {
                    System.out.println("Ошибка при сохранении текущего MMR: " + e.getMessage());
                }
            }

            @Override
            public void stop() {
                // Здесь можно добавить код, который будет выполняться при закрытии приложения
                System.out.println("Приложение закрыто.");
            }
        }
