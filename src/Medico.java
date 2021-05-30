import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Medico extends Persona{

    // Generados parámetros de la clase
    private int no_seguridad_social;
    private int no_colegialo;

    // Constructor
    public Medico(String email, String dni, String nombre, String apellidos, String fechaNacimiento, String genero,
                  int no_seguridad_social, int no_colegialo) {
        super(email, dni, nombre, apellidos, fechaNacimiento, genero);
        this.no_colegialo = no_colegialo;
        this.no_seguridad_social = no_seguridad_social;

    }

    // Getters y setters
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
            System.out.println("\n\n\n----MENU MÉDICO----");
            System.out.print(
                "1 - Ver citas pendientes\n" +
                "2 - Ver el historial de un paciente\n" +
                "3 - Crear una nueva cita \n" +
                "4 - Mostrar datos de un paciente\n" +
                "5 - Salir\n" +
                "Introduce el número de la opcion que quieras realizar:"
            );
            switch (menu = input.nextLine()) {
                case "1":
                    ver_cita(getDni());
                    break;
                case "2":
                    historial_paciente();
                    break;
                case "3":
                    crearCita(getDni());
                    break;
                case "4":
                    datos_paciente();
                    break;
                case "5":
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.print("Introduce una opcion correcta: ");
            }
        }while (!menu.equals("5"));
    }

    //FUNCIONES UTILIZADAS EN 1) VER CITAS PENDIENTES
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

    public void ver_cita(String dni){


    }

    //FUNCIONES UTILIZADAS EN 2) VER HISTORIAL DE UN PACIENTE
    public void historial_paciente(){
        Scanner input = new Scanner(System.in);
        System.out.println("Introduce el dni del paciente del que se quieren ver los datos:");
        String dniPaciente = input.nextLine();
        /*
        if(dniPaciente == buscarCita(dniPaciente)){
            System.out.println("Estas son las citas de este Paciente: \n");
            return cargarCita;
        }
        else
            System.out.println("No se encontraron citas de este Paciente.");
        */
    }

    /*
    public Persona buscarCita(String dniPaciente){
        Gson gson = new Gson();
        Cita cita = null;
        boolean encontrado = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/Citas.jsonl"));
            String linea;
            while ((linea = br.readLine()) != null && encontrado == false) {
                cita = gson.fromJson(linea, Cita.class);
                if (cita.getDniPaciente().equals(dniPaciente)) {
                    encontrado = true;
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return cita;
    }

    public Persona cargarCita (String ruta, int tipo){
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
    */

    //FUNCIONES UTILIZADAS EN 3) CREAR UNA NUEVA CITA
    public void escribirCita(Cita nuevo, String ruta){
        Gson gson = new Gson();
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
            bw.write(gson.toJson(nuevo));
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
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

        return ("src/Citas/"+dia+"-"+mes+"-"+año+".jsonl");
    }

    public void crearCita(String dniMedico){
        Scanner input = new Scanner(System.in);

        System.out.print("Introduce el DNI del paciente:");
        String dniPaciente = input.nextLine();
        System.out.print("Introduce la hora:");
        String hora = input.nextLine();
        System.out.print("Introduce el diagnóstico:");
        String diagnostico = input.nextLine();
        System.out.print("Introduce la receta:");
        String receta = input.nextLine();

        String ruta = solicitarFecha();

        escribirCita(new Cita(dniMedico,dniPaciente,hora,diagnostico,receta),ruta);
    }

    //FUNCIONES UTILIZADAS EN 4) VER LOS DATOS DE UN PACIENTE
    public void datos_paciente(){
        Scanner input = new Scanner(System.in);
        System.out.println("Introduce el dni del paciente del que se quieren ver los datos:");
        String dniPaciente = input.nextLine();
        /*
        if(dniPaciente == buscarCita(dniPaciente)){
            System.out.println("Estas son las citas de este Paciente: \n");
            return cargarCita;
        }
        else
            System.out.println("No se encontraron citas de este Paciente.");
        */

    }
}