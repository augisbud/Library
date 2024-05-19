package models.entities;

import exceptions.LibraryException;

/**
 * Interface defining actions that are restricted based on age.
 * Extends the PersonActions interface.
 */
public interface RestrictedActions extends PersonActions {

    /**
     * Allows a person to take an age-restricted book if they meet the minimum age requirement.
     *
     * @param ISBN        The ISBN of the book to be taken.
     * @param minimumAge  The minimum age required to take the book.
     * @throws LibraryException If there's a library-related exception, such as the book being blacklisted.
     */
    void takeAgeRestrictedBook(String ISBN, int minimumAge) throws LibraryException;
}
