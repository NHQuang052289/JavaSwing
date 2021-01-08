/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.PeopleClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author qn052
 */
public class DAOTest {
    
    public DAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addPeople method, of class DAO.
     */
    @Test
    public void testAddPeople() {
        System.out.println("addPeople");
        PeopleClass p = null;
        DAO instance = new DAO();
        boolean expResult = false;
        boolean result = instance.addPeople(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListPeople method, of class DAO.
     */
    @Test
    public void testGetListPeople() {
        System.out.println("getListPeople");
        DAO instance = new DAO();
        ArrayList<PeopleClass> expResult = null;
        ArrayList<PeopleClass> result = instance.getListPeople();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class DAO.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        DAO.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
