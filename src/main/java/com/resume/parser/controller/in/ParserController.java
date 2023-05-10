package com.resume.parser.controller.in;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.resume.parser.database.Candidate;
import com.resume.parser.database.CandidateRepository;
import com.resume.parser.database.Resume;
import com.resume.parser.service.in.AltParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import org.json.simple.JSONObject;

import com.resume.parser.ResponseWrapper;
import com.resume.parser.service.in.ParserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ParserController {

    @Autowired
    private ParserService parserService;
    private AltParser altParser;

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/upload")
    public ResponseWrapper parseResume(@RequestParam("resume") List<MultipartFile> resumes) {
        List<Object> responseData = new ArrayList<>();
        Candidate[] newCandidates = new Candidate[0];


        try {
            for (MultipartFile resume : resumes)  {
                altParser = new AltParser(resume);
                //ResponseWrapper responseWrapper = parserService.parseResume(resume);
               // JSONObject candidateData = (JSONObject) responseWrapper.getData();
                //Create an instance of ObjectMapper
               // ObjectMapper objectMapper = new ObjectMapper();
                //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                // Use the readValue method to map the JSON data to a Java object
               // Resume resumeObj = objectMapper.readValue(candidateData.toJSONString(), Resume.class);

                Candidate newCandidate = setCandidate(altParser);

               // JSONObject candidateJSON = putInRepository(newCandidate);

                //responseWrapper.setData(candidateJSON);
                candidateRepository.save(newCandidate);
                responseData.add(newCandidate.toString());
            }
        } catch (Exception ex) {
            ResponseWrapper responseWrapper = new ResponseWrapper();
            responseWrapper.setMessage(ex.getMessage());
            responseWrapper.setStatus(500);
            ex.printStackTrace();
            return responseWrapper;
        }
        ResponseWrapper finalResponse = new ResponseWrapper();
        finalResponse.setData(responseData);
        finalResponse.setMessage("Success");
        finalResponse.setStatus(200);
        return finalResponse;
    }

    public Candidate setCandidate(AltParser altParser){
        Candidate newCandidate = new Candidate();
        newCandidate.setEmail(altParser.parseEmails());
        newCandidate.setName(altParser.parseName());
        newCandidate.setPhoneNumber(altParser.parseNumbers());
        newCandidate.setSkills(altParser.parseSkills());
        newCandidate.setResume(altParser.getResume());
        newCandidate.setFileName(altParser.getFileName());
        return newCandidate;
    }

    public JSONObject putInRepository(Candidate newCandidate){
        JSONObject candidateJSON = new JSONObject();
        candidateJSON.put("id", newCandidate.getId());
        candidateJSON.put("email", newCandidate.getEmail());
        candidateJSON.put("phoneNumber", newCandidate.getPhoneNumber());
        candidateJSON.put("name", newCandidate.getName());
        candidateJSON.put("skills", newCandidate.getSkills());
        return candidateJSON;
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseWrapper handleMultipartException(Exception ex) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setData("No file uploaded");
        responseWrapper.setMessage("Please upload Resume!!");
        responseWrapper.setStatus(400);
        return responseWrapper;
    }

}
