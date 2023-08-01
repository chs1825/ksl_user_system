package egov.com.cmmn;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAop {

//    @AfterReturning("execution(* com.example.controller.DownloadController.downloadFile(..)) && args(fileName, ..)")
//    public void logAfterDownload(String fileName) {
//        System.out.println("Downloaded file: " + fileName);
//
//        // 다운로드 로그 기록
////        DownloadLogger.logDownload(fileName);
//        System.out.println("Downloaded file: " + fileName);
//    }

    @Before("execution(* iirtech.com..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("Accessing method " + methodName + " in class " + className);

        // 접속 로그 기록
//        AccessLogger.recordAccess(methodName);
        System.out.println("Accessing page: " + methodName);
    }


}
