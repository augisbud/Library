import ui.LibraryUI;

import javax.swing.*;

/**
 * The {@code Main} Class is the entry point of the application
 */
public class Main {
    /**
     * The main method starts the application by initializing the VendingMachineUI.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryUI::new);
    }
}