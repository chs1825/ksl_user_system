package egov.com.boot.web;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"egov", "iirtech"})
public class EgovBootApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EgovBootApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF); // 작동시 뜨는 귀여운 배너 지워주는 설정
        springApplication.setLogStartupInfo(false); // 그밖의 로그기록들 출력하지 않는 설정
        springApplication.run(args);
    }

}
