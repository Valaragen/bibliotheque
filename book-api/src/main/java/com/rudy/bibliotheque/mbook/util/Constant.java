package com.rudy.bibliotheque.mbook.util;

import org.springframework.beans.factory.annotation.Value;

// TODO Update
public class Constant {
    //ROLES
    public static final String USER_ROLE_NAME = "ROLE_USER";
    public static final String STAFF_ROLE_NAME = "ROLE_STAFF";
    public static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";

    public static final String SLASH_ID = "/{id}";
    public static final String SLASH_STRING = "/{string}";

    public static final String USERS_PATH = "/users";
    public static final String BOOKS_PATH = "/books";
    public static final String BOOK_VIEW_PATH = SLASH_STRING;

    public static final String LOANS_PATH = "/loans";
    public static final String NONRETURNED_EXPIRED_LOANS_PATH = "/nonReturnedExpiredLoans";


    public static final String ERROR_MSG_PASSWORD_MISMATCH = "Les mots de passe ne sont pas identiques";
    public static final String ERROR_MSG_PASSWORD_NOT_COMPLETE = "Le mot de passe doit contenir au moins une majuscule";
    public static final String ERROR_MSG_USERNAME_INVALID_CHAR = "Le nom d'utilisateur ne peut pas contenir de caractère spéciaux";
    public static final String ERROR_MSG_USERNAME_NOT_AVAILABLE = "Ce pseudo n'est pas disponible";
    public static final String ERROR_MSG_EMAIL_NOT_AVAILABLE = "Cet email n'est pas disponible";
    public static final String ERROR_MSG_EMAIL_INVALID = "L'email saisi n'est pas valide";
}
