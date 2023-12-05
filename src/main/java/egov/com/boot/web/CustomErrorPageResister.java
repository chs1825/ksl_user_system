package egov.com.boot.web;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


/*
    에러 페이지에 대한 커스텀 페이지 제공 설정
    에러 발생시 static 경로로 내에 있는 에러 페이지로 이동함
* */
@Configuration
public class CustomErrorPageResister implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {

        // 400s
        ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/errorCode/error400.html");
        ErrorPage errorPage401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/errorCode/error401.html");
        ErrorPage errorPage402 = new ErrorPage(HttpStatus.PAYMENT_REQUIRED, "/errorCode/error402.html");
        ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN, "/errorCode/error403.html");
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/errorCode/error404.html");
        ErrorPage errorPage405 = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/errorCode/error405.html");
        ErrorPage errorPage406 = new ErrorPage(HttpStatus.NOT_ACCEPTABLE, "/errorCode/error406.html");
        ErrorPage errorPage407 = new ErrorPage(HttpStatus.PROXY_AUTHENTICATION_REQUIRED, "/errorCode/error407.html");
        ErrorPage errorPage408 = new ErrorPage(HttpStatus.REQUEST_TIMEOUT, "/errorCode/error408.html");
        ErrorPage errorPage409 = new ErrorPage(HttpStatus.CONFLICT, "/errorCode/error409.html");
        ErrorPage errorPage410 = new ErrorPage(HttpStatus.GONE, "/errorCode/error410.html");
        ErrorPage errorPage411 = new ErrorPage(HttpStatus.LENGTH_REQUIRED, "/errorCode/error411.html");
        ErrorPage errorPage412 = new ErrorPage(HttpStatus.PRECONDITION_FAILED, "/errorCode/error412.html");
        ErrorPage errorPage413 = new ErrorPage(HttpStatus.PAYLOAD_TOO_LARGE, "/errorCode/error413.html");
        ErrorPage errorPage414 = new ErrorPage(HttpStatus.URI_TOO_LONG, "/errorCode/error414.html");
        ErrorPage errorPage415 = new ErrorPage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "/errorCode/error415.html");
        ErrorPage errorPage416 = new ErrorPage(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, "/errorCode/error416.html");
        ErrorPage errorPage417 = new ErrorPage(HttpStatus.EXPECTATION_FAILED, "/errorCode/error417.html");
        ErrorPage errorPage418 = new ErrorPage(HttpStatus.I_AM_A_TEAPOT, "/errorCode/error418.html");
        ErrorPage errorPage421 = new ErrorPage(HttpStatus.DESTINATION_LOCKED, "/errorCode/error421.html");
        ErrorPage errorPage422 = new ErrorPage(HttpStatus.UNPROCESSABLE_ENTITY, "/errorCode/error422.html");
        ErrorPage errorPage423 = new ErrorPage(HttpStatus.LOCKED, "/errorCode/error423.html");
        ErrorPage errorPage424 = new ErrorPage(HttpStatus.FAILED_DEPENDENCY, "/errorCode/error424.html");
        ErrorPage errorPage426 = new ErrorPage(HttpStatus.UPGRADE_REQUIRED, "/errorCode/error426.html");
        ErrorPage errorPage428 = new ErrorPage(HttpStatus.PRECONDITION_REQUIRED, "/errorCode/error428.html");
        ErrorPage errorPage429 = new ErrorPage(HttpStatus.TOO_MANY_REQUESTS, "/errorCode/error429.html");
        ErrorPage errorPage431 = new ErrorPage(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE, "/errorCode/error431.html");
        ErrorPage errorPage451 = new ErrorPage(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "/errorCode/error451.html");

        // 500s
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorPage/errorView.jsp");
//        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorCode/error500.html");
        ErrorPage errorPage501 = new ErrorPage(HttpStatus.NOT_IMPLEMENTED, "/errorCode/error501.html");
        ErrorPage errorPage502 = new ErrorPage(HttpStatus.BAD_GATEWAY, "/errorCode/error502.html");
        ErrorPage errorPage503 = new ErrorPage(HttpStatus.SERVICE_UNAVAILABLE, "/errorCode/error503.html");
        ErrorPage errorPage504 = new ErrorPage(HttpStatus.GATEWAY_TIMEOUT, "/errorCode/error504.html");
        ErrorPage errorPage505 = new ErrorPage(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "/errorCode/error505.html");
        ErrorPage errorPage506 = new ErrorPage(HttpStatus.VARIANT_ALSO_NEGOTIATES, "/errorCode/error506.html");
        ErrorPage errorPage507 = new ErrorPage(HttpStatus.INSUFFICIENT_STORAGE, "/errorCode/error507.html");
        ErrorPage errorPage508 = new ErrorPage(HttpStatus.LOOP_DETECTED, "/errorCode/error508.html");
        ErrorPage errorPage510 = new ErrorPage(HttpStatus.NOT_EXTENDED, "/errorCode/error510.html");
        ErrorPage errorPage511 = new ErrorPage(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, "/errorCode/error511.html");
        // Add more error pages if needed

        registry.addErrorPages(
                errorPage400, errorPage401, errorPage402, errorPage403, errorPage404,
                errorPage405, errorPage406, errorPage407, errorPage408, errorPage409,
                errorPage410, errorPage411, errorPage412, errorPage413, errorPage414,
                errorPage415, errorPage416, errorPage417, errorPage418, errorPage421,
                errorPage422, errorPage423, errorPage424, errorPage426, errorPage428,
                errorPage429, errorPage431, errorPage451, errorPage500, errorPage501,
                errorPage502, errorPage503, errorPage504, errorPage505, errorPage506,
                errorPage507, errorPage508, errorPage510, errorPage511
        );
    }
}
