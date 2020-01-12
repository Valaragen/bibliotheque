package com.rudy.biliotheque.mbook.util;

public class Constant {
    public static final String SLASH_ID = "/{id}";
    public static final String SLASH_STRING = "/{string}";

    public static final String BOOK_PATH = "/books";
    public static final String BOOK_VIEW_PATH = "/books" + SLASH_STRING;


    public static final String ERROR_MSG_PASSWORD_MISMATCH = "Les mots de passe ne sont pas identiques";
    public static final String ERROR_MSG_PASSWORD_NOT_COMPLETE = "Le mot de passe doit contenir au moins une majuscule";
    public static final String ERROR_MSG_USERNAME_INVALID_CHAR = "Le nom d'utilisateur ne peut pas contenir de caractère spéciaux";
    public static final String ERROR_MSG_USERNAME_NOT_AVAILABLE = "Ce pseudo n'est pas disponible";
    public static final String ERROR_MSG_EMAIL_NOT_AVAILABLE = "Cet email n'est pas disponible";
    public static final String ERROR_MSG_EMAIL_INVALID = "L'email saisi n'est pas valide";
}
