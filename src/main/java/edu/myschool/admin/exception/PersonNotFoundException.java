package edu.myschool.admin.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String message){
        super("Record not found '" + message + "'");
    }
}
