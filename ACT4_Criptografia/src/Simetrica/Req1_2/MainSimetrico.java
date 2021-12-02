package Simetrica.Req1_2;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

// Estamos trabajando con el algoritmo AES
public class MainSimetrico {

	public static void main(String[] args) throws IOException {
		Scanner rd = new Scanner(System.in);

		// ArrayList<Car> carList = new ArrayList();
		String frase = null;
		int num = 0;
		byte[] fraseConvertidaBytes = null;

		// Ofrecemos el menu
		System.out.println("MENÚ PRINCIPAL CLAVE SIMÉTRICA, MARQUE UNA OPCIÓN");
		System.out.println("******************************************************");
		System.out.println("OPCIÓN 1: Salir del programa");
		System.out.println("OPCIÓN 2: Encriptar frase");
		System.out.println("OPCIÓN 3: Monstrar frase encriptada");
		System.out.println("OPCIÓN 4: Desencriptar frase");
		System.out.println("OPIÓN 5:  Introduzca información del coche");
		System.out.println("    ");

		try {
			
			// Introducimos todo en un bucle para que puedes estar metiendo
			// opciones hasta que decidas salir del programa
			do {
				System.out.println("   ");
				System.out.println("Introduzca una opción:");
				num = rd.nextInt();

				// Paso 1: creamos un generador de claves
				KeyGenerator generador = KeyGenerator.getInstance("AES");

				// Paso 2: generamos una única clave
				SecretKey escitala = generador.generateKey();

				// Paso 3: generamos un cifrador con el objeto Cipher
				// nos ayudará a encriptar y desencriptar nuestra frase.
				Cipher cifrador = Cipher.getInstance("AES");

				switch (num) {
				case 1:
					System.out.println("El programa ha terminado");
					break;
				case 2:
					// Pedimos por pantalla una frase para encriptar
					System.out.println("Introduzca una frase:");
					// necesitamos agregar otra lectura de Scanner para que no salte
					// de linea. Es un problema de la clase Scanner al menterlo en estructuras
					// tipo Switch o tipo If. Solución para después de haber leido un Int y 
					// quieres leer un String, es poner una doble lectura.
					rd.nextLine();
					frase = rd.nextLine();
					
					// Configuramos el cifrador de manera que encripte el mensaje
					cifrador.init(Cipher.ENCRYPT_MODE, escitala);

					// Convertimos la frase introducida en pantalla a bytes
					// ya que el cifrador trabaja con bytes
					fraseConvertidaBytes = frase.getBytes();

					// Ciframos con doFinal()
					cifrador.doFinal(fraseConvertidaBytes);

					System.out.println("Frase encriptada ");
					System.out.println("   ");
					break;
				case 3:
					// Volvemos a reproducir todo el proceso de encriptado para
					// luego desencriptar. Al ser una opción no dependemos de otra para
					// que se ejecute la encriptación.

					// Configuramos el cifrador de manera que encripte el mensaje
					cifrador.init(Cipher.ENCRYPT_MODE, escitala);

					// Convertimos la frase introducida en pantalla a bytes
					// ya que el cifrador trabaja con bytes
					fraseConvertidaBytes = frase.getBytes();

					// Ciframos con doFinal() y lo metemos en una variable
					// convertida en bytes
					byte[] bytesMensajeCifrado3 = cifrador.doFinal(fraseConvertidaBytes);

					// Convertimos a String la cadena devuelta por el cifrador
					String mensajeCifrado3 = new String(bytesMensajeCifrado3);

					System.out.println("Frase introducida: " + frase);
					System.out.println("Frase encriptada: " + mensajeCifrado3);
					System.out.println(" ");
					break;
				case 4:
					// Volvemos a reproducir todo el proceso de encriptado para
					// luego desencriptar. Al ser una opción no dependemos de otra para
					// que se ejecute la encriptación.

					// Configuramos el cifrador de manera que encripte el mensaje
					cifrador.init(Cipher.ENCRYPT_MODE, escitala);

					// Convertimos la frase introducida en pantalla a bytes
					// ya que el cifrador trabaja con bytes
					fraseConvertidaBytes = frase.getBytes();

					// Ciframos con doFinal() y lo metemos en una variable
					// convertida en bytes
					byte[] bytesMensajeCifrado4 = cifrador.doFinal(fraseConvertidaBytes);

					// Convertimos a String la cadena devuelta por el cifrador
					String mensajeCifrado4 = new String(bytesMensajeCifrado4);

					// Ahora configuramos el criptador en modo que desencripte el mensaje
					cifrador.init(Cipher.DECRYPT_MODE, escitala);

					byte[] mensajeDescifrado4 = cifrador.doFinal(bytesMensajeCifrado4);
					String mensajeLegible = new String(mensajeDescifrado4);

					System.out.println("Frase encriptada: " + mensajeCifrado4);
					System.out.println("Frase desencriptada: " + mensajeLegible);
					System.out.println(" ");
					break;
				// Requerimiento 2
				case 5:
					// vuelvo a crear una variable de tipo Scanner para leer datos
					// introducidos en pantalla de manera local.
					Scanner rd5 = new Scanner(System.in);

					// configuramos el cifrador para encriptar el objeto Car
					cifrador.init(Cipher.ENCRYPT_MODE, escitala);

					Car coche = new Car();
					System.out.println("Introduzca matrícula del coche");
					coche.setLicense(rd5.nextLine());
					System.out.println("Introzuca marca del coche");
					coche.setBrand(rd5.nextLine());
					System.out.println("Introduzca modelo del coche");
					coche.setModel(rd5.nextLine());
					// Leemos el precio con un nextFloat para poder meter decimales
					System.out.println("Introduzca precio del coche, decimales con coma baja");
					coche.setPrice(rd5.nextFloat());

					// Con la clase SealedObject podemos encriptar objetos enteros
					// para ello necesitamos que el objeto este implementado en Serializable,
					// a pesar de ello tenemos que lanzarlo porque salta la
					// excepción IOException
					SealedObject sO = new SealedObject(coche, cifrador);

					System.out.println(sO);

					System.out.println("Objeto ecriptado");

				}

			} while (num != 1);

		} catch (GeneralSecurityException gse) {
			System.out.println(gse.getMessage());
			gse.printStackTrace();
		}
		rd.close();

	}

}