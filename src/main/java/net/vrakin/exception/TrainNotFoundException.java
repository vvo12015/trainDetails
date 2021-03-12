package net.vrakin.exception;

public class TrainNotFoundException extends Exception{

    public TrainNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
