import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

public class Administrador extends Persona {

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
                    try {
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
                        System.out.println("Administrador agregado con exito!");

                    } catch (Exception e) {
                        System.out.println("Error al introducir un nuevo Administrador.");
                    }

                    break;
                case "2":
                    try {
                        System.out.print("Introduce el email:");
                        String email = input.nextLine();
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

                        String ruta = "src/ficheros/Medicos/" + dni + ".jsonl";

                        try {
                            FileWriter DarAltaAdmin = new FileWriter((ruta), true);
                            DarAltaAdmin.write("{" + "'email': " + email + ", " + "'contraseña': " + contraseña + ", " +
                                    "'dni': " + dni + ", " + "'nombre': " + nombre + ", " + "'apellidos': " + apellidos + ", " +
                                    "'fechaNacimiento': " + fechaNacimiento + ", " + "'genero': " + genero + "}" + "\n");
                            DarAltaAdmin.close();

                        } catch (IOException e) {

                            System.out.println("No se encontró el fichero");
                        }
                        System.out.println("Médico agregado con exito!");

                    } catch (Exception e) {
                        System.out.println("Error al introducir un nuevo Médico.");
                    }
                    break;
                case "3":
                    try {
                        System.out.print("Introduce el email:");
                        String email = input.nextLine();
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

                        String ruta = "src/ficheros/Pacientes/" + dni + ".jsonl";

                        try {
                            FileWriter DarAltaAdmin = new FileWriter((ruta), true);
                            DarAltaAdmin.write("{" + "'email': " + email + ", " + "'contraseña': " + contraseña + ", " +
                                    "'dni': " + dni + ", " + "'nombre': " + nombre + ", " + "'apellidos': " + apellidos + ", " +
                                    "'fechaNacimiento': " + fechaNacimiento + ", " + "'genero': " + genero + "}" + "\n");
                            DarAltaAdmin.close();

                        } catch (IOException e) {

                            System.out.println("No se encontró el fichero");
                        }
                        System.out.println("Paciente agregado con exito!");

                    } catch (Exception e) {
                        System.out.println("Error al introducir un nuevo Paciente.");
                    }
                    break;
                case "4":
                    try {
                        System.out.print("Introduce el email:");
                        String email = input.nextLine();
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

                        String ruta = "src/ficheros/Recepcionistas/" + dni + ".jsonl";

                        try {
                            FileWriter DarAltaAdmin = new FileWriter((ruta), true);
                            DarAltaAdmin.write("{" + "'email': " + email + ", " + "'contraseña': " + contraseña + ", " +
                                    "'dni': " + dni + ", " + "'nombre': " + nombre + ", " + "'apellidos': " + apellidos + ", " +
                                    "'fechaNacimiento': " + fechaNacimiento + ", " + "'genero': " + genero + "}" + "\n");
                            DarAltaAdmin.close();

                        } catch (IOException e) {

                            System.out.println("No se encontró el fichero");
                        }
                        System.out.println("Recepcionista agregado con exito!");

                    } catch (Exception e) {
                        System.out.println("Error al introducir un nuevo Recepcionista.");
                    }
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
