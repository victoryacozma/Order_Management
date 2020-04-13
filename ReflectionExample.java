package start;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import model.Client;
import model.Order;
import model.Product;

public class ReflectionExample {
	
	public static void retrievePropertiesBill(ArrayList<Object> obj, Document document, PdfWriter writer) {
		
		try {
			document.add(new Paragraph("BILL"));
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (Object object : obj) {

			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true); // set modifier to public
				Object value;
				try {
					value = field.get(object);
					try {
						document.add(new Paragraph(field.getName() + " = " + value));
					} catch (DocumentException e) {
						e.printStackTrace();
					}

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			}
			try {
				document.add(new Paragraph("\n"));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	

	public static void retrieveProperties(ArrayList<Client> obj, Document document, PdfWriter writer) {

		for (Object object : obj) {

			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true); // set modifier to public
				Object value;
				try {
					value = field.get(object);
					try {
						document.add(new Paragraph(field.getName() + " = " + value));
					} catch (DocumentException e) {
						e.printStackTrace();
					}

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			}
			try {
				document.add(new Paragraph("\n"));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	//////////////////////////////////////////////////////////////////

	public static void retrievePropertiesProduct(ArrayList<Product> obj, Document document, PdfWriter writer) {

		for (Object object : obj) {

			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true); // set modifier to public
				Object value;
				try {
					value = field.get(object);
					try {
						document.add(new Paragraph(field.getName() + " = " + value));
					} catch (DocumentException e) {
						e.printStackTrace();
					}

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			}
			try {
				document.add(new Paragraph("\n"));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/////////////////////////////////////////////////////////

	public static void retrievePropertiesOrder(ArrayList<Order> obj, Document document, PdfWriter writer) {

		for (Object object : obj) {

			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true); // set modifier to public
				Object value;
				try {
					value = field.get(object);
					try {
						document.add(new Paragraph(field.getName() + " = " + value));
					} catch (DocumentException e) {
						e.printStackTrace();
					}

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			}
			try {
				document.add(new Paragraph("\n"));
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
