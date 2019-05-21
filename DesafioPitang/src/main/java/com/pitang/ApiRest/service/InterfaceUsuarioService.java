package main.java.com.pitang.ApiRest.service;

import java.util.List;

import main.java.com.pitang.ApiRest.domain.Login;
import main.java.com.pitang.ApiRest.domain.Usuario;
import main.java.com.pitang.ApiRest.exception.PitangAPIException;

public interface InterfaceUsuarioService {

	public Usuario signup(Usuario usuario) throws PitangAPIException;

	public Usuario signin(Login login) throws PitangAPIException;

	public void update(Usuario usuario);

	public List<Usuario> findByAttributeContainsText(String attributeName, String text);

}
