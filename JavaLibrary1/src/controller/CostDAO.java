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
import model.CostClass;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author qn052
 */
public class CostDAO {
    private Connection conn = null;
     
    public CostDAO(){
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
    public boolean addService(CostClass s){
        
        String sql = "INSERT INTO tblService(room, time, electric, water, security, parking, cleaning, other) VALUES(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, s.getRoom());
            ps.setString(2, s.getTime());
            ps.setString(3, s.getEletric());
            ps.setString(4, s.getWater());
            ps.setString(5, s.getSecurity());
            ps.setString(6, s.getParking());
            ps.setString(7, s.getCleaning());
            ps.setString(8, s.getOther());
            
            return 0 <= ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("add Room fail!!!" + e.toString());
        }
        return false;       
    }
    
        public ArrayList<CostClass> getListService(){
        
        ArrayList<CostClass> listService = new ArrayList<>();
        String sql = "SELECT * FROM tblService";
         
        try{
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CostClass s = new CostClass();
                
                s.setRoom(rs.getString("room"));
                s.setTime(rs.getString("time"));
                s.setEletric(rs.getString("electric"));
                s.setWater(rs.getString("water"));
                s.setSecurity(rs.getString("security"));
                s.setParking(rs.getString("parking"));
                s.setCleaning(rs.getString("cleaning"));
                s.setOther(rs.getString("other"));
                
                listService.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listService;
        
    }
        
        public void deleteService(String room){
        PreparedStatement ps = null;
        String sql = "Delete From tblService where room = ?";
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
     
    public boolean editService(String room, CostClass s){
       
        String sql = "update tblService set time = ?, electric = ?, water = ?, security = ?, parking = ?, cleaning = ?, other = ? where room = ? ";
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, s.getTime());
            ps.setString(2, s.getEletric());
            ps.setString(3, s.getWater());
            ps.setString(4, s.getSecurity());
            ps.setString(5, s.getParking());
            ps.setString(6, s.getCleaning());
            ps.setString(7, s.getOther());
            ps.setString(8, s.getRoom());
            
            return 0 <= ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Edit fail"+e.toString());
        }
        return false;
        
    }   
    public static void main(String[] args) throws ClassNotFoundException{
        CostDAO roomDao = new CostDAO();
    }
     
    
}
