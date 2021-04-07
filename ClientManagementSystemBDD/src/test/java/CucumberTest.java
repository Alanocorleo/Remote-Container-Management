
import org.junit.runner.RunWith;
import cucumber.api.*;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/main/resources/")
public class CucumberTest {

}
