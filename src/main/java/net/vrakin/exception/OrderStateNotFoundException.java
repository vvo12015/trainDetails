package net.vrakin.exception;

public class OrderStateNotFoundException extends Exception{

    public OrderStateNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
