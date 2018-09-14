/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem;

import java.util.Arrays;
import static spark.Spark.*;
import spark.Request;
import spark.Response;



/**
 *
 * @author andres_vaz
 */

public class SparkWebApp {
    /**
     * Implicitly sets up a web server and publishes a set of endpoints that can
     * be accessed through HTTP.
     *
     * @param args Command line arguments
     */
    
    public static void main(String[] args) {
        setPort(4567);
        port(getPort());
        get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> resultsPage(req, res));
        

    }

    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>Media y desviación estandar de n numeros reales</h2>"
                + "<h3>introduzca cada valor separado por un espacio</h3>"
                + "<form action=\"/results\">"
                + "  Columna 1 (ejemplo: 160 591 114 229 230 270 128 1657 624 1503):<br>"
                + "  <input type=\"text\" name=\"columna1\" value=\"\" required>"
                + "  <br>"
                + "  <input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";
        
        return pageContent;
    }

    private static String resultsPage(Request req, Response res) {

        String pageContent;
        String[] col1 = req.queryParams("columna1").split(" ");
        int[] coll1= convertSaD(col1);
        int maximo = max(coll1);
        int minimo = min(coll1);
        int sumatoria = sum(coll1);
        int multiplicatoria = mult(coll1);
        String resultadoJsonLista =" "+convertStringlist(coll1);
        String resultadoJsonMaximo =" "+maximo;
        String resultadoJsonMinimo =" "+minimo;
        String resultadoJsonSumatoria =" "+sumatoria;
        String resultadoJsonMultiplicatoria =" "+multiplicatoria;
        
        
        if (col1.length > 0){
            pageContent
            = "<!DOCTYPE html>"
            + "<html>"
            + "<body>"
            + "<h2>Los resultados son:</h2>"
            + "<h3 style=\"color:red;\">Columna 1:</h3>"
            + "<h4>"+"LISTA:"+"  "+resultadoJsonLista+"</h4>"
            + "<h4>"+"Maximo:"+"  "+resultadoJsonMaximo+"</h4>"
            + "<h4>"+"Minimo:"+"  "+resultadoJsonMinimo+"</h4>"
            + "<h4>"+"LISTA:"+"  "+resultadoJsonSumatoria+"</h4>"
            + "<h4>"+"LISTA:"+"  "+resultadoJsonMultiplicatoria+"</h4>";

        }else{
            pageContent="error: columna 1 esta vacia";
        }
        return pageContent;
    }
        

    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
    /**
     * Método que calcula el maximo en una lista
     */ 
    private static int max(int list[]) {
        int Max=list[0];
        for ( int i = 0; i < list.length-1; i++ ){
            if(Max< list[i+1]){
                Max=list[i+1];
            }
        }
        return Max;
    }
    /**
     * Método que calcula el minimo en una lista
     */ 
    private static int min(int list[]) {
        int Min=list[0];
        for ( int i = 0; i < list.length-1; i++ ){
            if(list[i+1]<Min){
                Min=list[i+1];
            }
        }
        return Min;
    }  
    
    /**
     * Método que calcula la sumatoria en una lista
     */ 
    private static int sum(int list[]) {
        int Sum=0;
        for ( int i = 0; i < list.length; i++ ){
            Sum+=list[i];
            
        }
        return Sum;
    }   

    /**
     * Método que calcula la sumatoria en una lista
     */ 
    private static String convertStringlist(int list[]) {
        String res="";
        res = Arrays.toString(list);

        return res;
    }   

    /**
     * Método que calcula la multiplicatoria en una lista
     */ 
    private static int mult(int list[]) {
        int Mult=1;
        for ( int i = 0; i < list.length; i++ ){
            Mult*=list[i];
            
        }
        return Mult;
    }     
    
    /**
     * Método que convierte una lista de String a enteros
     */
    private static int[] convertSaD (String list[]) {
      int[] res = new int[list.length];

      for ( int i = 0; i < list.length; i++ ) 
        res[i] = Integer.parseInt(list[i]);
      return res;
}    
    

}
