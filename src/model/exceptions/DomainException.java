package model.exceptions;

public class DomainException extends RuntimeException { //com 'RuntimeException' o compilador não obriga a tratar 
	private static final long serialVersionUID = 1L; 

	public DomainException(String msg) { //permitir instanciar a exceção personalizada, passando uma msg para ela (msg armazenada dentro da exceção)
		super(msg);
	}
}
