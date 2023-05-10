package com.resume.parser.database;

import com.resume.parser.database.Resume;
import org.springframework.util.SerializationUtils;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;

    private String phoneNumber;
    private String Name;


    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(length = 20000)
    private String skills;

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", Name='" + Name + '\'' +
                ", skills='" + skills + '\'' +
                ", resume='" + resume + '\'' +
                '}';
    }

    @Lob
    private String resume;

    public Candidate(int id, String email, String phoneNumber, String name, String resume) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.Name = name;
        this.resume = resume;
    }

    public Candidate() {
    }
    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getResume() {
        return this.resume;
    }

}
