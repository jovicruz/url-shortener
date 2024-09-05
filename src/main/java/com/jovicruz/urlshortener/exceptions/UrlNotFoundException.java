package com.jovicruz.urlshortener.exceptions;

public class UrlNotFoundException extends RuntimeException{
    public UrlNotFoundException(){
        super("Url not found!");
    }
    
    public UrlNotFoundException(String message){
        super(message);
    }
}
