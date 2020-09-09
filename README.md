# resume-parser

A Java Spring Boot based Resume Parser API using GATE library.

Works well with any type of CV (PDF, HTML, DOC, DOCX, RTF, TXT) or LinkedIn Profile Document. 
Can extract all types of useful information correctly in English.

#### Build
```
mvn clean install
```

#### Usage

After successful build process, Resume_Parser.jar will be generated in `/target` repo.
Copy the `GATEFiles` and `JAPEGrammars` dir from root dir of this repo to `/target`.

> Note: Wherever "Resume Parser.jar" is present, make sure GATEFiles and JAPEGrammars directories are also present.


#### Run

java -jar "Resume_Parser.jar"


#### Request
##### Post 
```shell script
curl --request POST 'http://localhost:8081/upload' \
--header 'Content-Type: multipart/form-data' \
--form 'resume=@/<PATH_TO RESUME_IN_PDF_HTML_RTF>'
```

##### Sample Response

```json
{
    "status": 200,
    "data": {  
               "title":"",
               "gender":"",
               "name":{  
                  "first":"Aditya",
                  "middle":"",
                  "last":"Kumar"
               },
               "email":[  
            
               ],
               "address":[  
            
               ],
               "phone":[  
            
               ],
               "url":[  
            
               ],
               "work_experience":[  
                  {  
                     "date_start":"",
                     "jobtitle":"",
                     "organization":"",
                     "date_end":"",
                     "text":""
                  },
                  {  
                     "<section_title>":""
                  }
               ],
               "skills":[  
                  {  
                     "<section_title_from_resume>":"text"
                  }
               ],
               "education_and_training":[  
                  {  
                     "<section_title_from_resume>":"text"
                  }
               ],
               "accomplishments":[  
                  {  
                     "<section_title_from_resume>":"text"
                  }
               ],
               "awards":[  
                  {  
                     "<section_title_from_resume>":"text"
                  }
               ],
               "credibility":[  
                  {  
                     "<section_title_from_resume>":"text"
                  }
               ],
               "extracurricular":[  
                  {  
                     "<section_title_from_resume>":"text"
                  }
               ],
               "misc":[  
                  {  
                     "<section_title_from_resume>":"text"
                  }
               ]
            },
    "message": "Successfully parsed Resume!"
}
```

#### Library References
- GATE (https://gate.ac.uk/) - Open source language processing framework.

