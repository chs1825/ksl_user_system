package egov.com.annotaion;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME) // 런타임까지 어노테이션 정보 유지
@Target(ElementType.METHOD) // 메소드에만 적용 가능
public @interface LogTrace {


    String value() default "";

    String path();


}
