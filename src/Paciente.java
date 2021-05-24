import java.util.Scanner;

public class Paciente extends Persona{

    // Declaramos las variables de la clase
    private double altura;
    private double peso;
    private String patologías;
    private String alergias;
    private String grupo_sanguineo;

    // Constructor

    public Paciente(String email, String dni, String nombre, String apellidos, String fechaNacimiento, String genero,
                    double altura, double peso, String patologías, String alergias, String grupo_sanguineo) {
        super(email, dni, nombre, apellidos, fechaNacimiento,genero);
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

    public String getPatologías() {
        return patologías;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setPatologías(String patologías) {
        this.patologías = patologías;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getGrupo_sanguineo() {
        return grupo_sanguineo;
    }

    public void setGrupo_sanguineo(String grupo_sanguineo) {
        this.grupo_sanguineo = grupo_sanguineo;
    }

// Menú del paciente y sus respectivas funciones necesarias

    public void Menu(){
        Scanner input = new Scanner(System.in);
        String menu = "0";
        do {
            System.out.println("\n\n\n----MENU PACIENTE----");
            System.out.print(
                "1 - Ver citas pendientes\n" +
                "2 - Solicitar cita\n" +
                "3 - Cancelar cita\n" +
                "4 - Modificar cita\n" +
                "5 - Ver resultados de consultas\n" +
                "6 - Salir\n" +
                "Introduce el número de la opcion que quieras realizar: "
            );
            switch (menu = input.nextLine()) {
                case "1":
                    mostrar_citas();
                    break;
                case "2":
                   solicitar_cita();
                    break;
                case "3":
                    cancelar_cita();
                    break;
                case "4":
                    modificar_citas();
                    break;
                case "6":
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.print("Introduce una opcion correcta: ");
            }
                    resultados_citas();
                    break;

        }while (!menu.equals("6"));
    }

    public void mostrar_citas(){

    }
    public void solicitar_cita(){

    }
    public void cancelar_cita(){

    }
    public void modificar_citas(){

    }
    public void resultados_citas(){

    }

}