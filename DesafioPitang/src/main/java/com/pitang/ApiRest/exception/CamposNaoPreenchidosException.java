package main.java.com.pitang.ApiRest.exception;

public class CamposNaoPreenchidosException extends PitangAPIException {

	private static final long serialVersionUID = 3015428352889656195L;
	
	public CamposNaoPreenchidosException() {
		super("Messege : Missing Fields, ErroCode : 400 ");
	}

}
