package model.exceptions;

public class DomainException extends RuntimeException { //com 'RuntimeException' o compilador n�o obriga a tratar 
	private static final long serialVersionUID = 1L; 

	public DomainException(String msg) { //permitir intanciar a exce��o personalizada, passando uma msg para ela (msg armazenada dentro da exce��o)
		super(msg);
	}
}