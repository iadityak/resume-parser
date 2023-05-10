package com.resume.parser.service.in;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.resume.parser.ResponseWrapper;
import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.springframework.web.multipart.MultipartFile;


import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;


public class AltParser {
    private String[] technicalSkills = {"Adobe Creative Suite", "Agile Development", "Amazon Web Services", "AWS",
            "Android Development", "AngularJS", "Angular", "Apache", "API Development",
            "ASP.NET", "Azure", "Big Data Analytics", "Bootstrap", "C++", "C#",
            "Cassandra", "Cloud Computing", "CodeIgniter", "CSS", "D3.js", "Django",
            "Docker", "Drupal", "Elasticsearch", "Ember.js", "Express.js", "Flask",
            "Flutter", "Gatsby", "Git", "Go", "Google Cloud Platform", "GCP", "GraphQL",
            "Hadoop", "Hibernate", "HTML", "iOS Development", "Java", "JavaScript", "Jenkins",
            "JIRA", "Joomla", "jQuery", "JSON", "Kafka", "Kubernetes", "Laravel", "Less",
            "Linux", "Machine Learning", "Magento", "MATLAB", "Microsoft Office", "MongoDB",
            "MySQL", "Nginx", "Node", "Objective-C", "Oracle", "Pandas", "PHP",
            "Photoshop", "PostgreSQL", "Power BI", "Python", "React", "React Native", "Redis",
            "Redux", "RESTful API Development", "Ruby", "Ruby on Rails", "Sass", "Scala",
            "Scikit-learn", "Scrum", "SEO", "Shopify", "Sketch", "SOAP", "Software Development",
            "Spring", "SQL", "SQLite", "Swift", "Symfony", "Tableau", "TensorFlow",
            "Test-driven Development", "TDD", "TypeScript", "Ubuntu", "Unity", "Unix",
            "Visual Studio", "Vue.js", "Web Development", "Webpack", "Windows",
            "WordPress", "XML", "XSLT", "Yii", "Zend", "Dart", "Firebase", "Microsoft Azure", "Excel", "PowerApps", "Power Automate", "Power Virtual Agents", "SharePoint", "Teams", "Visio", "Dynamics 365", "Power BI", "SQL Server", ".NET", "Windows Server", "Exchange Server", "Active Directory", "Outlook", "OneDrive", "OneNote", "Project", "Publisher", "Stream", "Forms", "Yammer", "Edge", "Bing", "Internet Explorer", "Office 365", "Visual Studio Code", "Windows", "PowerPoint", "Word", "Access", "Visual Basic", "Dynamics AX", "Jest", "Enzyme", "Mocha", "Chai", "Selenium", "Protractor", "Nightwatch.js", "Cypress", "Jasmine", "Karma", "Jupyter Notebook", "Pandas", "Numpy", "Scipy", "Matplotlib", "Seaborn", "Bokeh", "Plotly", "Ggplot2", "Shiny", "TensorFlow.js", "Keras.js", "Brain.js", "Convnet.js", "DeepLearn.js", "Pixi.js", "Phaser", "Three.js", "Babylon.js", "A-Frame", "Cordova", "Ionic",
            "React Native", "Flutter", "Xamarin", "PhoneGap", "Adobe PhoneGap", "Unity", "Unreal Engine", "OpenGL", "3D Printing",
            "ABAP", "ActiveMQ", "AdWords", "Agda", "Airtable", "Algorithms", "Alpine.js", "Altium Designer", "Amplitude", "ANTLR",
            "Apache Beam", "Apache Flink", "Apache HBase", "Apache Hive", "Apache Kafka", "Apache Kylin",
            "Apache Nifi", "GPT", "Chat GPT", "Apache Pig", "Apache Solr", "Apache Spark", "Appian", "Arduino", "ARM",
            "Artificial Intelligence", "AI", "Asana", "Asterisk", "Aurelia", "AutoCAD", "AutoHotkey", "Autodesk Revit", "Axure", "Babel", "Backbone.js", "Balsamiq", "Bash", "Batch Scripting", "Bigtable", "Bitbucket", "Bitrix", "Blender", "Blockchain", "Blue Prism", "BMC Remedy", "BootstrapVue", "Box", "Brackets", "Brand Management", "Broadleaf Commerce", "BuddyPress", "Bulma", "Business Analysis", "C", "C Shell",
            "CMake", "C# .NET", "C++/CLI", "CakePHP", "Capybara", "CartoDB", "Celery", "Ceph", "Chef", "Chisel", "Circuit Design", "Clojure",
            "ClojureScript", "Cloud Foundry", "Cloud Security", "CMS Development", "Cocoa", "Cocoa Touch", "Codeception", "CodeIgniter 4", "CoffeeScript", "ColdBox", "ColdFusion", "Collaboration", "Common Lisp", "Compliance", "Computational Biology", "Computational Chemistry", "Computational Physics", "Computer Vision", "Concourse CI", "Confluence", "Continuous Integration", "Convolutional Neural Networks", "Core Data", "Couchbase", "CouchDB", "Crystal", "CSS Grid", "Custom CMS Development", "Cypress.io", "D3", "Dancer", "Data Analysis", "Data Engineering", "Data Entry", "Data Mining", "Data Science", "Data Warehousing", "Database Administration", "Database Design", "Database Development", "Database Management", "Databricks", "DB2", "DDD", "Debugging", "Delphi", "Deployment", "Deno",
            "Design Patterns", "Desktop Applications", "DevOps", "Django Channels", "Django REST Framework", "Docker Compose", "Doctrine ORM", "Document Management", "DOM Manipulation", "DOS", "DotNetNuke",
            "Drupal 7", "Drupal 8", "DynamoDB", "E-commerce", "Eclipse", "ECS", "Eiffel", "Elastic Stack", "Electron", "Elementary OS", "ELK Stack", "Emacs"
    };
    private String content;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public AltParser(MultipartFile file) throws IOException {
        fileName = file.getOriginalFilename();
        Parser parser;
        if (fileName != null && fileName.endsWith(".docx")) {
            parser = new OOXMLParser();
        } else if (fileName != null && fileName.endsWith(".pdf")) {
            parser = new PDFParser();
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + fileName);
        }
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        try {
            parser.parse(file.getInputStream(), handler, metadata, context);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (TikaException e) {
            throw new RuntimeException(e);
        }
        this.content = handler.toString();
    }


    public String parseSkills() {
        try {


            String matchedSkills = "";
            String[] inputWords = content.toLowerCase().split("\\s+");

            for (String skill : technicalSkills) {
                if (content.contains(skill)) {
                    matchedSkills = matchedSkills + skill + ", ";
                }
            }


            return matchedSkills;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public String parseEmails() {


        Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");
        Matcher matcher = pattern.matcher(content);

        String matchedEmails = "";
        while (matcher.find()) {
            matchedEmails += matcher.group() + ", ";
        }


        return matchedEmails;

    }

    public String parseNumbers() {


        Pattern pattern = Pattern.compile("(\\+\\d{1,2}\\s?)?(\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]\\d{4})");
        Matcher matcher = pattern.matcher(content);

        String matchedNumbers = "";
        while (matcher.find()) {
            matchedNumbers += matcher.group() + ", ";
        }


        return matchedNumbers;

    }

    public String parseName(){
        String[] words = content.split("\\s+");
        return words[0] + " " + words[1] + " " + words[2] + " " + words[3];

    }

    public String getResume(){
        return content;
    }
}




