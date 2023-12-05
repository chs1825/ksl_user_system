package ksl.xssTest.service;

import org.springframework.stereotype.Service;

@Service
public class ExceptionTestService {

    public void makeNull(){
        throw new NullPointerException();
    }

}
