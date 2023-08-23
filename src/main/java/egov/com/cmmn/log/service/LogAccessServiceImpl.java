package egov.com.cmmn.log.service;

import egov.com.cmmn.log.domain.AccessLogVO;
import egov.com.cmmn.log.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogAccessServiceImpl implements LogAccessService{

    @Autowired
    LogMapper logMapper;

    @Override
    public void insertAccessLog(AccessLogVO accessLogVO) {
        logMapper.insertAccessLog(accessLogVO);
    }
}
