/**
 * 
 */
package dataAccessLayer;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.summary.ResultSummary;

import static org.neo4j.driver.Values.parameters;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * @author Administrator
 *
 */
public class EmbeddedNeo4j implements AutoCloseable{

    private final Driver driver;
    ArrayList<String> departamentos = new ArrayList<String>();

    public EmbeddedNeo4j( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
        departamentos.add("Ciudad de Guatemala");
        departamentos.add("Sacatepequez");
        departamentos.add("Antigua Guatemala");
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void printGreeting( final String message )
    {
        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "CREATE (a:Greeting) " +
                                                     "SET a.message = $message " +
                                                     "RETURN a.message + ', from node ' + id(a)",
                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
    }
    
    public LinkedList<String> getActors()
    {
    	 try ( Session session = driver.session() )
         {
    		 
    		 
    		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
             {
                 @Override
                 public LinkedList<String> execute( Transaction tx )
                 {
                     Result result = tx.run( "MATCH (people:Person) RETURN people.name");
                     LinkedList<String> myactors = new LinkedList<String>();
                     List<Record> registros = result.list();
                     for (int i = 0; i < registros.size(); i++) {
                    	 //myactors.add(registros.get(i).toString());
                    	 myactors.add(registros.get(i).get("people.name").asString());
                     }
                     
                     return myactors;
                 }
             } );
             
             return actors;
         }
    }

    boolean itExist(ArrayList<String> as, String s){
        boolean exist = false;

        for (int i = 0; i < as.size() && !exist; i++)
            if(as.get(i).equals(s))
                exist = true;

        return exist;
    }

    public LinkedList<String> getPlaces(String Price, String Addres, String Caracteristics ,String Categorie)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> places = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (p:place) WHERE p.department='" + Addres + "' AND p.cost='" + Price + "' AND p.relation='" + Categorie +"' AND p.caracteristic='" + Caracteristics + "' RETURN p.name,p.department,p.cost,p.relation,p.caracteristic");
                    LinkedList<String> myplaces = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	    myplaces.add(registros.get(i).get("p.name").asString());
                        myplaces.add(registros.get(i).get("p.department").asString());
                        myplaces.add(registros.get(i).get("p.cost").asString());
                        myplaces.add(registros.get(i).get("p.relation").asString());
                        myplaces.add(registros.get(i).get("p.caracteristic").asString());
                    }
                    
                    return myplaces;
                }
            } );
            
            return places;
        }
   }
    

    public LinkedList<String> getMoviesByActor(String actor)
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> actors = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (tom:Person {name: \"" + actor + "\"})-[:ACTED_IN]->(actorMovies) RETURN actorMovies.title");
                    LinkedList<String> myactors = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	 myactors.add(registros.get(i).get("actorMovies.title").asString());
                    }
                    
                    return myactors;
                }
            } );
            
            return actors;
        }
   }

   public LinkedList<String> getFavPlaces()
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 LinkedList<String> places = session.readTransaction( new TransactionWork<LinkedList<String>>()
            {
                @Override
                public LinkedList<String> execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (p:place) WHERE p.score='5' RETURN p.name,p.department,p.cost,p.relation,p.caracteristic");
                    LinkedList<String> myplaces = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 //myactors.add(registros.get(i).toString());
                   	    myplaces.add(registros.get(i).get("p.name").asString());
                        myplaces.add(registros.get(i).get("p.department").asString());
                        myplaces.add(registros.get(i).get("p.cost").asString());
                        myplaces.add(registros.get(i).get("p.relation").asString());
                        myplaces.add(registros.get(i).get("p.caracteristic").asString());
                    }
                    
                    return myplaces;
                }
            } );
            
            return places;
        }
   }
    
    public ArrayList<String> getAdress()
    {
   	 try ( Session session = driver.session() )
        {
   		 
   		 
   		 ArrayList<String> places = session.readTransaction( new TransactionWork<ArrayList<String>>()
            {
                @Override
                public ArrayList<String> execute( Transaction tx )
                {
                	Result result = tx.run( "MATCH (n:department) RETURN n");
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                   	 departamentos.add(registros.get(i).get("n").toString());
                    }
                    
                    return departamentos;
                }
            } );
            
            return places;
        }
   }
    public String insertPlace(String placeName, String Price, String Addres, String Caracteristics ,String Categorie, String Rating) {
    	try ( Session session = driver.session() )
        {
   		 
   		 String result = session.writeTransaction( new TransactionWork<String>()
   		 
            {
                @Override
                public String execute( Transaction tx )
                {
                    tx.run( "CREATE ("+ placeName +":place {name:'" + placeName + "',department:'" + Addres + "',cost:'" + Price + "',relation:'" + Categorie +"',caracteristic:'" + Caracteristics + "',score:'"+ Rating +"'})");
                    if(!itExist(departamentos, Addres)){
                        tx.run( "CREATE ("+ Addres + ":department {name: '" + Addres + "'})");
                        departamentos.add(Addres);
                    }
                    tx.run( "MATCH (a:place {name:'"+ placeName +"'}),(b:department {name:'"+ Addres +"'}) CREATE (a)-[:LOCATED_IN]->(b)");
                    tx.run( "MATCH (a:place {name:'"+ placeName +"'}),(b:cost {name:'"+ Price +"'}) CREATE (a)-[:COST]->(b)");
                    tx.run( "MATCH (a:place {name:'"+ placeName +"'}),(b:relation {name:'"+ Categorie +"'}) CREATE (a)-[:RELATION]->(b)");
                    tx.run( "MATCH (a:place {name:'"+ placeName +"'}),(b:caracteristic {name:'"+ Caracteristics +"'}) CREATE (a)-[:IS]->(b)");
                    tx.run( "MATCH (a:place {name:'"+ placeName +"'}),(b:score {name:'"+ Rating +"'}) CREATE (a)-[:POINTS]->(b)");
                    return "OK";
                }
            }
   		 
   		 );
            
            return result;
        } catch (Exception e) {
        	return e.getMessage();
        }
    }

}