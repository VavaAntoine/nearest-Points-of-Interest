//
//package com.innovus.nearestpoi.exception;
//
//import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.ext.ExceptionMapper;
//import jakarta.ws.rs.ext.Provider;
//
//@Provider
//public class NumberFormatExceptionMapper implements ExceptionMapper<NumberFormatException> {
//
//    @Override
//    public Response toResponse(NumberFormatException exception) {
//        return Response.status(Response.Status.BAD_REQUEST)
//                .entity("Invalid number format for query parameters. Please provide valid numeric values.")
//                .build();
//    }
//}