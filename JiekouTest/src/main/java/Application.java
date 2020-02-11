import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

//扫描哪个包下的类，进行托管
//@ComponentScan("com.study.server")

//Swagger-ui的api配置文件托管
@ComponentScan("com.study")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
