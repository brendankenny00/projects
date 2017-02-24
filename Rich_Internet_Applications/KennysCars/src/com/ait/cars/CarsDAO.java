package com.ait.cars;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import java.sql.PreparedStatement;


public class CarsDAO {

    public List<Cars> findAll() {
        List<Cars> list = new ArrayList<Cars>();
        Connection c = null;
    	String sql = "SELECT * FROM cars ORDER BY make";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }

    
   
    protected Cars processRow(ResultSet rs) throws SQLException {
        Cars cars = new Cars();
        cars.setId(rs.getInt("id"));
        cars.setMake(rs.getString("make"));
        cars.setModel(rs.getString("model"));
        cars.setYear(rs.getString("year"));
        cars.setMilleage(rs.getString("milleage"));
        cars.setNCT(rs.getDate("nct"));
        cars.setColour(rs.getString("colour"));
        cars.setEngineSize(rs.getString("engine_size"));
        cars.setDescription(rs.getString("description"));
        cars.setEdit(rs.getString("edit"));
        cars.setPicture(rs.getString("picture"));
        return cars;
    }
    
    
    public Cars findById(int id){
    	
    	String sql = "SELECT * FROM cars WHERE id = ?";
    	Cars cars = null;
    	Connection c = null;
    	try{
    		c = ConnectionHelper.getConnection();
    		PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql);
    		ps.setInt(1, id);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()){
    			cars = processRow(rs);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	}finally{
    		ConnectionHelper.close(c);
    	}
    	
    	return cars;
    	
    }
  
    
    public boolean remove(int id){
    	Connection c = null;	
    	try{
    		c = ConnectionHelper.getConnection();
    		PreparedStatement ps = c.prepareStatement("DELETE FROM cars WHERE id=?");
    		ps.setInt(1, id);
    		int count = ps.executeUpdate();
    		return count == 1;
    	} catch (Exception e){
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	}
    	finally{
    		ConnectionHelper.close(c);
    	}
    }
   
    
    public Cars update(Cars cars){
    	Connection c = null;
    	try{
    		c = ConnectionHelper.getConnection();
    		PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE cars SET make=?,model=?, year=?," +
    		  "milleage=?, nct=?, colour=?, engine_size=?, description=? WHERE id=?");	
    	ps.setString(1,  cars.getMake());
    	ps.setString(2,  cars.getModel());
    	ps.setString(3,  cars.getYear());
    	ps.setString(4,  cars.getMilleage());
    	ps.setDate(5,  cars.getNCT());
    	ps.setString(6,  cars.getColour());
    	ps.setString(7,  cars.getEngineSize());
    	ps.setString(8,  cars.getDescription());
    	ps.setInt(9,  cars.getId());
    	ps.executeUpdate();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	}finally{
    		ConnectionHelper.close(c);
    	}
    	return cars;
    }   
    
    public Cars create(Cars cars){
    	Connection c = null;
    	PreparedStatement ps = null;
    	try{
    		//System.out.println("ASDASDASDASDASDASDsdASDASDASDASDASD");
    		c = ConnectionHelper.getConnection();
    		ps = (PreparedStatement) c.prepareStatement("INSERT INTO cars" +
    		" (make, model, year, milleage, nct, colour, engine_size)" +
    	    " VALUES (?,?,?,?,?,?,?)",
    		new String[] {"ID"});
    		
    	ps.setString(1,  cars.getMake());
    	ps.setString(2,  cars.getModel());
    	ps.setString(3,  cars.getYear());
    	ps.setString(4,  cars.getMilleage());
    	ps.setDate(5,  cars.getNCT());
    	ps.setString(6,  cars.getColour());
    	ps.setString(7,  cars.getEngineSize());
    	  
    ps.executeUpdate();
	ResultSet rs = ps.getGeneratedKeys();
	rs.next();
	
	int id = rs.getInt(1);
	cars.setId(id);
	}catch(Exception e){
		e.printStackTrace();
		throw new RuntimeException(e);
	}finally{
		ConnectionHelper.close(c);
	}
	
	return cars;
}
    
  
    
    public List<Cars> Search(String search){
        List<Cars> list = new ArrayList<Cars>();
        Connection c = null;
        String[] text = search.split("=");
    	String sql = "SELECT * FROM cars WHERE " + text[0] + " LIKE '%" + text[1] +"%';";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }    
        
    
}
