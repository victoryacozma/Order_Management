package start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ClientBLL;
import bll.OrderBll;
import bll.ProductBll;
import model.Client;
import model.Order;
import model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException, IOException {

		File file = new File("V:\\AN2\\Teme_TP\\tema3\\text.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		int k = 0;
		ArrayList<String> myText = new ArrayList();
		ArrayList<String[]> selectat = new ArrayList();
		ArrayList<String> comenzi = new ArrayList();

		Document documentBill = new Document();
		PdfWriter writerBill = null;
		try {
			writerBill = PdfWriter.getInstance(documentBill, new FileOutputStream("Bill.pdf"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		documentBill.open();

		Document document = new Document();
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream("ReportClient.pdf"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		document.open();

		Document documentOrder = new Document();
		PdfWriter writerOrder = null;
		try {
			writerOrder = PdfWriter.getInstance(documentOrder, new FileOutputStream("ReportOrder.pdf"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		documentOrder.open();

		Document documentProduct = new Document();
		PdfWriter writerProduct = null;
		try {
			writerProduct = PdfWriter.getInstance(documentProduct, new FileOutputStream("ReportProduct.pdf"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		documentProduct.open();

		while ((str = br.readLine()) != null) {
			myText.add(str);
		}

		// citim din bufer
		for (int i = 0; i < myText.size(); i++) {
			// System.out.println( myText.get(i));
			String[] arrTime = myText.get(i).split(":");
			selectat.add(arrTime);
		}

		for (int i = 0; i < selectat.size(); i++) {
			if (selectat.get(i).length != 0) {
				for (int j = 0; j < selectat.get(i).length; j++) {
					String aux = selectat.get(i)[j];
					comenzi.add(aux);
				}
			}
		}

		for (int i = 0; i < comenzi.size(); i++) {

			if (comenzi.get(i).equals("Insert client")) {
				String[] myClient = comenzi.get(i + 1).split(",");
				Client client = new Client(myClient[0], myClient[1], "random@mail.com", 22);
				ClientBLL clientBll = new ClientBLL();
				clientBll.insertClient(client);
			}

			if (comenzi.get(i).equals("Delete client")) {
				String[] myClient = comenzi.get(i + 1).split(",");
				Client client = null;
				ClientBLL clientBll = new ClientBLL();
				client = clientBll.findClientByName(myClient[0], myClient[1]);

				if (client != null) {
					clientBll.deleteClient(client);
				}
			}

			if (comenzi.get(i).equals("Insert product")) {
				String[] myProduct = comenzi.get(i + 1).split(",");

				myProduct[1] = myProduct[1].replace(" ", "");
				myProduct[2] = myProduct[2].replace(" ", "");

				int quant = Integer.parseInt(myProduct[1]);
				float price = Float.parseFloat(myProduct[2]);

				Product prod = null;
				ProductBll prodbll = new ProductBll();

				try {
					prod = prodbll.findProductByName(myProduct[0]);
				} catch (Exception e) {
				}

				if (prod != null) {
					int newQ = prod.getQuantity() + quant;
					prod.setQuantity(newQ);

					prodbll.updateProduct(newQ, prod);
				}

				else {
					Product product = new Product(myProduct[0], quant, price);
					ProductBll productBll = new ProductBll();
					productBll.insertProduct(product);

				}

			}

			if (comenzi.get(i).equals("Delete Product")) {

				Product product = null;
				ProductBll prodbll = new ProductBll();

				try {
					product = prodbll.findProductByName(comenzi.get(i + 1));
				} catch (Exception ex) {
					LOGGER.log(Level.INFO, ex.getMessage());
				}
				int del = prodbll.deleteProduct(product);
			}

			if (comenzi.get(i).equals("Order")) {

				String[] myOrder = comenzi.get(i + 1).split(",");
				myOrder[2] = myOrder[2].replace(" ", "");
				int quant = Integer.parseInt(myOrder[2]);

				Order order = new Order(myOrder[0], myOrder[1], quant);
				OrderBll orderBll = new OrderBll();

				ClientBLL clientbll = new ClientBLL();
				Client client = null;

				ProductBll prodbll = new ProductBll();
				Product prod = null;

				prod = prodbll.findProductByName(myOrder[1]);

				if (prod != null) {
					if (prod.getQuantity() < quant) {
						System.out.println("Clientul vrea " + quant + "dar avem " + prod.getQuantity());
						System.out.println("nu exista asa multe produse");
						continue;
					}
					int newQuant = prod.getQuantity() - quant;
					prod.setQuantity(newQuant);

					try {
						prodbll.updateProduct(newQuant, prod);
					} catch (Exception e) {
						System.out.println("nu s-a gasit acest produs");
						continue;
					}

					try {
						client = clientbll.findClientByName(myOrder[0]);
						orderBll.insertOrder(order);
					} catch (Exception e) {
						System.out.println("nu s-a gasit acest client");
						continue;
					}

					float pretTotal = quant * prod.getPrice();

					try {
						documentBill.add(new Paragraph("BILL"));
						documentBill.add(new Paragraph(client.getName()));
						documentBill.add(new Paragraph(prod.getName()));
						documentBill.add(new Paragraph("total price: " + pretTotal));
						documentBill.add(new Paragraph("\n"));
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

			if (comenzi.get(i).equals("Report client")) {
				ArrayList<Client> clList = new ArrayList();

				ClientBLL clientBll = new ClientBLL();
				clList = clientBll.findAll();
				ReflectionExample re = new ReflectionExample();
				re.retrieveProperties(clList, document, writer);
			}

			if (comenzi.get(i).equals("Report order")) {
				ArrayList<Order> ordList = new ArrayList();

				OrderBll orderbll = new OrderBll();
				ordList = orderbll.findAll();
				ReflectionExample re = new ReflectionExample();
				re.retrievePropertiesOrder(ordList, documentOrder, writerOrder);
			}

			if (comenzi.get(i).equals("Report product")) {
				ArrayList<Product> clList = new ArrayList();

				ProductBll prodbll = new ProductBll();
				clList = prodbll.findAll();
				ReflectionExample re = new ReflectionExample();
				re.retrievePropertiesProduct(clList, documentProduct, writerProduct);
			}

		}

		document.close();
		writer.close();
		documentOrder.close();
		writerOrder.close();
		documentProduct.close();
		writerProduct.close();
		documentBill.close();
		writerBill.close();

	}

}
