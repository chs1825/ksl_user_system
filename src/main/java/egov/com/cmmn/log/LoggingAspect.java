package egov.com.cmmn.log;

import egov.com.annotaion.LogTrace;
import egov.com.cmmn.log.domain.AccessLogVO;
import egov.com.cmmn.log.service.LogAccessService;
import egov.com.cmmn.log.service.LogAccessServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static ksl.sts.domain.UrlEnum.findEnumByURL;

@Component
//@Aspect
@Slf4j
public class LoggingAspect {

    @Value("${custom.logFilePath}")
    private String filePath;

    LogAccessService logAccessService;

//    @Autowired
    public LoggingAspect(LogAccessServiceImpl logAccessService) {
        this.logAccessService = logAccessService;
    }

    @Before(value = "@annotation(requestMapping) && @annotation(logTrace)", argNames = "joinPoint,requestMapping,logTrace")
    public void logRequestMapping(JoinPoint joinPoint, RequestMapping requestMapping, LogTrace logTrace) {

        String logMessage = ""; // 기록될 로그
        String path = ""; // url
        String httpMethod = ""; // get/post
        String methodName = ""; // method 명
        String logTracePath = logTrace.path();
        log.info("로그트레이스 패스 : {}" , logTracePath);

        // 현재 시간을 LocalDateTime으로 가져옴
        LocalDateTime accessTime = LocalDateTime.now();
        // 형식 지정 (예: 2023-08-07 15:30:45)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedAccessTime = accessTime.format(formatter);


        // 작동 url 획득
        String[] paths = requestMapping.value();
        if (paths.length > 0) {
            path = paths[0]; // Assuming only one path is defined

            System.out.println("RequestMapping path: " + path);
        }

//        path = logTracePath;
        System.out.println("logTracePath = " + path);
        //path 코드화 진행
        try {
            path = Objects.requireNonNull(findEnumByURL(path)).getCode();
//            path = findEnumByURL(path).getCode();
        }catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        System.out.println("Transfer path: " + path);


        // http method 획득
//        RequestMethod[] methods = requestMapping.method();
//        for (RequestMethod method : methods) {
//            httpMethod = String.valueOf(method);
//            System.out.println("HTTP Method: " + httpMethod);
//        }


        // 작동 메소드 명 획득
        Method targetMethod = getTargetMethod(joinPoint);
        if (targetMethod != null) {
            System.out.println("Method Name: " + targetMethod.getName());
            methodName = targetMethod.getName();
        }


        logMessage = "[" + formattedAccessTime + "] " + "Method: " + methodName +
                ", URL: " + path + ", HTTP Method: " + httpMethod ;

        // 로그를 로컬 파일에 저장
        saveLogToFile(logMessage);

        //로그 디비 저장
        AccessLogVO accessLogVO = new AccessLogVO(formattedAccessTime,methodName,path);
        logAccessService.insertAccessLog(accessLogVO);
    }

    // 메서드 명 가져오는 메서드
    private Method getTargetMethod(JoinPoint joinPoint) {
        try {

            Signature signature = joinPoint.getSignature();
            Class<?> targetClass = joinPoint.getTarget().getClass();

            // Get parameter types from the signature
            Class<?>[] parameterTypes = ((MethodSignature) signature).getParameterTypes();

            // Find the method with the given name and parameter types
            return targetClass.getMethod(signature.getName(), parameterTypes);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveLogToFile(String logMessage) {
        try {
            // 파일이 없는 경우 파일을 생성
            File file = new File(filePath);


            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            writer.println(logMessage);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
