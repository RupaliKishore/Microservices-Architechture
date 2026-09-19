package com.architechture.exception;

import org.springframework.graphql.execution.ErrorType;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class GraphQlExceptionHandlers
{

    @GraphQlExceptionHandler
    public GraphQLError handle(EmailAlreadyExistsException ex, DataFetchingEnvironment environment)
    {
         return GraphqlErrorBuilder.newError(environment)
                 .message(ex.getMessage())
                 .errorType(ErrorType.BAD_REQUEST) // client-side error
                 .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(BadCredentialsException ex, DataFetchingEnvironment env)
    {
        return GraphqlErrorBuilder.newError(env)
                .message("Invalid email or password")
                .errorType(ErrorType.UNAUTHORIZED)
                .build();
    }


    @GraphQlExceptionHandler
    public GraphQLError handle(ResourceArgumentException ex, DataFetchingEnvironment env)
    {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .build();
    }

}
