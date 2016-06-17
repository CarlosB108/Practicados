/**
 * Created by carlosb108 on 5/29/16.
 */
public class Estudiantes {


    String matricula;
    String nombre;
    String apellido;
    String telefono;


    public Estudiantes(String _matricula, String _nombre,String _apellido, String _telefono) {


        this.setMatricula(_matricula);
        this.setApellido(_apellido);
        this.setNombre(_nombre);
        this.setTelefono(_telefono);

    }


    public Estudiantes(){


    }

    public  String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }


    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
