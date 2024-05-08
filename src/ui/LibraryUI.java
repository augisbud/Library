package ui;

import jdk.jshell.spi.ExecutionControl;
import models.literature.Book;
import models.literature.Journal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class LibraryUI {
    private final DefaultListModel<String> publicationListModel;
    private List<Book> books = new ArrayList<>();
    private List<Journal> journals = new ArrayList<>();
    private final JComboBox<String> publicationTypeComboBox;

    public LibraryUI() {
        var frame = new JFrame("Library UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 480);
        frame.setLayout(new BorderLayout());

        var mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        var controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        publicationTypeComboBox = new JComboBox<>(new String[]{ "Books", "Journals" });
        publicationTypeComboBox.addItemListener(e -> updatePublicationList());
        controlPanel.add(publicationTypeComboBox);

        var createPublicationButton = new JButton("Add a Publication");
        createPublicationButton.addActionListener(e -> createSelectedPublication(publicationTypeComboBox));
        controlPanel.add(createPublicationButton);

        var importPublicationsButton = new JButton("Import");
        importPublicationsButton.addActionListener(e -> {
            var selectedPublicationType = (String) publicationTypeComboBox.getSelectedItem();

            if (selectedPublicationType != null && selectedPublicationType.equals("Books")) {
                CompletableFuture<List<Book>> loadFuture = Book.loadBooksFromFileAsync("books_exported.bin");
                loadFuture.thenAccept(objectsLoaded -> {
                    if (objectsLoaded != null) {
                        books = objectsLoaded;
                        updatePublicationList();
                    } else {
                        System.out.println("Failed to load objects.");
                    }
                });
            } else {
                CompletableFuture<List<Journal>> loadFuture = Journal.loadJournalsFromFileAsync("journals_exported.bin");
                loadFuture.thenAccept(objectsLoaded -> {
                    if (objectsLoaded != null) {
                        journals = objectsLoaded;
                        updatePublicationList();
                    } else {
                        System.out.println("Failed to load objects.");
                    }
                });
            }
        });
        controlPanel.add(importPublicationsButton);

        var exportPublicationsButton = new JButton("Export");
        exportPublicationsButton.addActionListener(e -> {
            var selectedPublicationType = (String) publicationTypeComboBox.getSelectedItem();

            if (selectedPublicationType != null && selectedPublicationType.equals("Books")) {
                Book.saveBooksToFileAsync(books, "books_exported.bin");
            } else {
                Journal.saveJournalsToFileAsync(journals, "journals_exported.bin");
            }
        });
        controlPanel.add(exportPublicationsButton);

        frame.add(controlPanel, BorderLayout.NORTH);

        publicationListModel = new DefaultListModel<>();
        JList<String> publicationList = new JList<>(publicationListModel);
        publicationList.setCellRenderer(new ButtonCellRenderer());
        publicationList.addMouseListener(new ButtonClickListener(publicationList));
        JScrollPane publicationScrollPane = new JScrollPane(publicationList);
        mainPanel.add(publicationScrollPane, BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createSelectedPublication(JComboBox<String> publicationTypeComboBox) {
        var selectedPublicationType = (String) publicationTypeComboBox.getSelectedItem();

        if (selectedPublicationType != null && selectedPublicationType.equals("Books")) {
            displayBookInput();
        } else {
            displayJournalInput();
        }
    }

    private void displayBookInput() {
        var bookFrame = new JFrame("Add a Book");
        bookFrame.setSize(400, 300);
        bookFrame.setLayout(new GridLayout(6, 2));

        var isbnField = addLabelAndFields(bookFrame, "ISBN:");
        var titleField = addLabelAndFields(bookFrame, "Title:");
        var authorsField = addLabelAndFields(bookFrame, "Authors (separated by commas):");
        var publisherField = addLabelAndFields(bookFrame, "Publisher:");
        var pagesField = addLabelAndFields(bookFrame, "Pages:");

        var saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            var isbn = isbnField.getText();
            var title = titleField.getText();
            int pages = Integer.parseInt(pagesField.getText());
            var publisher = publisherField.getText();

            var authorArray = authorsField.getText().split(",");
            List<String> authors = new ArrayList<>();
            for (String author : authorArray) {
                authors.add(author.trim());
            }

            var book = new Book(isbn, title, authors, publisher, pages);

            books.add(book);
            updatePublicationList();
            bookFrame.dispose();
        });

        bookFrame.add(saveButton);
        bookFrame.setLocationRelativeTo(null);
        bookFrame.setVisible(true);
    }

    private void displayJournalInput() {
        var journalFrame = new JFrame("Add a Journal");
        journalFrame.setSize(400, 300);
        journalFrame.setLayout(new GridLayout(6, 2));

        var isbnField = addLabelAndFields(journalFrame, "ISBN:");
        var titleField = addLabelAndFields(journalFrame, "Title:");
        var editorField = addLabelAndFields(journalFrame, "Editor:");
        var publisherField = addLabelAndFields(journalFrame, "Publisher:");
        var pagesField = addLabelAndFields(journalFrame, "Pages:");

        var saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            var isbn = isbnField.getText();
            var title = titleField.getText();
            var pages = Integer.parseInt(pagesField.getText());
            var editor = editorField.getText();
            var publisher = publisherField.getText();

            var journal = new Journal(isbn, title, pages, editor, publisher);

            journals.add(journal);
            updatePublicationList();
            journalFrame.dispose();
        });

        journalFrame.add(saveButton);
        journalFrame.setLocationRelativeTo(null);
        journalFrame.setVisible(true);
    }

    private void updatePublicationList() {
        publicationListModel.clear();
        var selectedPublicationType = (String) publicationTypeComboBox.getSelectedItem();

        if (selectedPublicationType != null && selectedPublicationType.equals("Books")) {
            for (var book : books) {
                publicationListModel.addElement(book.generateDisplayTitle());
            }
        } else {
            for (var journal : journals) {
                publicationListModel.addElement(journal.generateDisplayTitle());
            }
        }
    }

    private void displayBookInformation(Book book) {
        var bookFrame = new JFrame("Information about a Book");
        bookFrame.setSize(400, 300);
        bookFrame.setLayout(new GridLayout(6, 1));

        addLabelAndValue(bookFrame, "Publisher:", book.getPublisher());
        addLabelAndValue(bookFrame, "ISBN:", book.getISBN());
        addLabelAndValue(bookFrame, "Title:", book.getTitle());
        addLabelAndValue(bookFrame, "Pages:", String.valueOf(book.getPages()));
        addLabelAndValue(bookFrame, "Authors:", String.join(", ", book.getAuthors()));

        var changeBookOwnershipButton = new JButton((book.getIsTaken() ? "Mark Book Returned" : "Mark Book Taken"));
        changeBookOwnershipButton.addActionListener(e -> updateBookInformation(bookFrame, book));
        bookFrame.add(changeBookOwnershipButton);

        bookFrame.setLocationRelativeTo(null);
        bookFrame.setVisible(true);
    }

    private void displayJournalInformation(Journal journal) {
        var journalFrame = new JFrame("Information about a Journal");
        journalFrame.setSize(400, 300);
        journalFrame.setLayout(new GridLayout(6, 1));

        addLabelAndValue(journalFrame, "Publisher:", journal.getPublisher());
        addLabelAndValue(journalFrame, "ISBN:", journal.getISBN());
        addLabelAndValue(journalFrame, "Title:", journal.getTitle());
        addLabelAndValue(journalFrame, "Pages:", String.valueOf(journal.getPages()));
        addLabelAndValue(journalFrame, "Authors:", journal.getEditor());

        journalFrame.setLocationRelativeTo(null);
        journalFrame.setVisible(true);
    }

    private void updateBookInformation(JFrame bookFrame, Book book) {
        try {
            if (book.getIsTaken())
                book.returnBook();
            else
                book.takeBook();
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }

        updatePublicationList();
        bookFrame.dispose();
        displayBookInformation(book);
    }

    private void addLabelAndValue(JFrame frame, String labelText, String valueText) {
        var panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        var label = new JLabel(labelText);
        var value = new JLabel(valueText);
        panel.add(label);
        panel.add(value);
        frame.add(panel);
    }

    private JTextField addLabelAndFields(JFrame frame, String labelText) {
        var label = new JLabel(labelText);
        var field = new JTextField();
        frame.add(label);
        frame.add(field);

        return field;
    }

    class ButtonClickListener extends MouseAdapter {
        JList<String> list;

        ButtonClickListener(JList<String> list) {
            this.list = list;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            var selectedPublicationType = (String) publicationTypeComboBox.getSelectedItem();

            int index = list.locationToIndex(e.getPoint());
            if (index != -1) {
                if(Objects.equals(selectedPublicationType, "Books")) {
                    var selectedBook = books.get(index);
                    displayBookInformation(selectedBook);
                } else {
                    var selectedJournal = journals.get(index);
                    displayJournalInformation(selectedJournal);
                }

                updatePublicationList();
            }
        }
    }

    static class ButtonCellRenderer extends JButton implements ListCellRenderer<String> {
        ButtonCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value);
            return this;
        }
    }
}
