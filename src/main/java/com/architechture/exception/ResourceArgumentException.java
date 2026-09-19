package com.architechture.exception;

public class ResourceArgumentException extends RuntimeException
{
    public ResourceArgumentException(String resource, String field, Object value)
    {
        super(resource + " not found with "+ field + ":"+ value);
    }
}
