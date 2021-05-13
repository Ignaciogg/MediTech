import java.util.Scanner;

public class Medico extends Persona{

    // Generados parámetros de la clase
    private int no_seguridad_social;
    private int no_colegialo;

    //  Constructor

    public Medico(String email, String contraseña, String dni, String nombre, String apellidos, String fechaNacimiento, String genero, int no_seguridad_social, int no_colegialo) {
        super(email, contraseña, dni, nombre, apellidos, fechaNacimiento, genero);
        this.no_colegialo = no_colegialo;
        this.no_seguridad_social = no_seguridad_social;

    }
    public Medico(String email, String dni, String nombre, String apellidos, String fechaNacimiento, String genero, int no_seguridad_social, int no_colegialo) {
        super(email, dni, nombre, apellidos, fechaNacimiento, genero);
        this.no_colegialo = no_colegialo;
        this.no_seguridad_social = no_seguridad_social;

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

    // Menú del médico y sus respectivas funciones necesarias

    public void Menu(){
        Scanner input = new Scanner(System.in);
        String menu = "0";
        do {
            System.out.println("\n\n\n----MENU ADMIN----");
            System.out.print(
                "1 - Ver citas pendientes\n" +
                "2 - Recetar medicamento a un paciente\n" +
                "3 - Ver el historial de un paciente\n" +
                "4 - Concluir una cita \n" +
                "5 - Mostrar datos de un paciente\n" +
                "6 - Salir\n" +
                "Introduce el número de la opcion que quieras realizar: "
            );
            switch (menu = input.nextLine()) {
                case "1":
                    ver_cita();
                    break;
                case "2":
                    recetar();
                    break;
                case "3":
                    historial_paciente();
                    break;
                case "4":
                    final_cita();
                    break;
                case "5":
                    datos_paciente();
                    break;
                case "6":
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.print("Introduce una opcion correcta: ");
            }
        }while (!menu.equals("7"));
    }
    public void ver_cita(){

    }
    public void recetar(){

    }
    public void historial_paciente(){

    }
    public void final_cita(){

    }
    public void datos_paciente(){

    }
}
