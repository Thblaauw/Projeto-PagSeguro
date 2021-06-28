package com.exceptions;

public class UsuarioIncompletoException extends Exception {
	public UsuarioIncompletoException(String errorMessage, Throwable err) {
	    super("Usuario Incompleto", err);
	}
	
	public UsuarioIncompletoException() {
	    super("Usuario Incompleto");
	}
}
