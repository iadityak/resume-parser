package com.resume.parser.database;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;


@Entity
public class Resume {

    private List<Map<String, String>> summary;
    private List<Map<String, String>> education_and_training;
    private List<Map<String, String>> skills;
    private Basics basics;
    private List<WorkExperience> work_experience;

    public List<Map<String, String>> getSummary() {
        return summary;
    }

    public void setSummary(List<Map<String, String>> summary) {
        this.summary = summary;
    }

    public List<Map<String, String>> geteducation_and_training() {
        return education_and_training;
    }

    public void seteducation_and_training(List<Map<String, String>> education_and_training) {
        this.education_and_training = education_and_training;
    }

    public List<Map<String, String>> getSkills() {
        return skills;
    }

    public void setSkills(List<Map<String, String>> skills) {
        this.skills = skills;
    }

    public Basics getBasics() {
        return basics;
    }

    public void setBasics(Basics basics) {
        this.basics = basics;
    }

    public List<WorkExperience> getwork_experience() {
        return work_experience;
    }

    public void setwork_experience(List<WorkExperience> work_experience) {
        this.work_experience = work_experience;
    }

    public static class Basics {
        private String gender;
        private List<String> phone;
        private Name name;
        private String title;
        private List<String> email;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public List<String> getPhone() {
            return phone;
        }

        public void setPhone(List<String> phone) {
            this.phone = phone;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getEmail() {
            return email;
        }

        public void setEmail(List<String> email) {
            this.email = email;
        }

        public static class Name {
            private String first_name;
            private String surname;

            public String getFirstName() {
                return first_name;
            }

            public void setFirstName(String first_name) {
                this.first_name = first_name;
            }

            public String getSurname() {
                return surname;
            }

            public void setSurname(String surname) {
                this.surname = surname;
            }
        }
    }

    public static class WorkExperience {
        private String date_start;
        private String job_title;
        private String date_end;
        private String text;

        public String getdate_start() {
            return date_start;
        }

        public void setdate_start(String date_start) {
            this.date_start = date_start;
        }

        public String getjobtitle() {
            return job_title;
        }

        public void setjobtitle(String job_title) {
            this.job_title = job_title;
        }

        public String getdate_end() {
            return date_end;
        }

        public void setdate_end(String date_end) {
            this.date_end = date_end;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;

        }
    }
}
