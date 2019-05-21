package main.java.com.pitang.ApiRest.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.pitang.ApiRest.domain.Login;
import main.java.com.pitang.ApiRest.domain.Usuario;
import main.java.com.pitang.ApiRest.exception.CamposInvalidosException;
import main.java.com.pitang.ApiRest.exception.PitangAPIException;
import main.java.com.pitang.ApiRest.security.TokenAuthenticationService;
import main.java.com.pitang.ApiRest.service.InterfaceUsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private InterfaceUsuarioService usuarioService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Usuario> signup(@RequestBody Usuario usuario) {
		try {
			usuarioService.signup(usuario);
			return new ResponseEntity(TokenAuthenticationService.getToken(usuario), HttpStatus.OK);
		} catch (PitangAPIException e) {
			if (e instanceof CamposInvalidosException || e instanceof CamposInvalidosException) {

				return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
			}
		}

	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Usuario> signin(@RequestBody Login login) {
		Usuario usuario;
		try {

			usuario = usuarioService.signin(login);

		} catch (PitangAPIException e) {
			if (e instanceof CamposInvalidosException || e instanceof CamposInvalidosException) {

				return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
			}
		}
		usuario.setLastLogin(LocalDate.now());
		usuarioService.update(usuario);
		return new ResponseEntity(TokenAuthenticationService.getToken(usuario), HttpStatus.OK);
	}

	@RequestMapping(value = "/me", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Usuario> me(@RequestHeader String authorization) {

		List<Usuario> usuario = usuarioService.findByAttributeContainsText("firstName",
				TokenAuthenticationService.getNome(authorization));

		return new ResponseEntity(usuario.get(0), HttpStatus.OK);
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHome() {
		return "Hello!";
	}

}
