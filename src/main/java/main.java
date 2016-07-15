/**
 * Created by carlosb108 on 5/29/16.
 */


import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;


import freemarker.template.Configuration;

import javax.swing.plaf.PanelUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.h2.tools.Server;



import static spark.Spark.*;


public class main {

    private static Statement estado;
    private static ResultSet resultados;


    public static void main(String [] args) throws Exception {

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        setPort(port);

        Server server = null;
        try {
            server = Server.createTcpServer("-tcpAllowOthers").start();
        } catch (SQLException e) {
            System.out.println("FAILED TO START SERVER, CLOSE H2 IF YOU HAVE IT OPENED");
            e.printStackTrace();
        }




        // http://http://localhost:4567/
        Class.forName("org.h2.Driver");
        Connection coneccion = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/practica2", "sa", "");
        estado = coneccion.createStatement();



        // Conectando con el front-end

        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "");
            attributes.put("lista", crearlista().toArray());
            return new ModelAndView(attributes, "front.ftl");
        }, new FreeMarkerEngine());




        post("/delete", (request, response) -> {
            //response.status(200);
            // query de eliminacion usando request.queryparmas para coger los datos enviados por otras paginas.
            borrarfila(request.queryParams("matricula"));
            response.redirect("/");
            return "No se pudo realizar";
        });


        get("/edit", (request, response) -> {
            // Spark FreeMarker
            Map<String, Object> attributes = new HashMap<>();
            Estudiantes estudiante = procesomodificacion(request.queryParams("matricula"));

            if( estudiante == null ) halt("Estudiante invalido!");

            attributes.put("matricula", request.queryParams("matricula"));

            attributes.put("nombre", estudiante.getNombre());

            attributes.put("apellido", estudiante.getApellido());

            attributes.put("telefono", estudiante.getTelefono());



            return new ModelAndView(attributes, "edit.ftl");
        }, new FreeMarkerEngine());


//            get("/edit",  (request, response) -> { return "A"; } );

        post("/edit", (request, response) -> {
            //response.status(200);
            //relizar query de modificacion usando request.queryparmas para coger los datos enviados por otras paginas.
            String mat = request.queryParams("matricula");
            String nom = request.queryParams("nombre");
            String apell = request.queryParams("apellido");
            String tel = request.queryParams("telefono");
            modificarLista(mat,nom,apell,tel);
            System.out.print("A3");
            response.redirect("/");
            return "No se pudo realizar";
        });

        get("/create", (request, response) -> {
            // Spark FreeMarker
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "creater.ftl");
        }, new FreeMarkerEngine());




        post("/create", (request, response) -> {
            //response.status(200);
            //relizar query de modificacion usando request.queryparmas para coger los datos enviados por otras paginas.
            String mat = request.queryParams("matricula");
            String nom = request.queryParams("nombre");
            String apell = request.queryParams("apellido");
            String tel = request.queryParams("telefono");
            crearEstudiante(mat,nom,apell,tel);
            response.redirect("/");
            return "No se pudo realizar";
        });


    }


    public static ArrayList<Estudiantes> crearlista() throws Exception {
        ArrayList<Estudiantes> listas = new ArrayList<>();
        resultados = estado.executeQuery("SELECT * from ESTUDIANTES");

        while(resultados.next()){
            listas.add(new Estudiantes(resultados.getString("matricula"), resultados.getString("nombre"), resultados.getString("apellidos"),resultados.getString("telefono")));
        }
        return listas;
    }



    public static void borrarfila(String matricula) throws Exception {

        estado.executeUpdate("DELETE from ESTUDIANTES WHERE MATRICULA= '"+ matricula + "'");


    }

    public static void crearEstudiante(String matricula, String nombre, String apellido ,  String telefono) throws Exception{

        estado.executeUpdate("INSERT INTO ESTUDIANTES values('"+ matricula + "','"+ nombre +"','"+ apellido + "','"+ telefono +"')");



    }





    public static void modificarLista(  String matricula, String nombre, String apellido ,  String telefono) throws Exception{

        estado.executeUpdate("UPDATE ESTUDIANTES SET NOMBRE = '"+ nombre +"',APELLIDOS = '"+ apellido + "', TELEFONO = '"+ telefono +"' WHERE MATRICULA='"+ matricula + "'");
    }



    public static Estudiantes procesomodificacion(String matricula) throws Exception{

        Estudiantes estudiante = null;
        ResultSet resultados;
        resultados = estado.executeQuery("Select * From ESTUDIANTES WHERE MATRICULA='" + matricula + "'");

        while(resultados.next())
            estudiante = new Estudiantes(resultados.getString("matricula"), resultados.getString("nombre"), resultados.getString("apellidos"), resultados.getString("telefono"));


        return estudiante;
    }


}










