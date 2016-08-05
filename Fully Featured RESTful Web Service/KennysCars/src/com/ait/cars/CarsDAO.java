package com.ait.cars;

//import java.util.Date;

import java.sql.Date;

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
        cars.setId(rs.getInt("car_id"));
        cars.setMake(rs.getString("make"));
        cars.setModel(rs.getString("model"));
        cars.setYear(rs.getString("year"));
        cars.setMilleage(rs.getString("milleage"));
        cars.setNct(rs.getString("nct"));
        cars.setColour(rs.getString("colour"));
        cars.setEngineSize(rs.getString("engine_size"));
        cars.setTs(rs.getString("Timestamp"));
        return cars;
    }
    
    
    public Cars findById(int id){
    	
    	String sql = "SELECT * FROM cars WHERE car_id = ?";
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
    		PreparedStatement ps = c.prepareStatement("DELETE FROM cars WHERE car_id=?");
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
   
    
    public Cars update(Cars car){
    	Connection c = null;
    	try{
    		c = ConnectionHelper.getConnection();
    		PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE cars SET make=?, model=?," +
    		  "year=?, milleage=?, nct=?, colour=?, engine_size=?, timestamp=? WHERE car_id=?");	
    	ps.setString(1,  car.getMake());
    	ps.setString(2,  car.getModel());
    	ps.setString(3,  car.getYear());
    	ps.setString(4,  car.getMilleage());
    	ps.setString(5,  car.getNct());
    	ps.setString(6,  car.getColour());
    	ps.setString(7,  car.getEngineSize());
    	ps.setString(8,  car.getTs());
    	ps.setInt(9,  car.getId());
    	ps.executeUpdate();
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	}finally{
    		ConnectionHelper.close(c);
    	}
    	return car;
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
    	ps.setString(5,  cars.getNct());
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
    
    public void deleteAllItems()
    { Connection c = null;
    
    	try
    	{
    		c = ConnectionHelper.getConnection();
    		PreparedStatement ps = c.prepareStatement(
    				"DELETE FROM cars"
    				);
    		ps.execute();
    	}
    	catch(SQLException ex)
    	{
    		System.err.println("\n SQL Exception in deleteAllItems");
    		ex.printStackTrace();		
    	}
    }
    
}
