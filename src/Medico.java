import com.google.gson.Gson;

import java.io.*;
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

    //FUNCIONES UTILIZADAS EN 1) VER CITA
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

    public void ver_cita(){

    }
    public void recetar(){

    }
    public void historial_paciente(){

    }

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

    public void final_cita(){
        Scanner input = new Scanner(System.in);

        System.out.print("Introduce el DNI del paciente:");
        String dniPaciente = input.nextLine();
        System.out.print("Introduce la fecha:");
        String fecha = input.nextLine();
        System.out.print("Introduce la hora:");
        String hora = input.nextLine();
        System.out.print("Introduce el diagnóstico:");
        String diagnostico = input.nextLine();
        System.out.print("Introduce la receta:");
        String receta = input.nextLine();

        String ruta = "src/ficheros/Administradores/" + fecha + ".jsonl";

        escribirCita(new Cita(dniPaciente,dniPaciente,fecha,hora,diagnostico,receta),ruta);
    }
    public void datos_paciente(){

    }
}
