package com.marriaga.venta_service.infra.excepciones;

public class ValidacionException extends RuntimeException{
    public ValidacionException(String mensaje){
        super(mensaje);
    }
}
