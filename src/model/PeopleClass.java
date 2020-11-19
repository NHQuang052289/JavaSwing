/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
/**
 *
 * @author qn052
 */
public class PeopleClass {
    private String name, gender, phone, room, homeTown;
    private Date dob; //dd/mm/yyyy
    private int ID_People;
     /*get and set ID*/
    public int getID(){
        return ID_People;
    }
    public void setID(int ID_People){
        this.ID_People = ID_People;
    } 
    
    /*get and set NAME*/
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    /*get and set GENDER*/
    public String getGender(){
        return gender;
    }
    public void setRender(String gender){
        this.gender = gender;
    }   
    
    /*get and set PHONE*/
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }   
    
    /*get and set ROOM*/
    public String getRoom(){
        return room;
    }
    public void setRoom(String room){
        this.room = room;
    }   
    
    /*get and set BirthDay*/
    public Date getBirthDay(){
        return dob;
    }
    public void setBirthDay(Date dob){
        this.dob = dob;
    }   
    
    /*get and set HOME TOWNS*/
    public String getHomeTowns(){
        return homeTown;
    }
    public void setHomeTowns(String homeTown){
        this.homeTown = homeTown;
    } 
            
}
