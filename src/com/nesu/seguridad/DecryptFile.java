package com.nesu.seguridad;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DecryptFile
 */
@WebServlet("/DecryptFile")
@MultipartConfig()
public class DecryptFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DecryptFile() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encryptedMessage = request.getParameter("enc");
		Encriptador encrypter = new Encriptador();
		PrintWriter out = response.getWriter();
		try {
			String unencrypted = encrypter.decryptMsg(encryptedMessage);
			System.out.println(unencrypted);
			out.write("Mensaje encriptado recibido y desencriptado con exito. " + unencrypted);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}

}
