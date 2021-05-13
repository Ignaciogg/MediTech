public class Persona {

    private String email;
    private String contraseña;
    private String dni;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String genero;

    public Persona(String email, String contraseña, String dni, String nombre, String apellidos, String fechaNacimiento, String genero) {
        this.email = email;
        this.contraseña = contraseña;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public Persona(String email, String dni, String nombre, String apellidos, String fechaNacimiento, String genero) {
        this.email = email;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public Persona(String email, String contraseña, String dni, String tipoUsuario) {
        this.email = email;
        this.contraseña = contraseña;
        this.dni = dni;
        this.genero = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void Menu(){}

    @Override
    public String toString() {
        return "Persona{" +
                "email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}