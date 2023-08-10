package egov.com.cmmn.log;


import egov.com.annotaion.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

//@PropertySource("classpath:/src/main/resources/application.yml") // application.yml 위치에 따라 수정
@Aspect
@Component
@Slf4j
public class LoggingAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Value("${custom.logFilePath}")
    private String filePath;
    @Pointcut("@annotation(egov.com.annotaion.LogTrace) && @annotation(org.springframework.web.bind.annotation.RequestMapping)" )
    public void loggableMethods(){

    }

    @Around(value = "@annotation(logTraceAnnotation) && @annotation(requestMappingAnnotation)", argNames = "joinPoint,logTraceAnnotation,requestMappingAnnotation")
    public Object writeLog(ProceedingJoinPoint joinPoint, LogTrace logTraceAnnotation, RequestMapping requestMappingAnnotation) throws Throwable {
       log.info("성공 ? {}", logTraceAnnotation.value());
//       log.info("성공 ??? {}", requestMappingAnnotation.path()[0]);
        return joinPoint.proceed();
    }

    @After("loggableMethods()")
    public void writeLog(JoinPoint joinPoint) {
//        if (logTrace != null && requestMapping != null) log.info("아싸 개꿀");

        String className = joinPoint.getTarget().getClass().getSimpleName();

        String methodName = joinPoint.getSignature().getName();
        String url = request.getRequestURL().toString();
        log.info("AOP 해보기 {}",request.getSession().getId());
        String httpMethod = request.getMethod();

        String logMessage = "";
        // 현재 시간을 LocalDateTime으로 가져옴
        LocalDateTime accessTime = LocalDateTime.now();
        // 형식 지정 (예: 2023-08-07 15:30:45)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedAccessTime = accessTime.format(formatter);

        System.out.println("response.getstatus :" + response.getStatus());


        if (response.getStatus() >= 300 && response.getStatus() < 400) {
            System.out.println("Redirecting to URL: " + response.getHeader("Location"));
        } else {

            logMessage = "[" + formattedAccessTime + "] " + "Method: " + methodName +
                    ", URL: " + url + ", HTTP Method: " + httpMethod + "className :" + className;


//            logMessage = "Accessing URL: " + url + " through method: " + methodName +
//                    " with HTTP method: " + httpMethod;

            System.out.println("Accessing URL: " + url + " through method: " + methodName +
                    " with HTTP method: " + httpMethod + "className :" + className);

        }


        // 로그 기록 로직
        System.out.println("커스텀 어노테이션 작동!! Logging before method: " + joinPoint.getSignature().getName());

//         로그를 로컬 파일에 저장
        saveLogToFile(logMessage);
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





//    @AfterReturning("execution(* com.example.controller.DownloadController.downloadFile(..)) && args(fileName, ..)")
//    public void logAfterDownload(String fileName) {
//        System.out.println("Downloaded file: " + fileName);
//
//        // 다운로드 로그 기록
////        DownloadLogger.logDownload(fileName);
//        System.out.println("Downloaded file: " + fileName);
//    }

//    @Before("execution(* iirtech.com..*(..))")
//    public void logBefore(JoinPoint joinPoint) {
//
//        String sessionId = getSessionId();
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getTarget().getClass().getSimpleName();
//        System.out.println("Accessing method : " + methodName + " in class " + className);
//
//        // 접속 로그 기록
////        AccessLogger.recordAccess(methodName);
//        System.out.println("Accessing page: " + methodName);
//    }
//
//    private String getSessionId() {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        HttpSession session = request.getSession(false); // false 인자는 세션이 없으면 새로 생성하지 않도록 합니다.
//        if (session != null) {
//            System.out.println("세션 아이디 확인 : " + session.getId());
//            System.out.println("세션 비지트확인: " + session.getAttribute("firstVisit"));
//            log.debug("세션 아이디 확인 : {}" , session.getId());
//            return session.getId();
//        }
//
//        System.out.println("세션 아이디 확인 : " + session.getId());
//        log.debug("세션 아이디 확인 : {}" , session.getId());
//        return null;
//    }


}
