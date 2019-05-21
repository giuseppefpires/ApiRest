package test.java.com.pitang.ApiRest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.pitang.ApiRest.ApiRestApplication;
import main.java.com.pitang.ApiRest.domain.Login;
import main.java.com.pitang.ApiRest.domain.Phone;
import main.java.com.pitang.ApiRest.domain.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiRestApplication.class)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

	private final String SIGNUP_ROUTE = "/signup";
	private final String SIGNIN_ROUTE = "/signin";
	
	@Autowired 
	private ObjectMapper mapper;
	
	@Autowired
	private MockMvc mvc;
	
	
	@Test
	public void signgUpComSucessoTest() {
		Usuario usuario = new Usuario();
		usuario.setFirstName("Giuseppe");
		usuario.setLastName("Fregapane");
		usuario.setEmail("giuseppefpires@gmail.com");
		usuario.setPassword("1234567");
		List<Phone> phones = new ArrayList<>();
		Phone phone = new Phone();
		phone.setNumber("997644430");
		phone.setArea_code("81");
		phone.setCountry_code("55");
		phones.add(phone);
		usuario.setPhones(phones);
		
		try {
			String json = mapper.writeValueAsString(usuario);
			
		    mvc.perform(post(SIGNUP_ROUTE)
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk());
		} catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void signgUpVazioSemSucesso() {
		Usuario usuario = new Usuario();
		try {
			String json = mapper.writeValueAsString(usuario);
			
		    mvc.perform(post(SIGNUP_ROUTE)
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().is4xxClientError());
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	
	@Test
	public void signgInSemSucesso() {
		Login login = new Login();
		login.setEmail("giuseppefpires@gmail.com");
		login.setPassword("123467");
		try {
			String json = mapper.writeValueAsString(login);
			
		    mvc.perform(post(SIGNIN_ROUTE)
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().is4xxClientError());
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
//	@Test
//	public void meSucesso() {
//		try {
//			String json = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJHaXVzZXBwZSIsImV4cCI6MTU1OTIyOTc0Nn0.i7RfHAGhVLdeoe2sYTGqWY0oamLDPQmXzfHNgtO7vg0o5Yo8NLWyqxmDQmb6iG8MzT3butPZvDQQ2lgpyxV38Q";
//			
//		    mvc.perform(MockMvcRequestBuilders.get(ME_ROUTE)
//		       .header("Authorization", "Bearer " + json)	
//		       .contentType(MediaType.APPLICATION_JSON)
//		       .accept(MediaType.APPLICATION_JSON))
//		       .andExpect(status().isOk());
//			
//		}catch (Exception e) {
//			Assert.fail();
//		}
//	}
		
	
	
}
