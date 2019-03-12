package com.resume.parser.service.in;

import org.springframework.web.multipart.MultipartFile;

import com.resume.parser.ResponseWrapper;

public interface ParserService {

	ResponseWrapper parseResume(MultipartFile file);

}
