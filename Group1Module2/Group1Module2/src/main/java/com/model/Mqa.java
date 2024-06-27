/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author user
 */
public class Mqa {
    private int docid;
    private String doctype;
    private byte[] file;
    private String date;
    private String pic;
    
    public Mqa(){}

    public Mqa(String doctype, byte[] file, String date, String pic) {
        this.doctype = doctype;
        this.file = file;
        this.date = date;
        this.pic = pic;
    }

    public Mqa(int docid, String doctype, byte[] file, String date, String pic) {
        this.docid = docid;
        this.doctype = doctype;
        this.file = file;
        this.date = date;
        this.pic = pic;
    }

    public int getDocid() {
        return docid;
    }

    public void setDocid(int docid) {
        this.docid = docid;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    
    public String getFilename() {
        // Assuming the filename can be derived or set separately if needed
        return "document_" + docid;
    }
    
}
