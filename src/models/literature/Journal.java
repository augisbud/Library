package models.literature;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Represents a journal publication, extending the Publication class.
 * Each journal has an editor in addition to the attributes inherited from Publication.
 */
public class Journal extends Publication {
    private final String editor;

    /**
     * Gets the editor of the journal.
     *
     * @return The editor of the journal.
     */
    public String getEditor() { return this.editor; }

    /**
     * Constructs a new Journal with the specified ISBN, title, number of pages, editor, and publisher.
     *
     * @param ISBN      The ISBN of the journal.
     * @param title     The title of the journal.
     * @param pages     The number of pages of the journal.
     * @param editor    The editor of the journal.
     * @param publisher The publisher of the journal.
     */
    public Journal(String ISBN, String title, int pages, String editor, String publisher) {
        super(ISBN, title, pages, publisher);

        this.editor = editor;
    }

    /**
     * Generates a display title for the journal combining the publisher, editor, and title.
     *
     * @return The display title of the journal.
     */
    public String generateDisplayTitle() {
        return getPublisher() + " | " + editor + " | " + super.getTitle();
    }

    /**
     * Asynchronously saves a list of journals to a file.
     *
     * @param journals The list of journals to save.
     * @param filename The name of the file to save the journals to.
     */
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

    /**
     * Asynchronously loads a list of journals from a file.
     *
     * @param filename The name of the file to load the journals from.
     * @return A CompletableFuture containing the list of journals loaded from the file.
     */
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
}
