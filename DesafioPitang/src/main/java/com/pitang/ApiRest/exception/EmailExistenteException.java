package main.java.com.pitang.ApiRest.exception;

public class EmailExistenteException extends PitangAPIException {

	private static final long serialVersionUID = 7754527552135386988L;
	
	public EmailExistenteException() {
		super("Messege : E-mail already exists, ErroCode : 401 ");
	}


}
