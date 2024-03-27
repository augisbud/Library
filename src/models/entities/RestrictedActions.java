package models.entities;

import exceptions.LibraryException;

public interface RestrictedActions extends PersonActions {
    void takeAgeRestrictedBook(String ISBN, int minimumAge) throws LibraryException;
}
