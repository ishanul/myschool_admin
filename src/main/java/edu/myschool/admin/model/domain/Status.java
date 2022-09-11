package edu.myschool.admin.model.domain;

public enum Status {
    ACTIVE(1),
    SUSPENDED(0);

    private int value;
    Status(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
