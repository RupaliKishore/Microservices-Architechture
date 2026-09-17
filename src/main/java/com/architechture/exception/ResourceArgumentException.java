package com.architechture.exception;

public class ResourceArgumentException extends RuntimeException
{
    public ResourceArgumentException(String message, String id, Long aLong)
    {
        super(message);
    }
}
