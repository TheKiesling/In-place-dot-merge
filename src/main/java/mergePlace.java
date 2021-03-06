

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dataAccessLayer.EmbeddedNeo4j;

import org.json.simple.JSONArray;

/**
 * Servlet implementation class MoviesByActor
 */
@WebServlet("/mergePlace")
public class mergePlace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mergePlace() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	 	response.setContentType("application/json");
	 	response.setCharacterEncoding("UTF-8");
	 	JSONObject myResponse = new JSONObject();

	 	JSONArray data = new JSONArray();
        JSONArray dataa = new JSONArray();
        JSONArray datac = new JSONArray();
		
	 	
	 	String placeName = request.getParameter("name");
	 	String Price = request.getParameter("price_range");
	 	String Addres = request.getParameter("Addres");
	 	String Caracteristics = request.getParameter("Caracteristic");
	 	String Categorie = request.getParameter("Categorie");
         
	 	 try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "UVG-compu2022" ) )
	        {
			 	LinkedList<String> myplaces = greeter.getPlaces(Price, Addres, Caracteristics, Categorie);
			 	
			 	for (int i = 0; i < myplaces.size(); i++) {
			 		 //out.println( "<p>" + myactors.get(i) + "</p>" );
			 		data.add(myplaces.get(i));
			 	}

                LinkedList<String> myplacesa = greeter.getAPlaces(Price, Addres, Caracteristics, Categorie);
			 	
			 	for (int i = 0; i < myplacesa.size(); i++) {
			 		 //out.println( "<p>" + myactors.get(i) + "</p>" );
			 		dataa.add(myplacesa.get(i));
			 	}

                LinkedList<String> myplacesc = greeter.getCPlaces(Price, Addres, Caracteristics, Categorie);
			 	
			 	for (int i = 0; i < myplacesc.size(); i++) {
			 		 //out.println( "<p>" + myactors.get(i) + "</p>" );
			 		datac.add(myplacesc.get(i));
			 	}
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	
	 	myResponse.put("conteo", data.size()); //Guardo la cantidad de actores
	 	myResponse.put("lugares", data);
        myResponse.put("conteoa", dataa.size());
        myResponse.put("a", dataa);
        myResponse.put("conteoc", datac.size());
        myResponse.put("c", datac);
	 	out.println(myResponse);
	 	out.flush();  
	 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}