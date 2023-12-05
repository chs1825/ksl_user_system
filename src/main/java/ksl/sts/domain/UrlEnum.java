package ksl.sts.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UrlEnum {

    HOME_JSP("100", "/first.do"),
    JSPGOOD_JSP("101", "/second.do");



    private final String code;
    private final String url;


    public static UrlEnum findEnumByURL(String url){
        for (UrlEnum value : UrlEnum.values()) {
            if (value.getUrl().equals(url)){
                return value;
            }
        }
        return null;
    }



}
