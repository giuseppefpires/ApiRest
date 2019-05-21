package main.java.com.pitang.ApiRest.repository;

import org.springframework.stereotype.Repository;

import main.java.com.pitang.ApiRest.domain.Usuario;

/**
 * Repositorio para usuario
 * @author Giuseppe
 *
 */
@Repository
public interface UsuarioRepository extends ExtendedRepository<Usuario, Long> {

}