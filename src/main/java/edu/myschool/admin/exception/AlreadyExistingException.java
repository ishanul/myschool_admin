package edu.myschool.admin.exception;

public class AlreadyExistingException extends RuntimeException {
    public AlreadyExistingException(String message){
        super("Record already exists '" + message + "'");
    }
}
