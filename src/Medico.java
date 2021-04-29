public class Medico extends Persona{


    // Generados parámetros de la clase
    private int no_seguridad_social;
    private int no_colegialo;

    //  Constructor

    public Medico(java.lang.String email, java.lang.String contraseña, java.lang.String dni, java.lang.String nombre, java.lang.String apellidos, java.lang.String fechaNacimiento, java.lang.String genero, int no_seguridad_social, int no_colegialo) {
        super(email, contraseña, dni, nombre, apellidos, fechaNacimiento, genero);
        this.no_colegialo = no_colegialo;
        this.no_seguridad_social = no_seguridad_social

    }

    // Generamos getters y setters
    public int getNo_seguridad_social() {
        return no_seguridad_social;
    }

    public void setNo_seguridad_social(int no_seguridad_social) {
        this.no_seguridad_social = no_seguridad_social;
    }
    public int getNo_colegialo() {
        return no_colegialo;
    }

    public void setNo_colegialo(int no_colegialo) {
        this.no_colegialo = no_colegialo;
    }
}
