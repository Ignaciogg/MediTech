import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

public class Administrador extends Persona {

    // constructores
    public Administrador(String email, String contraseña, String dni, String nombre, String apellidos, String fechaNacimiento, String genero) {
        super(email, contraseña, dni, nombre, apellidos, fechaNacimiento, genero);
    }
    public Administrador(String email, String dni, String nombre, String apellidos, String fechaNacimiento, String genero) {
        super(email, dni, nombre, apellidos, fechaNacimiento, genero);
    }

    public void Menu(){
        Scanner input = new Scanner(System.in);
        String menu = "0";
        do {
            System.out.println("\n\n\n----MENU ADMIN----");
            System.out.print(
                "1 - Dar de alta a un usuario\n" +
                "2 - Modificar a un usuario\n" +
                "3 - Eliminar a un usuario\n" +
                "4 - Salir\n" +
                "Introduce el número de la opcion que quieras realizar: "
            );
            switch (menu = input.nextLine()) {
                case "1":
                    CrearUsuario();
                    break;
                case "2":
                    ModificarUsuario();
                    break;
                case "3":
                    EliminarUsuario();
                    break;
                case "4":
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.print("Introduce una opcion correcta: ");
            }
        }while (!menu.equals("4"));
    }

    public void escribirLogin(Persona nuevo){
        Gson gson = new Gson();
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/ficheros/login.jsonl",true));
            bw.newLine();
            bw.append(gson.toJson(nuevo));
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void escribirPersona(Persona nuevo, String ruta){
        Gson gson = new Gson();
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
            bw.write(gson.toJson(nuevo));
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void generarAdministrador(){
        Scanner input = new Scanner(System.in);

        System.out.print("Introduce el email:");
        String email = input.nextLine();
        //GENERAR AUTOMATICO Y MANDAR POR CORREO
        System.out.print("Introduce la contraseña:");
        String contraseña = input.nextLine();
        System.out.print("Introduce el dni:");
        String dni = input.nextLine();
        System.out.print("Introduce el nombre:");
        String nombre = input.nextLine();
        System.out.print("Introduce los apellidos:");
        String apellidos = input.nextLine();
        System.out.print("Introduce la fecha de nacimiento:");
        String fechaNacimiento = input.nextLine();
        System.out.print("Introduce el género:");
        String genero = input.nextLine();

        String ruta = "src/ficheros/Administradores/" + dni + ".jsonl";

        escribirLogin(new Persona(email,contraseña,dni,"1"));
        escribirPersona(new Administrador(email,dni,nombre,apellidos,fechaNacimiento,genero),ruta);
    }

    public void generarMedico(){
        Scanner input = new Scanner(System.in);

        System.out.print("Introduce el email:");
        String email = input.nextLine();
        //GENERAR AUTOMATICO Y MANDAR POR CORREO
        System.out.print("Introduce la contraseña:");
        String contraseña = input.nextLine();
        System.out.print("Introduce el dni:");
        String dni = input.nextLine();
        System.out.print("Introduce el nombre:");
        String nombre = input.nextLine();
        System.out.print("Introduce los apellidos:");
        String apellidos = input.nextLine();
        System.out.print("Introduce la fecha de nacimiento:");
        String fechaNacimiento = input.nextLine();
        System.out.print("Introduce el género:");
        String genero = input.nextLine();
        System.out.print("Introduce el nº de la Seguridad Social:");
        int no_seguridad_social = input.nextInt();
        System.out.print("Introduce el nº de colegialo:");
        int no_colegialo = input.nextInt();

        String ruta = "src/ficheros/Medicos/" + dni + ".jsonl";

        escribirLogin(new Persona(email,contraseña,dni,"2"));
        escribirPersona(new Medico(email,dni,nombre,apellidos,fechaNacimiento,genero, no_seguridad_social, no_colegialo),ruta);
    }

    public void generarRecepcionista(){
        Scanner input = new Scanner(System.in);

        System.out.print("Introduce el email:");
        String email = input.nextLine();
        //GENERAR AUTOMATICO Y MANDAR POR CORREO
        System.out.print("Introduce la contraseña:");
        String contraseña = input.nextLine();
        System.out.print("Introduce el dni:");
        String dni = input.nextLine();
        System.out.print("Introduce el nombre:");
        String nombre = input.nextLine();
        System.out.print("Introduce los apellidos:");
        String apellidos = input.nextLine();
        System.out.print("Introduce la fecha de nacimiento:");
        String fechaNacimiento = input.nextLine();
        System.out.print("Introduce el género:");
        String genero = input.nextLine();
        System.out.print("Introduce número de la seguridad social");
        int no_seguridad_social = input.nextInt();

        String ruta = "src/ficheros/Recepcionistas/" + dni + ".jsonl";

        escribirLogin(new Persona(email,contraseña,dni,"4"));
        escribirPersona(new Recepcionista(email,dni,nombre,apellidos,fechaNacimiento,genero,no_seguridad_social),ruta);
    }

    public void CrearUsuario(){
        Scanner input = new Scanner(System.in);
        String menuAD = "0";
        do {
            System.out.println("\n\n----Dar de alta a un usuario----");
            System.out.print(
                "1 - Admin\n" +
                "2 - Medico\n" +
                "3 - Paciente\n" +
                "4 - Recepcionista\n" +
                "5 - Salir\n" +
                "Introduce el número del usuario que quieras dar de alta: "
            );
            switch (menuAD = input.nextLine()) {
                case "1":
                    generarAdministrador();
                    break;
                case "2":
                    generarMedico();
                    break;
                case "3":

                    break;
                case "4":
                    generarRecepcionista();
                    break;
                case "5":
                    System.out.println("Elejiste salir \n");
                    break;
                default:
                    System.out.print("Introduce una opcion correcta: ");
            }
        }while (!menuAD.equals("5"));
    }

    public void ModificarUsuario(){

    }

    public void EliminarUsuario(){

    }

}
