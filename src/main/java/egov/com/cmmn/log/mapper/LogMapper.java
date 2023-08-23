package egov.com.cmmn.log.mapper;

import egov.com.cmmn.log.domain.AccessLogVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    void insertAccessLog(AccessLogVO accessLogVO);
}
