package com.marriaga.carrito_service.infra.excepciones;

public class ValidacionException extends RuntimeException {
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
