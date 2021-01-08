/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RoomClass;

/**
 *
 * @author qn052
 */
public class RoomDAO {
    private Connection conn = null;
    
    public RoomDAO(){
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
    
    @SuppressWarnings("empty-statement")
    public boolean addRoom(RoomClass r){
        
        String sql = "INSERT INTO tblRoom(Room, nameBoss, phoneBoss) VALUES(?,?,?)";
        try{
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, r.getRoom());;
            ps.setString(2, r.getNameBoss());
            ps.setString(3, r.getPhoneBoss());
            
            return 0 <= ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("add Room fail!!!" + e.toString());
        }
        return false;       
    }
    
    public ArrayList<RoomClass> getListRoom(){
        
        ArrayList<RoomClass> listRoom = new ArrayList<>();
        String sql = "SELECT * FROM tblRoom";
         
        try{
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                RoomClass r = new RoomClass();
                
                r.setRoom(rs.getString("Room"));
                r.setNameBoss(rs.getString("nameBoss"));
                r.setPhoneBoss(rs.getString("phoneBoss"));
                
                listRoom.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRoom;
        
    }
    
    public void deleteRoom(String room){
        PreparedStatement ps = null;
        String sql = "Delete From tblRoom where Room = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, room);
            if(ps.executeUpdate() > 0){
                System.out.println("Da Xoa");
            }else{
                System.out.println("Bi Loi");
            }
        }catch(SQLException e){
            System.out.println("Xoa fail");
        }
    }
    
    public boolean editRoom(String room, RoomClass r){
       
        String sql = "update tblRoom set nameBoss = ?, phoneBoss = ? where Room = ? ";
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);
            
            ps.setString(3, r.getRoom());
            ps.setString(1, r.getNameBoss());
            ps.setString(2, r.getPhoneBoss());
            
            return 0 <= ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Edit fail"+e.toString());
        }
        return false;
        
    }
    public static void main(String[] args) throws ClassNotFoundException{
        RoomDAO roomDao = new RoomDAO();
    }
    
    
}
    
