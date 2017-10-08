package com.demo.services;


import com.demo.service.SecurityService;
import com.demo.services.HelpData.SubmissionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionProcessingService {

    List<Object> programm;

    public void compileProgramm(SubmissionData submissionData, Long taskId){

    }

    public int runProgramm(){
        return 40;
    }

    public Object runDebugMode(){return new Object();}


}
