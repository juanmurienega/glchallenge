import com.jmurienega.demo.domain.entity.User
import com.jmurienega.demo.domain.repository.UserRepository
import com.jmurienega.demo.model.UserSignUpRequest
import com.jmurienega.demo.model.UserSignUpResponse
import com.jmurienega.demo.service.UserService
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

@SpringBootTest(classes = DemoApplication.class)
public class UserServiceSpecification extends Specification  {

	
	@Autowired
	UserRepository userRepository 
	
	@Autowired
	UserService userService 
	
	
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
	def "should expect save new user #emails"(){
		given:
		UserSignUpRequest userRequest = new UserSignUpRequest()
		userRequest.setName("test")
		userRequest.setEmail(emails)
		userRequest.setPassword("aa18aaaaF")
		when:
		UserSignUpResponse response = userService.createUser(userRequest)
		
		
		then:
		noExceptionThrown()
		where:
			emails << ["test@mail.com","emaid@ominio.com"]
	}
}