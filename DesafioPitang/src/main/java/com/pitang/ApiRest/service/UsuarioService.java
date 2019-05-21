package main.java.com.pitang.ApiRest.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.pitang.ApiRest.domain.Login;
import main.java.com.pitang.ApiRest.domain.Usuario;
import main.java.com.pitang.ApiRest.exception.CamposInvalidosException;
import main.java.com.pitang.ApiRest.exception.CamposNaoPreenchidosException;
import main.java.com.pitang.ApiRest.exception.EmailExistenteException;
import main.java.com.pitang.ApiRest.exception.InvalidEmailOrPasswordException;
import main.java.com.pitang.ApiRest.exception.PitangAPIException;
import main.java.com.pitang.ApiRest.repository.UsuarioRepository;

@Service
public class UsuarioService implements InterfaceUsuarioService {

	@Autowired
	UsuarioRepository repository;

	private final String EMAIL_ATTRIBUTE = "email";

	public Usuario signup(Usuario usuario) throws PitangAPIException {
		if (usuario.getEmail() == null || usuario.getEmail().equals("") || usuario.getFirstName() == null
				|| usuario.getFirstName().equals("") || usuario.getLastName() == null
				|| usuario.getLastName().equals("") || usuario.getPassword() == null
				|| usuario.getPassword().equals("")) {
			throw new CamposNaoPreenchidosException();
		}
		if (usuario.getPassword().length() < 6 || !usuario.getEmail().contains("@")) {
			throw new CamposInvalidosException();
		}
		List<Usuario> userList = repository.findByAttributeContainsText(EMAIL_ATTRIBUTE, usuario.getEmail());
		if (!userList.isEmpty()) {
			throw new EmailExistenteException();
		}
		usuario.setCreateat(LocalDate.now());
		return repository.save(usuario);
	}

	public Usuario signin(Login login) throws PitangAPIException {
		if (login.getEmail() == null || login.getEmail().equals("") || login.getPassword() == null
				|| login.getPassword().equals("")) {
			throw new CamposNaoPreenchidosException();
		}

		List<Usuario> userList = repository.findByAttributeContainsText(EMAIL_ATTRIBUTE, login.getEmail());
		if (userList.isEmpty()) {
			throw new InvalidEmailOrPasswordException();
		} else {
			if (!userList.get(0).getPassword().equals(login.getPassword())) {
				throw new InvalidEmailOrPasswordException();
			}
		}
		return userList.get(0);

	}

	@Override
	public void update(Usuario usuario) {
		repository.save(usuario);

	}

	@Override
	public List<Usuario> findByAttributeContainsText(String attributeName, String text) {
		return repository.findByAttributeContainsText(attributeName, text);
	}

}
