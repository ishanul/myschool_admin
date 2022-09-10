package edu.myschool.admin.exception;

public class AlreadyExistingException extends Exception{
    public AlreadyExistingException(String message){
        super(message);
    }
}
