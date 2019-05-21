package main.java.com.pitang.ApiRest.exception;

public class InvalidEmailOrPasswordException extends PitangAPIException {

	private static final long serialVersionUID = -991987743170973650L;

	public InvalidEmailOrPasswordException() {
		super("Messege : Invalid email or password, ErroCode : 401 ");
	}


}
