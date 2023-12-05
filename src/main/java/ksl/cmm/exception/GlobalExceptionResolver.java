package ksl.cmm.exception;

import egov.com.boot.web.CustomErrorPageResister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionResolver extends SimpleMappingExceptionResolver {


    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.err.println("시스템 에러 발생 : " + ex.toString());
        Arrays.stream(ex.getStackTrace()).iterator().forEachRemaining(System.err::println);

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        if (isApiRequest(request)) {
            return handleApiException(ex);
        }
        //CustomErrorPageResister에서 정의한 경로의 에러페이지에 리졸빙 해줌
        return super.doResolveException(request,response,handler,ex);
    }

// 일반 에러 처리
    /*@ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        // URL을 기반으로 예외를 분기 처리
        if (isApiRequest(request)) {
            return handleApiException(ex);
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("errorView"); // 에러 페이지 뷰 이름 설정
            modelAndView.addObject("errorMessage", ex.getMessage()); // 예외 메시지를 모델에 추가
            return modelAndView;
        }
    }
*/
    // API 예외 처리
    public ModelAndView handleApiException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        String message = ex.getMessage();
        String localizedMessage = ex.getLocalizedMessage();

        String res = "";
        if (localizedMessage != null && !localizedMessage.equals(message)){
            res = localizedMessage;
        }else {
            res = "오류가 발생하였습니다.\n관리자에게 문의해주세요.";
        }

        modelAndView.addObject("message",res);
        modelAndView.setViewName("jsonView");
        return modelAndView;
    }

    // URL을 기반으로 API 요청 여부를 판단하는 메서드
    private boolean isApiRequest(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return requestUri.contains("/api"); // URL이 "/api"로 시작하는지 여부 판단
    }

}


