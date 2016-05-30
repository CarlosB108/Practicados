/**
 * Created by carlosb108 on 5/29/16.
 */

import freemarker.cache.ClassTemplateLoader;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import javax.security.auth.login.Configuration;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.modelAndView;

public class main {

    public static void main(String [] args) throws Exception {
        // Carpeta dentro esta todos los datos
        //Spark.staticFileLocation("/public");

        Class.forName("org.h2.Driver");
        Connection coneccion = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/practica2","sa","");

        // http://http://localhost:4567/
        //get("/",(request,response)-> "Hello");

        Statement estado = coneccion.createStatement();
        //select
        ResultSet resultados;
        // agregando estudiante a la tabla (data base)
        //estado.execute("INSERT INTO ESTUDIANTES VALUES('20112319','Djijelly','Siclait','8292184635')");
        //estado.execute("INSERT INTO ESTUDIANTES VALUES('20120844','Eduardo','Veras','8291112345')");
        //estado.execute("INSERT INTO ESTUDIANTES VALUES('20120890','juan','pedro','8292345617')");

        //Buscando los estudiantes registrados
        resultados = estado.executeQuery("SELECT * from ESTUDIANTES");

        while(resultados.next()){
            System.out.print("\n nombre: " + resultados.getString("Nombre"));
        }

        estado.execute("DELETE from ESTUDIANTES where MATRICULA= '20120890'");


        resultados = estado.executeQuery("SELECT * from ESTUDIANTES");
        while(resultados.next()){
            System.out.print("\n nombre: " + resultados.getString("Nombre"));
        }


        estado.execute("UPDATE ESTUDIANTES set NOMBRE='Frank' where NOMBRE= 'Eduardo'");
        resultados = estado.executeQuery("SELECT * from ESTUDIANTES");
        while(resultados.next()){
            System.out.print("\n nombre: " + resultados.getString("Nombre"));
        }



        //inventando
        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "");
            return new ModelAndView(attributes, "front.ftl");
        }, new FreeMarkerEngine());


        coneccion.close();

    }
}
