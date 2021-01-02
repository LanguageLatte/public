package com.example.demo;

//import com.languagelatte.simplechaos.spring.ChaosService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simplechaos/aop")
public class RestEndpoint {

    private ServiceA serviceA;
    public RestEndpoint(ServiceA serviceA){
        this.serviceA = serviceA;

    }
    
    @RequestMapping(value = "/service", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> exception() {  
        serviceA.testMethod();
        String result = "";
        return new ResponseEntity<String>(result , HttpStatus.OK);
    }
    
}
