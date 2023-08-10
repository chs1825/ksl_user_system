package egov.com.cmmn.log;

import egov.com.annotaion.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Aspect
@Slf4j
public class LogginAspect {

    @Value("${custom.logFilePath}")
    private String filePath;


    @Before(value = "@annotation(requestMapping) && @annotation(logTrace)", argNames = "joinPoint,requestMapping,logTrace")
//    @Before(value = "@annotation(requestMapping) &&@annotation(logTrace) && within(@org.springframework.web.bind.annotation.*)", argNames = "joinPoint,requestMapping,logTrace")
//    @Before(value = "@annotation(logTrace) && @within(org.springframework.web.bind.annotation.GetMapping)")
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

        String[] paths = requestMapping.value();

        if (paths.length > 0) {
            path = paths[0]; // Assuming only one path is defined

            System.out.println("RequestMapping path: " + path);
        }

        RequestMethod[] methods = requestMapping.method();

        for (RequestMethod method : methods) {
            httpMethod = String.valueOf(method);
            System.out.println("HTTP Method: " + method);
        }

        Method targetMethod = getTargetMethod(joinPoint);
        if (targetMethod != null) {
            System.out.println("Method Name: " + targetMethod.getName());
            methodName = targetMethod.getName();
        }

        logMessage = "[" + formattedAccessTime + "] " + "Method: " + methodName +
                ", URL: " + path + ", HTTP Method: " + httpMethod ;

        // 로그를 로컬 파일에 저장
        saveLogToFile(logMessage);
    }

    private Method getTargetMethod(JoinPoint joinPoint) {
        try {
            return joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName());
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
