package com.example.sqlitepractice.model;

public class Emp {
    private Integer id;
    private String name;
    private String exNum;
    private String dept;
    private Integer jobTitle;

    public Emp() {
    }

    public Emp(Integer id, String name, String exNum, String dept, Integer jobTitle) {
        this.id = id;
        this.name = name;
        this.exNum = exNum;
        this.dept = dept;
        this.jobTitle = jobTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExNum() {
        return exNum;
    }

    public void setExNum(String exNum) {
        this.exNum = exNum;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(Integer jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", exNum='" + exNum + '\'' +
                ", dept='" + dept + '\'' +
                ", jobTitle=" + jobTitle +
                '}';
    }

}
