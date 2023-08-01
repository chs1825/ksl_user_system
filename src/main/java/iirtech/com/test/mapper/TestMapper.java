package iirtech.com.test.mapper;


import iirtech.com.test.domain.NbVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

    NbVO selectMainNotice();

}
