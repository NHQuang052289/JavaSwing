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
import model.VehicleClass;

/**
 *
 * @author qn052
 */
public class VehicleDAO {
    private Connection conn = null;
    
    public VehicleDAO(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
    public boolean addVehicle(VehicleClass v){
        
        String sql = "INSERT INTO tblVehicle(numberPlate, type, phoneBoss, nameBoss) VALUES(?,?,?,?)";
        try{
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, v.getNumberPlate());;
            ps.setString(2, v.getType());
            ps.setString(3, v.getPhoneBoss());
            ps.setString(4, v.getNameBoss());
            
            return 0 <= ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("add Vehicle fail!!!" + e.toString());
        }
        return false;       
    }
    
    public ArrayList<VehicleClass> getListVehicle(){
        
        ArrayList<VehicleClass> listVehicle = new ArrayList<>();
        String sql = "SELECT * FROM tblVehicle";
         
        try{
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                VehicleClass v = new VehicleClass();
                
                v.setNumberPlate(rs.getString("numberPlate"));
                v.setType(rs.getString("type"));
                v.setPhoneBoss(rs.getString("phoneBoss"));
                v.setNameBoss(rs.getString("nameBoss"));
                
                listVehicle.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listVehicle;
        
    }
    
    public void deleteVehicle(String numberPlate){
        PreparedStatement ps = null;
        String sql = "Delete From tblVehicle where numberPlate = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, numberPlate);
            if(ps.executeUpdate() > 0){
                System.out.println("Da Xoa");
            }else{
                System.out.println("Bi Loi");
            }
        }catch(SQLException e){
            System.out.println("Xoa fail");
        }
    }
    
    public boolean editVehicle(String numberPlate, VehicleClass v){
       
        String sql = "update tblVehicle set type = ?, phoneBoss = ?, nameBoss = ? where numberPlate = ? ";
        try{
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);
            
            ps.setString(4, v.getNumberPlate());
            ps.setString(1, v.getType());
            ps.setString(2, v.getPhoneBoss());
            ps.setString(3, v.getNameBoss());
            
            return 0 <= ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Edit fail"+e.toString());
        }
        return false;
        
    }
    public static void main(String[] args) throws ClassNotFoundException{
        VehicleDAO vehicleDAO = new VehicleDAO();
    }
    
    
    
}
