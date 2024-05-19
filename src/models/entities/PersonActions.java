package models.entities;

/**
 * Interface defining actions that a person can perform.
 */
interface PersonActions {
    /**
     * Takes a book and adds it to the list of books in possession.
     *
     * @param ISBN The ISBN of the book to be taken.
     */
    void takeBook(String ISBN);

    /**
     * Returns a book and removes it from the list of books in possession.
     *
     * @param ISBN The ISBN of the book to be returned.
     */
    void returnBook(String ISBN);
}
