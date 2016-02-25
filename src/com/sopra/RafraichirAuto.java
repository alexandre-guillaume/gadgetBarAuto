package com.sopra;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sopra.RequeteGA;
import com.google.gson.Gson;

public class RafraichirAuto extends HttpServlet {

	private static final long serialVersionUID 		= 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		// On va récupérer les paramètres du gadgets qui ont été passés en session
		/*String metrique = (String) session.getAttribute("metrique");
		String siteAsuivre = (String) session.getAttribute("siteAsuivre");
		
		String realPath = getServletConfig().getServletContext().getRealPath("/");
		String path = realPath + "/WEB-INF/client_secrets.p12";  
		
		String[] tabMetriques = metrique.split(";");
		String allMetriques = "ga:" + tabMetriques[0];
		for(int i=1 ; i<tabMetriques.length ; i++){
			allMetriques += ",ga:" + tabMetriques[i];
		}

		// On envoie la requête à Google
		List<DonneesChart> results = RequeteGA.getResponse(path, siteAsuivre, allMetriques);
		
		//On envoie la réponse de Google Analytics en JSON à la vue
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(results));
		response.flushBuffer();*/
		response.setContentType("text/html");
	    response.getOutputStream().println("Hello World !");
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		doGet(request, response);
	}
}