package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.EmailValidator;
import bll.validators.ClientAgeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;


public class ClientBLL {

	private List<Validator<Client>> validators;

	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new EmailValidator());
		validators.add(new ClientAgeValidator());
	}

	public Client findClientById(int id) {
		Client st = ClientDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The Client with id =" + id + " was not found!");
		}
		return st;
	}
	
	public Client findClientByName(String name, String addr) {
		Client st = ClientDAO.findByName(name, addr);
		if (st == null) {
			throw new NoSuchElementException("The Client with name =" + name + " was not found!");
		}
		return st;
	}
	
	public Client findClientByName(String name) {
		Client st = ClientDAO.findByName(name);
		if (st == null) {
			throw new NoSuchElementException("The Client with name =" + name + " was not found!");
		}
		return st;
	}
	
	public ArrayList<Client> findAll() {
		ArrayList<Client> st = new ArrayList();
		st = ClientDAO.findAll();
		if (st == null) {
			throw new NoSuchElementException("The Client with name = ce ma was not found!");
		}
		return st;
	}

	public int insertClient(Client client) {
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		return ClientDAO.insert(client);
	}
	
	public int deleteClient(Client client) {
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		return ClientDAO.delete(client);
	}
	
	
}
