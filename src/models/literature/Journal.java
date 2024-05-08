package models.literature;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Journal extends Publication {
    private final String editor;

    public String getEditor() { return this.editor; }

    public Journal(String ISBN, String title, int pages, String editor, String publisher) {
        super(ISBN, title, pages, publisher);

        this.editor = editor;
    }

    public String generateDisplayTitle() {
        return getPublisher() + " | " + editor + " | " + super.getTitle();
    }

    public static void saveJournalsToFileAsync(List<Journal> journals, String filename) {
        Thread thread = new Thread(() -> {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
                out.writeObject(journals);
            } catch (Exception e){
                System.err.println("Failed to save journals to file: " + e.getMessage());
            }
        });
        thread.start();
    }

    public static CompletableFuture<List<Journal>> loadJournalsFromFileAsync(String filename) {
        CompletableFuture<List<Journal>> future = new CompletableFuture<>();
        Thread thread = new Thread(() -> {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
                List<Journal> journals = (List<Journal>) in.readObject();
                future.complete(journals);
            } catch (Exception e) {
                System.err.println("Failed to load journals from file: " + e.getMessage());
                future.completeExceptionally(e);
            }
        });
        thread.start();
        return future;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
