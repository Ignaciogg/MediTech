public class Paciente extends Persona{

    // Declaramos las variables de la clase
    private double altura;
    private String peso;
    private String patologías;
    private String alergias;
    private String grupo_sanguineo;



    // Constructor

    public Paciente(java.lang.String email, java.lang.String contraseña, java.lang.String dni, java.lang.String tipoUsuario, double altura, String patologías, String alergias, String grupo_sanguineo) {
        super(email, contraseña, dni, tipoUsuario);
        this.altura = altura;
        this.alergias = alergias;
        this.patologías = patologías;
        this.peso = peso;
        this.grupo_sanguineo = grupo_sanguineo;
    }



    // Getters and setters

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    private double peso;

    public java.lang.String getPatologías() {
        return patologías;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setPatologías(java.lang.String patologías) {
        this.patologías = patologías;
    }

    public java.lang.String getAlergias() {
        return alergias;
    }

    public void setAlergias(java.lang.String alergias) {
        this.alergias = alergias;
    }

    public java.lang.String getGrupo_sanguineo() {
        return grupo_sanguineo;
    }

    public void setGrupo_sanguineo(java.lang.String grupo_sanguineo) {
        this.grupo_sanguineo = grupo_sanguineo;
    }



}
