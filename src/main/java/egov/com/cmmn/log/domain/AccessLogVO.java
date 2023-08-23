package egov.com.cmmn.log.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccessLogVO {


    private int logSeq;
    private String formattedAccessTime;
    private String methodName;
    private String path;

    public AccessLogVO(String formattedAccessTime, String methodName, String path) {
        this.formattedAccessTime = formattedAccessTime;
        this.methodName = methodName;
        this.path = path;
    }




}
