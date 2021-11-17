import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Administrador extends Persona {

    //Constructor
    public Administrador(String email, String dni, String nombre, String apellidos, String fechaNacimiento, String genero) {
        super(email, dni, nombre, apellidos, fechaNacimiento, genero);
    }

    //Menú principal
    public void Menu(){
        Scanner input = new Scanner(System.in);
        String menu = "0";
        do {
            System.out.println("\n\n\n----MENU ADMIN----");
            System.out.print(
                "0 - Salir\n" +
                "1 - Dar de alta a un usuario\n" +
                "2 - Modificar a un usuario\n" +
                "3 - Eliminar a un usuario\n" +
                "4 - Contar usuarios totales\n" +
                "5 - Contar administradores\n" +
                "6 - Contar medicos\n" +
                "7 - Contar recepcionistas\n" +
                "8 - Contar pacientes\n" +
                "9 - Salir\n" +
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
                    int usuarios = contar_usuarios("src/ficheros/Login.jsonl", 0);
                    System.out.println("Hay " + usuarios + " usuarios registrados");
                    pulsaEnterParaContinuar();
                    break;
                case "5":
                    int admins = contar_usuarios("src/ficheros/Login.jsonl", 1);
                    System.out.println("Hay " + admins + " administradores registrados");
                    pulsaEnterParaContinuar();
                    break;
                case "6":
                    int medicos = contar_usuarios("src/ficheros/Login.jsonl", 2);
                    System.out.println("Hay " + medicos + " medicos registrados");
                    pulsaEnterParaContinuar();
                    break;
                case "7":
                    int recepcionistas = contar_usuarios("src/ficheros/Login.jsonl", 4);
                    System.out.println("Hay " + recepcionistas + " recepcionistas registrados");
                    pulsaEnterParaContinuar();
                    break;
                case "8":
                    int pacientes = contar_usuarios("src/ficheros/Login.jsonl", 3);
                    System.out.println("Hay " + pacientes + " pacientes registrados");
                    pulsaEnterParaContinuar();
                    break;
                case "9":
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.print("Introduce una opcion correcta: ");
            }
        }while (!menu.equals("9"));
    }

    public void escribirLogin(Persona nuevo){
        Gson gson = new Gson();
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/ficheros/login.jsonl",true));
            bw.newLine();
            bw.append(gson.toJson(nuevo));
            bw.flush();
            bw.close();
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
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //FUNCIONES UTILIZADAS EN 1) CREAR NUEVO USUARIO

    public void CrearUsuario(){
        Scanner input = new Scanner(System.in);
        String menuCU = "0";
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
            switch (menuCU = input.nextLine()) {
                case "1":
                    generarAdministrador();
                    break;
                case "2":
                    generarMedico();
                    break;
                case "3":
                    generarPaciente();
                    break;
                case "4":
                    generarRecepcionista();
                    break;
                case "5":
                    System.out.println("Elegiste salir \n");
                    break;
                default:
                    System.out.print("Introduce una opcion correcta: ");
            }
        }while (!menuCU.equals("5"));
    }

    public String solicitarFecha(){
        Scanner input = new Scanner(System.in);
        boolean salir = false;
        boolean bisiesto = false;
        boolean mismoAnio = false;
        boolean mismoMes = false;
        int maxDias = 0;

        String dia,mes,año;

        //año
        do {
            System.out.print("Introduce el año de la cita:");
            año = input.nextLine();
            if (Integer.parseInt(año) >= LocalDateTime.now().getYear()) {
                salir = true;
                if(Integer.parseInt(año) == LocalDateTime.now().getYear()) mismoAnio = true;
                if ((Integer.parseInt(año) % 4 == 0) && ((Integer.parseInt(año) % 100 != 0) || (Integer.parseInt(año) % 400 == 0)) ) bisiesto = true;
            }
        } while (!salir) ;
        salir = false;
        //mes
        do {
            System.out.print("Introduce el mes de la cita:");
            mes = input.nextLine();
            int mesEntero = Integer.parseInt(mes);
            if (mesEntero > 0 && mesEntero < 13) {
                if (mismoAnio) {
                    if (mesEntero >= LocalDateTime.now().getMonthValue()) {
                        if (mesEntero == LocalDateTime.now().getMonthValue()) mismoMes = true;
                        salir = true;
                        if (mesEntero == 1 || mesEntero == 3 || mesEntero == 5 || mesEntero == 7 || mesEntero == 8 || mesEntero == 10 || mesEntero == 12) {
                            maxDias = 31;
                        } else {
                            if (mesEntero == 2) {
                                if (bisiesto) {
                                    maxDias = 29;
                                } else {
                                    maxDias = 28;
                                }
                            } else {
                                maxDias = 30;
                            }
                        }
                    }
                }else {
                    salir = true;
                    if (mesEntero == 1 || mesEntero == 3 || mesEntero == 5 || mesEntero == 7 || mesEntero == 8 || mesEntero == 10 || mesEntero == 12) {
                        maxDias = 31;
                    } else {
                        if (mesEntero == 2) {
                            if (bisiesto) {
                                maxDias = 29;
                            } else {
                                maxDias = 28;
                            }
                        } else {
                            maxDias = 30;
                        }
                    }
                }
            }
        }while (!salir);
        salir = false;
        //dia
        do {
            System.out.print("Introduce el dia de la cita:");
            dia = input.nextLine();
            if (Integer.parseInt(dia) > 0 && Integer.parseInt(dia) <= maxDias) {
                if (mismoMes) {
                    if (Integer.parseInt(dia) >= LocalDateTime.now().getDayOfMonth()) {
                        salir = true;
                    }
                }else {
                    salir = true;
                }
            }
        }while (!salir);
        if (Integer.parseInt(mes) < 10) mes = "0"+Integer.parseInt(mes);
        if (Integer.parseInt(dia) < 10) dia = "0"+Integer.parseInt(dia);

        return (dia + "-"+mes+"-"+año);
    }

    public void generarAdministrador(){
        Scanner input = new Scanner(System.in);

        String email = verificadorEmail();
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
        String fechaNacimiento = solicitarFecha();
        System.out.print("Introduce el género:");
        String genero = input.nextLine();

        String ruta = "src/ficheros/Administradores/" + dni + ".jsonl";

        escribirLogin(new Persona(email,contraseña,dni,"1"));
        escribirPersona(new Administrador(email,dni,nombre,apellidos,fechaNacimiento,genero),ruta);
    }

    public void generarMedico(){
        Scanner input = new Scanner(System.in);
        String email = verificadorEmail();
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
        String fechaNacimiento = solicitarFecha();
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

    public void generarPaciente(){
        Scanner input = new Scanner(System.in);

        String email = verificadorEmail();
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
        String fechaNacimiento = solicitarFecha();
        System.out.print("Introduce el género:");
        String genero = input.nextLine();
        System.out.print("Introduce la altura:");
        Double altura = input.nextDouble();
        System.out.print("Introduce el peso:");
        Double peso = input.nextDouble();
        input.next();
        System.out.print("Introduce las patologías:");
        String patologías = input.nextLine();
        System.out.print("Introduce las alergias:");
        String alergias = input.nextLine();
        System.out.print("Introduce el grupo sanguíneo:");
        String grupo_sanguineo = input.nextLine();

        String ruta = "src/ficheros/Pacientes/" + dni + ".jsonl";

        escribirLogin(new Persona(email,contraseña,dni,"3"));
        escribirPersona(new Paciente(email,dni,nombre,apellidos,fechaNacimiento,genero,altura,peso,patologías,alergias,grupo_sanguineo),ruta);
    }

    public void generarRecepcionista(){
        Scanner input = new Scanner(System.in);

        String email = verificadorEmail();
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
        String fechaNacimiento = solicitarFecha();
        System.out.print("Introduce el género:");
        String genero = input.nextLine();
        System.out.print("Introduce número de la seguridad social");
        int no_seguridad_social = input.nextInt();
        String ruta = "src/ficheros/Recepcionistas/" + dni + ".jsonl";
        escribirLogin(new Persona(email,contraseña,dni,"4"));
        escribirPersona(new Recepcionista(email,dni,nombre,apellidos,fechaNacimiento,genero,no_seguridad_social),ruta);
    }

    //FUNCIONES UTILIZADAS EN 2/3) MODIFICAR/ELIMINAR USUARIO
    public Persona buscarUsuario(String dni){
        Gson gson = new Gson();
        Persona persona = null;
        boolean encontrado = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/Login.jsonl"));
            String linea;
            while ((linea = br.readLine()) != null && encontrado == false) {
                persona = gson.fromJson(linea, Persona.class);
                if (persona.getDni().equalsIgnoreCase(dni)) {
                    encontrado = true;
                }
            }
            br.close();
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
            br.close();
        }catch (IOException e){
            System.out.println(e);
        }
        return persona;
    }

    public boolean eliminarUsuarioLogin(String dni){
        Gson gson = new Gson();
        Persona persona = null;
        File ficheroViejo = new File("src/ficheros/Login.jsonl");
        File ficheroNuevo = new File("src/ficheros/Login2.jsonl");
        try {
            FileReader fr = new FileReader(ficheroViejo);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(ficheroNuevo,true);
            BufferedWriter bw = new BufferedWriter(fw);
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
            fr.close();
            bw.close();
            fw.close();
            if(ficheroViejo.delete()){
                if(ficheroNuevo.renameTo(new File("src/ficheros/login.jsonl"))){
                    return true;
                }
            }
            return false;
        }catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public void ModificarUsuario(){
        Scanner input = new Scanner(System.in);
        System.out.print("Introduce el DNI del usuario que quieres modificar: ");
        String DNI = input.nextLine();
        Persona personaLoginAntiguo = buscarUsuario(DNI);
        if(personaLoginAntiguo==null){
            System.out.println("No se ha encontrado ningún usuario con ese DNI");
        }else{
            Persona personaLoginNuevo = personaLoginAntiguo;
            String menu = "0";
            String ruta;
            Persona personaFicheroAntiguo,personaFicheroNuevo;
            String email,contraseña,dni,nombre,apellido,fechaNacimiento,genero;
            int no_ss,no_cole;
            double altura, peso;
            String patologías, alergias, grupo_sanguineo;

            switch (personaLoginAntiguo.getGenero()) {
                case "1":
                    ruta = "src/ficheros/Administradores/"+personaLoginAntiguo.getDni()+".jsonl";
                    personaFicheroAntiguo = cargarUsuario(ruta,1);
                    personaFicheroNuevo = personaFicheroAntiguo;
                    do {
                        System.out.println("\n\n----Modificar un Administrador----");
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
                                email = input.nextLine();
                                //Comprobar formato email
                                personaFicheroNuevo.setEmail(email);
                                personaLoginNuevo.setEmail(email);
                                break;
                            case "2":
                                System.out.print("Introduce la nueva contraseña: ");
                                contraseña = input.nextLine();
                                personaLoginNuevo.setContraseña(contraseña);
                                break;
                            case "3":
                                System.out.print("Introduce el nuevo dni: ");
                                dni = input.nextLine();
                                personaFicheroNuevo.setDni(dni);
                                personaLoginNuevo.setDni(dni);
                                break;
                            case "4":
                                System.out.print("Introduce el nuevo nombre: ");
                                nombre = input.nextLine();
                                personaFicheroNuevo.setNombre(nombre);
                                break;
                            case "5":
                                System.out.print("Introduce el nuevo apellido: ");
                                apellido = input.nextLine();
                                personaFicheroNuevo.setApellidos(apellido);
                                break;
                            case "6":
                                System.out.print("Introduce la nueva fecha de nacimiento: ");
                                fechaNacimiento = solicitarFecha();
                                personaFicheroNuevo.setFechaNacimiento(fechaNacimiento);
                                break;
                            case "7":
                                System.out.print("Introduce el nuevo genero: ");
                                genero = input.nextLine();
                                personaFicheroNuevo.setGenero(genero);
                                break;
                            case "8":
                                //Eliminar usuario
                                eliminarUsuarioLogin(personaLoginAntiguo.getDni());
                                File eliminar = new File(ruta);
                                eliminar.delete();
                                //guardar de nuevo
                                ruta = "src/ficheros/Administradores/"+personaFicheroNuevo.getDni()+".jsonl";
                                escribirLogin(personaLoginNuevo);
                                escribirPersona(personaFicheroNuevo,ruta);
                                break;
                            default:
                                System.out.println("Introduce una opción válida");
                        }
                    }while (!menu.equals("8"));
                    break;
                case "2":
                    ruta = "src/ficheros/Medicos/"+personaLoginAntiguo.getDni()+".jsonl";
                    personaFicheroAntiguo = cargarUsuario(ruta,2);
                    personaFicheroNuevo = personaFicheroAntiguo;
                    do {
                        System.out.println("\n\n----Modificar un Médico----");
                        System.out.print(
                            "1 - Email\n" +
                            "2 - Contraseña\n" +
                            "3 - DNI\n" +
                            "4 - Nombre\n" +
                            "5 - Apellidos\n" +
                            "6 - Fecha de nacimiento\n" +
                            "7 - Genero\n" +
                            "8 - Nº de Seguridad Social\n"+
                            "9 - Nº de Colegialo\n"+
                            "10 - Guardar y salir\n" +
                            "Introduce el número del dato que quieras modificar: "
                        );
                        switch (menu = input.nextLine()) {
                            case "1":
                                System.out.print("Introduce el nuevo email: ");
                                email = input.nextLine();
                                //Comprobar formato email
                                personaFicheroNuevo.setEmail(email);
                                personaLoginNuevo.setEmail(email);
                                break;
                            case "2":
                                System.out.print("Introduce la nueva contraseña: ");
                                contraseña = input.nextLine();
                                personaLoginNuevo.setContraseña(contraseña);
                                break;
                            case "3":
                                System.out.print("Introduce el nuevo dni: ");
                                dni = input.nextLine();
                                personaFicheroNuevo.setDni(dni);
                                personaLoginNuevo.setDni(dni);
                                break;
                            case "4":
                                System.out.print("Introduce el nuevo nombre: ");
                                nombre = input.nextLine();
                                personaFicheroNuevo.setNombre(nombre);
                                break;
                            case "5":
                                System.out.print("Introduce el nuevo apellido: ");
                                apellido = input.nextLine();
                                personaFicheroNuevo.setApellidos(apellido);
                                break;
                            case "6":
                                System.out.print("Introduce la nueva fecha de nacimiento: ");
                                fechaNacimiento = solicitarFecha();
                                personaFicheroNuevo.setFechaNacimiento(fechaNacimiento);
                                break;
                            case "7":
                                System.out.print("Introduce el nuevo genero: ");
                                genero = input.nextLine();
                                personaFicheroNuevo.setGenero(genero);
                                break;
                            case "8":
                                System.out.print("Introduce el nuevo nº de Seguridad Social: ");
                                no_ss = input.nextInt();
                                ((Medico) personaFicheroNuevo).getNo_seguridad_social();
                                break;
                            case "9":
                                System.out.print("Introduce el nuevo nº de Colegialo: ");
                                no_cole = input.nextInt();
                                ((Medico) personaFicheroNuevo).getNo_colegialo();
                                break;
                            case "10":
                                //Eliminar usuario
                                eliminarUsuarioLogin(personaLoginAntiguo.getDni());
                                File eliminar = new File(ruta);
                                eliminar.delete();
                                //guardar de nuevo
                                escribirLogin(personaLoginNuevo);
                                escribirPersona(personaFicheroNuevo,ruta);
                                break;
                            default:
                                System.out.println("Introduce una opción válida");
                        }
                    }while (!menu.equals("10"));
                    break;
                case "3":
                    ruta = "src/ficheros/Pacientes/"+personaLoginAntiguo.getDni()+".jsonl";
                    personaFicheroAntiguo = cargarUsuario(ruta,3);
                    personaFicheroNuevo = personaFicheroAntiguo;
                    do {
                        System.out.println("\n\n----Modificar un Paciente----");
                        System.out.print(
                                "1 - Email\n" +
                                        "2  - Contraseña\n" +
                                        "3  - DNI\n" +
                                        "4  - Nombre\n" +
                                        "5  - Apellidos\n" +
                                        "6  - Fecha de nacimiento\n" +
                                        "7  - Genero\n" +
                                        "8  - Altura\n" +
                                        "9  - Peso\n" +
                                        "10 - Patologías\n" +
                                        "11 - Alergias\n" +
                                        "12 - Grupo Sanguíneo\n" +
                                        "13 - Guardar y salir\n" +
                                        "Introduce el número del dato que quieras modificar: "
                        );
                        switch (menu = input.nextLine()) {
                            case "1":
                                System.out.print("Introduce el nuevo email: ");
                                email = input.nextLine();
                                //Comprobar formato email
                                personaFicheroNuevo.setEmail(email);
                                personaLoginNuevo.setEmail(email);
                                break;
                            case "2":
                                System.out.print("Introduce la nueva contraseña: ");
                                contraseña = input.nextLine();
                                personaLoginNuevo.setContraseña(contraseña);
                                break;
                            case "3":
                                System.out.print("Introduce el nuevo dni: ");
                                dni = input.nextLine();
                                personaFicheroNuevo.setDni(dni);
                                personaLoginNuevo.setDni(dni);
                                break;
                            case "4":
                                System.out.print("Introduce el nuevo nombre: ");
                                nombre = input.nextLine();
                                personaFicheroNuevo.setNombre(nombre);
                                break;
                            case "5":
                                System.out.print("Introduce el nuevo apellido: ");
                                apellido = input.nextLine();
                                personaFicheroNuevo.setApellidos(apellido);
                                break;
                            case "6":
                                System.out.print("Introduce la nueva fecha de nacimiento: ");
                                fechaNacimiento = solicitarFecha();
                                personaFicheroNuevo.setFechaNacimiento(fechaNacimiento);
                                break;
                            case "7":
                                System.out.print("Introduce el nuevo genero: ");
                                genero = input.nextLine();
                                personaFicheroNuevo.setGenero(genero);
                                break;
                            case "8":
                                System.out.print("Introduce la nueva altura: ");
                                altura = input.nextDouble();
                                ((Paciente) personaFicheroNuevo).getAltura();
                                break;
                            case "9":
                                System.out.print("Introduce el nuevo peso: ");
                                peso = input.nextDouble();
                                ((Paciente) personaFicheroNuevo).getPeso();
                                break;
                            case "10":
                                System.out.print("Introduce las nuevas patologías: ");
                                patologías = input.nextLine();
                                ((Paciente) personaFicheroNuevo).getPatologías();
                                break;
                            case "11":
                                System.out.print("Introduce las nuevas alergias: ");
                                alergias = input.nextLine();
                                ((Paciente) personaFicheroNuevo).getAlergias();
                                break;
                            case "12":
                                System.out.print("Introduce el nuevo grupo sanguíneo: ");
                                grupo_sanguineo = input.nextLine();
                                ((Paciente) personaFicheroNuevo).getGrupo_sanguineo();
                                break;
                            case "13":
                                //Eliminar usuario
                                eliminarUsuarioLogin(personaLoginAntiguo.getDni());
                                File eliminar = new File(ruta);
                                eliminar.delete();
                                //guardar de nuevo
                                escribirLogin(personaLoginNuevo);
                                escribirPersona(personaFicheroNuevo,ruta);
                                break;
                            default:
                                System.out.println("Introduce una opción válida");
                        }
                    }while (!menu.equals("13"));
                    break;
                case "4":
                    ruta = "src/ficheros/Recepcionista/"+personaLoginAntiguo.getDni()+".jsonl";
                    personaFicheroAntiguo = cargarUsuario(ruta,4);
                    personaFicheroNuevo = personaFicheroAntiguo;
                    do {
                        System.out.println("\n\n----Modificar un Recepcionista----");
                        System.out.print(
                                "1 - Email\n" +
                                        "2 - Contraseña\n" +
                                        "3 - DNI\n" +
                                        "4 - Nombre\n" +
                                        "5 - Apellidos\n" +
                                        "6 - Fecha de nacimiento\n" +
                                        "7 - Genero\n" +
                                        "8 - Nº de Seguridad Social\n"+
                                        "9 - Guardar y salir\n" +
                                        "Introduce el número del dato que quieras modificar: "
                        );
                        switch (menu = input.nextLine()) {
                            case "1":
                                System.out.print("Introduce el nuevo email: ");
                                email = input.nextLine();
                                //Comprobar formato email
                                personaFicheroNuevo.setEmail(email);
                                personaLoginNuevo.setEmail(email);
                                break;
                            case "2":
                                System.out.print("Introduce la nueva contraseña: ");
                                contraseña = input.nextLine();
                                personaLoginNuevo.setContraseña(contraseña);
                                break;
                            case "3":
                                System.out.print("Introduce el nuevo dni: ");
                                dni = input.nextLine();
                                personaFicheroNuevo.setDni(dni);
                                personaLoginNuevo.setDni(dni);
                                break;
                            case "4":
                                System.out.print("Introduce el nuevo nombre: ");
                                nombre = input.nextLine();
                                personaFicheroNuevo.setNombre(nombre);
                                break;
                            case "5":
                                System.out.print("Introduce el nuevo apellido: ");
                                apellido = input.nextLine();
                                personaFicheroNuevo.setApellidos(apellido);
                                break;
                            case "6":
                                System.out.print("Introduce la nueva fecha de nacimiento: ");
                                fechaNacimiento = solicitarFecha();
                                personaFicheroNuevo.setFechaNacimiento(fechaNacimiento);
                                break;
                            case "7":
                                System.out.print("Introduce el nuevo genero: ");
                                genero = input.nextLine();
                                personaFicheroNuevo.setGenero(genero);
                                break;
                            case "8":
                                System.out.print("Introduce el nuevo nº de Seguridad Social: ");
                                no_ss = input.nextInt();
                                ((Recepcionista) personaFicheroNuevo).getNo_seguridad_social();
                                break;
                            case "9":
                                //Eliminar usuario
                                eliminarUsuarioLogin(personaLoginAntiguo.getDni());
                                File eliminar = new File(ruta);
                                eliminar.delete();
                                //guardar de nuevo
                                escribirLogin(personaLoginNuevo);
                                escribirPersona(personaFicheroNuevo,ruta);
                                break;
                            default:
                                System.out.println("Introduce una opción válida");
                        }
                    }while (!menu.equals("9"));
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
            if(eliminarUsuarioLogin(DNI)){
                String ruta = "";
                switch (personaBuscada.getGenero()) {
                    case "1":
                        ruta = "src/ficheros/Administradores/" + personaBuscada.getDni() + ".jsonl";
                        break;
                    case "2":
                        ruta = "src/ficheros/Medicos/" + personaBuscada.getDni() + ".jsonl";
                        break;
                    case "3":
                        ruta = "src/ficheros/Pacientes/" + personaBuscada.getDni() + ".jsonl";
                        break;
                    case "4":
                        ruta = "src/ficheros/Recepcionista/" + personaBuscada.getDni() + ".jsonl";
                        break;
                }
                File fichero = new File(ruta);

                if(fichero.delete()){
                    System.out.println("El fichero del usuario se ha eliminado correctamente");
                }else {
                    System.out.println("Error al eliminar el fichero del usuario");
                }
            }else{
                System.out.println("Error al eliminar el usuario de la lista de login");
            }
        }else {
            System.out.println("No se ha encontrado ningun usuario con ese dni");
        }
    }

    public String verificadorEmail (){
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Scanner input = new Scanner(System.in);
        boolean correcto = false;
        String email = "";
        do{
            System.out.print("Introduce el email:");
            email = input.nextLine();
            Matcher mather = pattern.matcher(email);

            if (mather.find() == true) {
                System.out.println("El email ingresado es válido.");
                correcto=true;
            } else {
                System.out.println("El email ingresado es inválido.");
            }
        }while (!correcto);
        return  email;
    }

    public int contar_usuarios(String ruta, int tipo){
        int usuarios = 0;
        int admin = 0;
        int recepcionista = 0;
        int paciente = 0;
        int medico = 0;
        String linea;

        File fichero = new File(ruta);

        try {
            Scanner sc = new Scanner(fichero);
            while (sc.hasNextLine()) { // repite el bucle hasta que no haya usuarios en el documneto

                linea = sc.nextLine();
                usuarios++;

                if (linea.contains("\"genero\":\"1\"")){
                    admin++;
                }else if (linea.contains("\"genero\":\"2\"")){
                    medico++;
                }else if (linea.contains("\"genero\":\"3\"")){
                    paciente++;
                }else if (linea.contains("\"genero\":\"4\"")){
                    recepcionista++;
                }
            }
            sc.close(); // se cierra el escaner al salir del bucle
        } catch (FileNotFoundException e) { //en caso de no encontrar el archivo se recoge la excepcion si se imprime por pantalla un mensaje de error
            System.out.println("Error, no se puede abrir el fichero.");
        }

        if (tipo == 1){
            return admin;
        }else if (tipo == 2){
            return medico;
        }else if (tipo == 3){
            return paciente;
        }else if (tipo == 4){
            return recepcionista;
        }else {
            return usuarios;
        }

    }

    public static void pulsaEnterParaContinuar(){
        System.out.println("Pulsa la tecla enter para continuar...");
        try {
            System.in.read(); //espera una entrada por el usuario para continuar la ejecucion del programa
        } catch (Exception e) {
        }
    }

}
