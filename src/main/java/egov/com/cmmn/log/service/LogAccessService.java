package egov.com.cmmn.log.service;

import egov.com.cmmn.log.domain.AccessLogVO;

public interface LogAccessService {

    void insertAccessLog(AccessLogVO accessLogVO);

}
