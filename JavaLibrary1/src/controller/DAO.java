/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.PeopleClass;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author qn052
 */
public class DAO{
    
    private Connection conn = null;
    
    public DAO(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //conn = DriverManager.getConnection(readConnect());
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=Test;"
                + "username=sa;password=123");
             if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
    
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean addPeople(PeopleClass p){
        
        String sql = "INSERT INTO tblPeople(ID_People, name, dob, gender, phone, room, homeTown) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, p.getID());
            ps.setString(2, p.getName());
            ps.setDate(3,  new java.sql.Date(p.getBirthDay().getTime()));
            ps.setString(4, p.getGender());
            ps.setString(5, p.getPhone());
            ps.setString(6, p.getRoom());
            ps.setString(7, p.getHomeTowns());
            
            return 0 <= ps.executeUpdate();
            
        }catch(SQLException e){  
        }
        return false;
    }
    
    public ArrayList<PeopleClass> getListPeople(){
        
        ArrayList<PeopleClass> list = new ArrayList<>();
        String sql = "SELECT * FROM tblPeople";
        
        try{
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                PeopleClass p = new PeopleClass();
                
                p.setID(rs.getInt("ID_People"));
                p.setName(rs.getString("name"));
                p.setBirthDay(rs.getDate("dob"));
                p.setRender(rs.getString("gender"));
                p.setPhone(rs.getString("phone"));
                p.setRoom(rs.getString("room"));
                p.setHomeTowns(rs.getString("homeTown"));
                
                list.add(p);
            }
        }catch(SQLException e){    
            e.printStackTrace();
        }
        return list;
        
    }
    
    public void deletePeople(int index){
        PreparedStatement ps = null;
        String sql = "Delete From tblPeople where ID_People = ?" ;
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            if(ps.executeUpdate() > 0){
                System.out.println("Da Xoa");
            }else{
                System.out.println("Bi Loi");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean editPeople(int id, PeopleClass p){
        
        String sql = "update tblPeople set name = ?, dob = ?, gender = ?, phone = ?, room = ?, homeTown = ? where ID_People = ? ";
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);
            ps.setInt(7, p.getID());
            ps.setString(1, p.getName());
            ps.setDate(2,  new java.sql.Date(p.getBirthDay().getTime()));
            ps.setString(3, p.getGender());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getRoom());
            ps.setString(6, p.getHomeTowns());
            
            return 0 <= ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Edit fail"+e.toString());
        }
        return false;
                
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException{
        DAO dao = new DAO();
    }
    
}
