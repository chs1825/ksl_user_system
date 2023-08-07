package iirtech.com.test.mapper;


import iirtech.com.test.domain.NbVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface TestMapper {

    NbVO selectMainNotice();

}
