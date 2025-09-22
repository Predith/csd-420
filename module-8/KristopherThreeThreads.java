//Kristopher Kuenning
//09/21/2025
//CSD420
//Module 8 Programming Assignment

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * KristopherThreeThreads
 * - Thread 1: random letters a-z
 * - Thread 2: random digits 0-9
 * - Thread 3: random symbols ! @ # $ % & *
 * Appends at least 10,000 characters from EACH category to a shared TextArea.
 */
public class KristopherThreeThreads extends Application {

    private static final int COUNT_PER_CATEGORY = 10_000;
    private static final int BATCH_SIZE = 250; // UI batching to keep things smooth

    private final char[] LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] DIGITS  = "0123456789".toCharArray();
    private final char[] SYMBOLS = "!@#$%&*".toCharArray();

    private TextArea outputArea;
    private Label statusLabel;
    private volatile int lettersDone = 0;
    private volatile int digitsDone  = 0;
    private volatile int symbolsDone = 0;

    private ExecutorService exec;

    @Override
    public void start(Stage stage) {
        outputArea = new TextArea();
        outputArea.setWrapText(true);
        outputArea.setEditable(false);
        outputArea.setStyle("-fx-font-family: 'Consolas', 'Courier New', monospace; -fx-font-size: 12px;");

        statusLabel = new Label(progressText());
        HBox statusBar = new HBox(statusLabel);
        statusBar.setStyle("-fx-padding: 6px 10px; -fx-background-color: #f4f4f4;");

        BorderPane root = new BorderPane(outputArea);
        root.setBottom(statusBar);

        stage.setTitle("Three Threads — Random Characters");
        stage.setScene(new Scene(root, 900, 600));
        stage.show();

        exec = Executors.newFixedThreadPool(3);

        // Launch the three producers
        exec.submit(() -> produceRandom(LETTERS, "Letters"));
        exec.submit(() -> produceRandom(DIGITS,  "Digits"));
        exec.submit(() -> produceRandom(SYMBOLS, "Symbols"));
    }

    private void produceRandom(char[] alphabet, String name) {
        ThreadLocalRandom rng = ThreadLocalRandom.current();
        StringBuilder batch = new StringBuilder(BATCH_SIZE);

        int produced = 0;
        while (produced < COUNT_PER_CATEGORY && !Thread.currentThread().isInterrupted()) {
            // Fill a batch
            batch.setLength(0);
            int toMake = Math.min(BATCH_SIZE, COUNT_PER_CATEGORY - produced);
            for (int i = 0; i < toMake; i++) {
                char c = alphabet[rng.nextInt(alphabet.length)];
                batch.append(c);
                // Optional: add a space every ~50 chars to improve readability
                if ((produced + i + 1) % 50 == 0) batch.append(' ');
            }

            String toAppend = batch.toString();
            Platform.runLater(() -> {
                outputArea.appendText(toAppend);
                // Update counters and status
                switch (name) {
                    case "Letters" -> lettersDone += toAppend.replace(" ", "").length();
                    case "Digits"  -> digitsDone  += toAppend.replace(" ", "").length();
                    case "Symbols" -> symbolsDone += toAppend.replace(" ", "").length();
                }
                statusLabel.setText(progressText());
            });

            produced += toMake;

            // Tiny pause helps interleave output from the three threads
            try { Thread.sleep(1); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }

    private String progressText() {
        return String.format(
                "Progress — Letters: %d / %d   Digits: %d / %d   Symbols: %d / %d   (Total shown: ~%d)",
                Math.min(lettersDone, COUNT_PER_CATEGORY), COUNT_PER_CATEGORY,
                Math.min(digitsDone,  COUNT_PER_CATEGORY), COUNT_PER_CATEGORY,
                Math.min(symbolsDone, COUNT_PER_CATEGORY), COUNT_PER_CATEGORY,
                (lettersDone + digitsDone + symbolsDone)
        );
    }

    @Override
    public void stop() {
        if (exec != null) {
            exec.shutdownNow();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
