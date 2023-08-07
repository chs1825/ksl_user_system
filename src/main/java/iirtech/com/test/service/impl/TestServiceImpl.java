package iirtech.com.test.service.impl;

import iirtech.com.test.domain.NbVO;
import iirtech.com.test.mapper.TestMapper;
import iirtech.com.test.service.TestSerivce;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl extends EgovAbstractServiceImpl implements TestSerivce {

    @Autowired
    private TestMapper testMapper;

    @Override
    public NbVO testSpringBoot() {
        return testMapper.selectMainNotice();
    }
}
