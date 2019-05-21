package main.java.com.pitang.ApiRest.exception;

public class CamposInvalidosException extends PitangAPIException {

	private static final long serialVersionUID = 6413335560154498504L;
	
	public CamposInvalidosException() {
		super("Messege : Invalid Fields, ErroCode : 400 ");
	}

}
