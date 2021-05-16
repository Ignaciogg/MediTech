import com.google.gson.Gson;

import java.io.*;
import java.lang.module.FindException;
import java.util.Scanner;

public class Administrador extends Persona {

    // constructores
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

    public Persona buscarUsuario(String dni){
        Gson gson = new Gson();
        Persona persona = null;
        boolean encontrado = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/login.jsonl"));
            String linea;
            while ((linea = br.readLine()) != null && encontrado == false) {
                persona = gson.fromJson(linea, Persona.class);
                if (persona.getDni().toLowerCase().equals(dni)) {
                    encontrado = true;
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return persona;
    }

    public Persona cargarUsuario(String ruta, int tipo){
        Gson gson = new Gson();
        Persona persona = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            linea = br.readLine();
            switch (tipo){
                case 1:
                    persona = gson.fromJson(linea, Administrador.class);
                    break;
                case 2:
                    persona = gson.fromJson(linea, Medico.class);
                    break;
                case 3:
                    persona = gson.fromJson(linea, Paciente.class);
                    break;
                case 4:
                    persona = gson.fromJson(linea, Recepcionista.class);
                    break;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return persona;
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

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":
                    System.out.println("Elejiste salir \n");
                    break;
                default:
                    System.out.print("Introduce una opcion correcta: ");
            }
        }while (!menuAD.equals("5"));
    }

    //Metodo a revisar
    public boolean EliminarUsuarioLogin(String dni){
        Gson gson = new Gson();
        Persona persona = null;
        File ficheroViejo = new File("src/ficheros/login.jsonl");
        File ficheroNuevo = new File("src/ficheros/login2.jsonl");
        try {
            BufferedReader br = new BufferedReader(new FileReader(ficheroViejo));
            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroNuevo,true));

            String linea;
            while ((linea = br.readLine()) != null) {
                persona = gson.fromJson(linea, Persona.class);
                if (!persona.getDni().toLowerCase().equals(dni)) {
                    bw.append(gson.toJson(persona));
                    bw.flush();
                    bw.newLine();
                }
            }
            br.close();
            bw.close();
            System.out.println("fichero viejo duplicado");
            if(ficheroViejo.delete()){ //Aquí deberí eliminara el original
                System.out.println("fichero viejo eliminado");
                File renombrar = new File("src/ficheros/loginmanolo.jsonl");
                if(ficheroNuevo.renameTo(renombrar)){ //Aquí debería renombrarlo al nombre original
                    System.out.println("fichero renombrado");
                    return true;
                }else{
                    System.out.println("error al renombrar fichero");
                }
            }else {
                System.out.println("error al eliminar fichero");
            }
        }catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public void ModificarUsuario(){
        Scanner input = new Scanner(System.in);
        System.out.print("Introduce el DNI del usuario que quieres modificar: ");
        String DNI = input.nextLine();
        Persona personaLoginAntiguo = buscarUsuario(DNI);
        if(personaLoginAntiguo==null){
            System.out.println("No se ga encontrado ningun usuario con ese Dni");
        }else{
            Persona personaLoginNuevo = personaLoginAntiguo;

            String menu = "0";
            String ruta = "";

            switch (personaLoginAntiguo.getGenero()) {
                case "1":
                    ruta = "src/ficheros/Administradores/"+personaLoginAntiguo.getDni()+".json";
                    Persona personaFicheroAntiguo = cargarUsuario(ruta,1);
                    Persona personaFicheroNuevo = personaFicheroAntiguo;
                    do {
                        System.out.println("\n\n----Modificar a un administrador----");
                        System.out.print(
                            "1 - Email\n" +
                            "2 - Contraseña\n" +
                            "3 - DNI\n" +
                            "4 - Nombre\n" +
                            "5 - Apellidos\n" +
                            "6 - Fecha de nacimiento\n" +
                            "7 - Genero\n" +
                            "8 - Guardar y salir\n" +
                            "Introduce el número del dato que quieras modificar: "
                        );
                        switch (menu = input.nextLine()) {
                            case "1":
                                System.out.print("Introduce el nuevo email: ");
                                String email = input.nextLine();
                                //Comprobar formato email
                                personaFicheroNuevo.setEmail(email);
                                personaLoginNuevo.setEmail(email);
                                break;
                            case "2":
                                System.out.print("Introduce la nueva contraseña: ");
                                String contraseña = input.nextLine();
                                personaLoginNuevo.setContraseña(contraseña);
                                break;
                            case "3":
                                System.out.print("Introduce el nuevo dni: ");
                                String dni = input.nextLine();
                                personaFicheroNuevo.setDni(dni);
                                personaLoginNuevo.setDni(dni);
                                break;
                            case "4":
                                System.out.print("Introduce el nuevo nombre: ");
                                String nombre = input.nextLine();
                                personaFicheroNuevo.setNombre(nombre);
                                break;
                            case "5":
                                System.out.print("Introduce el nuevo apellido: ");
                                String apellido = input.nextLine();
                                personaFicheroNuevo.setApellidos(apellido);
                                break;
                            case "6":
                                System.out.print("Introduce la nueva fecha de nacimiento: ");
                                String fechaNacimiento = input.nextLine();
                                personaFicheroNuevo.setFechaNacimiento(fechaNacimiento);
                                break;
                            case "7":
                                System.out.print("Introduce el nuevo genero: ");
                                String genero = input.nextLine();
                                personaFicheroNuevo.setGenero(genero);
                                break;
                            case "8":
                                //Eliminar usuario
                                //guardar de nuevo
                                break;
                            default:
                                System.out.println("Introduce una opción válida");
                        }
                    }while (!menu.equals("8"));
                    break;
                case "2":
                    ruta = "src/ficheros/Medicos/"+personaLoginAntiguo.getDni()+".json";

                    break;
                case "3":
                    ruta = "src/ficheros/Pacientes/"+personaLoginAntiguo.getDni()+".json";

                    break;
                case "4":
                    ruta = "src/ficheros/Recepcionista/"+personaLoginAntiguo.getDni()+".json";

                    break;
            }

        }
    }

    public void EliminarUsuario(){
        Scanner input = new Scanner(System.in);
        System.out.print("Introduce el DNI del usuario que quieres modificar: ");
        String DNI = input.nextLine();
        Persona personaBuscada = buscarUsuario(DNI);
        if (personaBuscada != null){
            if(EliminarUsuarioLogin(DNI)){
                String ruta = "";
                switch (personaBuscada.getGenero()) {
                    case "1":
                        ruta = "src/ficheros/Administradores/" + personaBuscada.getDni() + ".json";
                        break;
                    case "2":
                        ruta = "src/ficheros/Medicos/" + personaBuscada.getDni() + ".json";
                        break;
                    case "3":
                        ruta = "src/ficheros/Paciente/" + personaBuscada.getDni() + ".json";
                        break;
                    case "4":
                        ruta = "src/ficheros/Recepcionista/" + personaBuscada.getDni() + ".json";
                        break;
                }
                File fichero = new File(ruta);
                if(fichero.delete()){
                    System.out.println("El fichero del usuario se ha eliminado correctamente");
                }else {
                    System.out.println("Error al eliminar el fichero del usuario");
                }
            }else{
                System.out.println("No se ha encontrado ningun usuario con ese dni");
            }
        }else {
            System.out.println("Error al eliminar el usuario de la lista de login");
        }
    }

}
