import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Recepcionista extends Persona{

    // Generados parámetros de la clase
    private int no_seguridad_social;


    //  Constructor

    public Recepcionista(String email, String dni, String nombre, String apellidos, String fechaNacimiento,
                         String genero, int no_seguridad_social) {
        super(email, dni, nombre, apellidos, fechaNacimiento, genero);
        this.no_seguridad_social = no_seguridad_social;
    }

    // Generamos getters y setters
    public int getNo_seguridad_social() {
        return no_seguridad_social;
    }

    public void setNo_seguridad_social(int no_seguridad_social) {
        this.no_seguridad_social = no_seguridad_social;
    }

    // Menú del recepcionista y sus respectivas funciones necesarias

    public void Menu(){
        Scanner input = new Scanner(System.in);
        String menu = "0";
        do {
            System.out.println("\n\n\n----MENU RECEPCIONISTA----");
            System.out.print(
                "1 - Crear cita\n" +
                "2 - Cancelar cita a un paciente\n" +
                "3 - Modificar cita de un paciente\n" +
                "4 - Enviar recordatorio a un paciente de su cita\n" +
                        "5 - Generar un nuevo paciente\n" +
                "6 - Salir\n" +
                "Introduce el número de la opcion que quieras realizar: "
            );
            switch (menu = input.nextLine()) {
                case "1":
                    Crear_cita();
                    break;
                case "2":
                    Cancelar_cita();
                    break;
                case "3":
                    Modificar_cita();
                    break;
                case "4":
                    Recordar_cita();
                    break;
                case "5":
                    generarPaciente();
                    break;
                case "6":
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.print("Introduce una opcion correcta: ");
            }
        }while (!menu.equals("5"));
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

    public void generarPaciente(){
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
        System.out.print("Introduce la altura:");
        Double altura = input.nextDouble();
        System.out.print("Introduce el peso:");
        Double peso = input.nextDouble();
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

        return ("src/Citas/"+dia + "-"+mes+"-"+año+".jsonl");
    }

    public void Crear_cita() {
        Scanner input = new Scanner(System.in);
        System.out.print("Introduce el dni del paciente:");
        String paciente = input.nextLine();
        System.out.print("Introduce el dni del médico:");
        String medico = input.nextLine();
        //facha
        String ficheroNombre = solicitarFecha();
        //hora
        System.out.print("Introduce la hora de la cita:");
        String hora = input.nextLine();
        try {
            File cita = new File (ficheroNombre);
            if(!cita.exists()) {
                cita.createNewFile();
            }
            escribirCita(ficheroNombre,medico,paciente,hora);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirCita (String ruta, String medico, String paciente, String hora){
        Gson gson = new Gson();
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
            Cita nuevo = new Cita(medico,paciente,hora);
            bw.append(gson.toJson(nuevo));
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void Cancelar_cita(){
            System.out.print("");
    }

    public void Modificar_cita(){
            System.out.print("");
    }

    public void Recordar_cita(){
            System.out.print("");
    }
}
