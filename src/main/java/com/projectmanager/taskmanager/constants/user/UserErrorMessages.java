package com.projectmanager.taskmanager.constants.user;


/**
 * Contains only user related error messages.
 */
public final class UserErrorMessages {
    //Password does not match the confirm password!
    public static final String EMAIL_ALREADY_EXISTS =
            "Ops, it seems that this email is already taken. Please choose new one!";

    public static final String CONFIRM_PASS_NOT_MATCHING = "Password does not match the confirm password!";

    public static final String EMAIL_FIELD_IS_EMPTY = "Please fill out the email field.";

    public static final String NAME_FIELD_IS_EMPTY = "Please fill out the name field.";

    public static final String PASSWORD_FIELD_IS_EMPTY = "Please fill out the password fields.";

    private UserErrorMessages() {
    }
}
