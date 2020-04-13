package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;
import model.Order;

public class OrderDAO {

	protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO schooldb.order (id,clientName,productName,productQuant)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementAll = "SELECT * FROM schooldb.order";
	private final static String findStatementStringName = "SELECT * FROM order WHERE name = ?";
	private final static String deleteStatementStringName = "DELETE FROM order WHERE name = ?";

		
	public static ArrayList<Order> findAll() {
		ArrayList<Order> toReturn = new ArrayList();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementAll);
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("clientName");
				String address = rs.getString("productName");
				int email = rs.getInt("productQuant");
				
				Order temp = new Order(id, name, address, email);
				toReturn.add(temp);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	
	public static Order findByName(String OrderName) {
		Order toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringName);
			findStatement.setString(1, OrderName);
			rs = findStatement.executeQuery();
			rs.next();

			int id = rs.getInt("id");
			String clientName = rs.getString("clientName");
			String productName = rs.getString("productName");
			int productQuant = rs.getInt("productQuant");
			toReturn = new Order(id, clientName, productName, productQuant);
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"OrderDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}


	public static int insert(Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, order.getId());
			insertStatement.setString(2, order.getClientName());
			insertStatement.setString(3, order.getProducttName());
			insertStatement.setInt(4, order.getproductQuant());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
		
}

