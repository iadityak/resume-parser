package com.resume.parser.database;

import com.resume.parser.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DatabaseConnection {

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping
    public ResponseWrapper getCandidates(){
        ResponseWrapper getResponse = new ResponseWrapper();
        try{
            getResponse.setStatus(200);
            getResponse.setMessage("Successfully retrieved candidates from database");
            getResponse.setData(candidateRepository.findAll());
        }
        catch (Exception ex){
            getResponse = new ResponseWrapper();
            getResponse.setMessage(ex.getMessage());
            getResponse.setStatus(500);
            ex.printStackTrace();
        }
        return getResponse;
    }

    @Transactional
    @GetMapping("/getResume/{Id}")
//    @RequestBody int Id
    public ResponseWrapper getResume(@PathVariable("Id") Integer Id){
        ResponseWrapper getResponse = new ResponseWrapper();
        try{
            getResponse.setStatus(200);
            getResponse.setMessage("Successfully retrieved Resume "+ Id +" from database");
            getResponse.setData(candidateRepository.getOne(Id).getResume());
        }
        catch (Exception ex){
            getResponse = new ResponseWrapper();
            getResponse.setMessage(ex.getMessage());
            getResponse.setStatus(500);
            ex.printStackTrace();
        }
        return getResponse;
    }
}
