/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author USER
 */
public class Home {
    private int id;
    private String ncr;
    private String courseid;
    private String pic;
    private String date;

    public Home() {}

    public Home(String ncr, String courseid, String pic, String date) {
        super();
        this.ncr = ncr;
        this.courseid = courseid;
        this.pic = pic;
        this.date = date;
    }

    public Home(int id, String ncr, String courseid, String pic, String date) {
        this.id = id;
        this.ncr = ncr;
        this.courseid = courseid;
        this.pic = pic;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNcr() {
        return ncr;
    }

    public void setNcr(String ncr) {
        this.ncr = ncr;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    

}