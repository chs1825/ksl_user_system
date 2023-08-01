package iirtech.com.sample.service.impl;

import iirtech.com.sample.mapper.SampleMapper;
import iirtech.com.sample.service.SampleService;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sampleService")
public class SampleServiceImpl extends EgovAbstractServiceImpl implements SampleService {

    @Resource(name = "sampleMapper")
    private SampleMapper sampleMapper;

    public String sample() {
        return sampleMapper.sample();
    }
}
