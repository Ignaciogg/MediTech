import java.util.Scanner;

public class Recepcionista extends Persona{

    // Generados parámetros de la clase
    private int no_seguridad_social;


    //  Constructor

    public Recepcionista(java.lang.String email, java.lang.String contraseña, java.lang.String dni, java.lang.String nombre, java.lang.String apellidos, java.lang.String fechaNacimiento, java.lang.String genero, int no_seguridad_social) {
        super(email, contraseña, dni, nombre, apellidos, fechaNacimiento, genero);
        this.no_seguridad_social = no_seguridad_social

    }

    // Generamos getters y setters
    public int getNo_seguridad_social() {
        return no_seguridad_social;
    }

    public void setNo_seguridad_social(int no_seguridad_social) {
        this.no_seguridad_social = no_seguridad_social;
    }


    // Menú del recepcionista y sus respectivas funciones necesarias



    public void Menu_medico(){
        Scanner input = new Scanner(System.in);
        String menu = "0";
        do {
            System.out.println("\n\n\n----MENU ADMIN----");
            System.out.print(
                    "1 - Crear cita\n" +
                            "2 - Cancelar cita a un paciente\n" +
                            "3 - Modificar cita de un paciente\n" +
                            "4 - Enviar recordatorio a un paciente de su cita\n" +
                            "5 - Salir\n" +
                            "Introduce el número de la opcion que quieras realizar: "
            );
            switch (menu = input.nextLine()) {
                case "1":
                    Crear_cita();
                    break;
                case "2":
                    Cancelar_sita();
                    break;
                case "3":
                    Modificar_cita();
                    break;
                case "4":
                    recordar_cita();
                    break;
                case "5":
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.print("Introduce una opcion correcta: ");
            }
        }while (!menu.equals("5"));
    }
    public void Crear_cita{}
    public void Cancelar_sita{}
    public void Modificar_cita{}
    public void recordar_cita{}
}
