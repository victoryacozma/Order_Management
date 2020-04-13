package bll;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.Validator;
import dao.ClientDAO;
import dao.ProductDAO;
import model.Client;
import model.Product;


public class ProductBll {

	private List<Validator<Product>> validators;

	public ProductBll() {
		validators = new ArrayList<Validator<Product>>();
	}
	
	public ArrayList<Product> findAll() {
		ArrayList<Product> st = new ArrayList();
		st = ProductDAO.findAll();
		if (st == null) {
			throw new NoSuchElementException("The Client with name = ce ma was not found!");
		}
		return st;
	}

	
	public Product findProductByName(String name) {
		Product st = ProductDAO.findByName(name);
		if (st == null) {
			throw new NoSuchElementException("The Product with name =" + name + " was not found!");
		}
		return st;
	}

	public int insertProduct(Product prod) {
		for (Validator<Product> v : validators) {
			v.validate(prod);
		}
		return ProductDAO.insert(prod);
	}
	
	public int updateProduct(int q, Product prod) {
		return ProductDAO.update(q, prod);
	}
	
	public int deleteProduct(Product prod) {
	
		return ProductDAO.delete(prod);
	}
	
	
}

