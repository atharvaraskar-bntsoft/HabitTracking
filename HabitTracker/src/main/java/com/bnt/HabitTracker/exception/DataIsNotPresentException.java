package com.bnt.HabitTracker.exception;

public class DataIsNotPresentException extends RuntimeException {
          
    public DataIsNotPresentException(String msg){
        super(msg);
    }
}
