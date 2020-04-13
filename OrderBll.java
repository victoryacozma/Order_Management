package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.Validator;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.Order;
import model.Product;

public class OrderBll {

	private List<Validator<Product>> validators;

	public OrderBll() {
		validators = new ArrayList<Validator<Product>>();
	}

	public ArrayList<Order> findAll() {
		ArrayList<Order> st = new ArrayList();
		st = OrderDAO.findAll();
		if (st == null) {
			throw new NoSuchElementException("The Client with name = ce ma was not found!");
		}
		return st;
	}

	public int insertOrder(Order prod) {
		return OrderDAO.insert(prod);
	}

}
