import com.jmurienega.demo.domain.entity.User
import com.jmurienega.demo.domain.model.UserLoginResponse
import com.jmurienega.demo.domain.model.UserSignUpRequest
import com.jmurienega.demo.domain.model.UserSignUpResponse
import com.jmurienega.demo.domain.repository.UserRepository
import com.jmurienega.demo.domain.service.UserService

import spock.lang.Specification
import spock.lang.Unroll
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import org.spockframework.spring.SpringBean
import com.jmurienega.demo.DemoApplication
import javax.validation.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException

@SpringBootTest(classes = DemoApplication.class)
public class UserServiceSpecification extends Specification  {

	
	@Autowired
	UserRepository userRepository 
	
	@Autowired
	UserService userService 
	
	@Unroll
	def "should expect an Exception to be throw for invalid input: email is null"(){
		given:
		UserSignUpRequest userRequest = new UserSignUpRequest()
		userRequest.setName("test")
		userRequest.setPassword("aa18aaaaF")
		when:
		UserSignUpResponse response = userService.createUser(userRequest)
		
		then:
		thrown ConstraintViolationException

	}
	
	@Unroll
	def "should expect an Exception to be throw for invalid input: name is null"(){
		given:
		UserSignUpRequest userRequest = new UserSignUpRequest()
		userRequest.setEmail("mail@mail.com")
		userRequest.setPassword("aa18aaaaF")
		when:
		UserSignUpResponse response = userService.createUser(userRequest)
		
		then:
		noExceptionThrown()

	}
	
	@Unroll
	def "should expect an Exception to be throw for invalid input: email value #emails"(){
		given:
		UserSignUpRequest userRequest = new UserSignUpRequest()
		userRequest.setName("test")
		userRequest.setEmail(emails)
		userRequest.setPassword("aa18aaaaF")
		when:
		UserSignUpResponse response = userService.createUser(userRequest)
		
		then:
		thrown ConstraintViolationException

		where:
			emails << ["test.com","emaid@ominiocom","prueba"]
	}
	
	@Unroll
	def "should expect save new user"(){
		given:
		UserSignUpRequest userRequest = new UserSignUpRequest()
		userRequest.setName("test")
		userRequest.setEmail("mailtest@mail.com")
		userRequest.setPassword("aa18aaaaF")
		when:
		UserSignUpResponse response = userService.createUser(userRequest)
		
		
		then:
		noExceptionThrown()
		
	}
	
	@Unroll
	def "should expect an Exception to be throw for invalid input: password value #passwords"(){
		given:
		UserSignUpRequest userRequest = new UserSignUpRequest()
		userRequest.setName("test")
		userRequest.setEmail("mail@mail.com")
		userRequest.setPassword(passwords)
		when:
		UserSignUpResponse response = userService.createUser(userRequest)
		
		then:
		thrown ConstraintViolationException

		where:
			passwords << ["aa18aaaaf","aaddaaaaf","aa1aaaaf"]
	}
	
	@Unroll
	def "should expect an Exception to be throw for user duplication"(){
		given:
		UserSignUpRequest userRequest = new UserSignUpRequest()
		userRequest.setName("test")
		userRequest.setEmail("mail@mail.com")
		userRequest.setPassword("aa18aaaaF")
		when:
		UserSignUpResponse response1 = userService.createUser(userRequest)
		UserSignUpResponse response2 = userService.createUser(userRequest)
		then:
		thrown DataIntegrityViolationException

		
	}
	
	@Unroll
	def "should expect login user"(){
		given:
		UserSignUpRequest userRequest = new UserSignUpRequest()
		userRequest.setName("test")
		userRequest.setEmail("maillogint@mail.com")
		userRequest.setPassword("aa18aaaaF")
		UserSignUpResponse responseSignUp = userService.createUser(userRequest)
		when:
		UserLoginResponse responseLogin = userService.login(responseSignUp.getToken())
		
		
		then:
		noExceptionThrown()
		
	}
}
