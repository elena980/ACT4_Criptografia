package Asimetrico.Req3;

import java.io.Serializable;


// Clase que no se han metido los get ya que únicamente la usaremos para
// introducir los datos del coche, no para leer
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;
	private String license;
	private String brand;
	private String model;
	private float price;

	public Car() {
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
