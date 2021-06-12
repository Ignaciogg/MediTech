import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.IOException;
import java.security.InvalidParameterException;
import javax.mail.MessagingException;

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
                "1 - Ver resultados de consultas\n" +
                "2 - Solicitar cita\n" +
                "3 - Cancelar cita\n" +
                "4 - Modificar cita\n" +
                "5 - Ver citas pendientes\n" +
                "6 - Salir\n" +
                "Introduce el número de la opcion que quieras realizar: "
            );
            switch (menu = input.nextLine()) {
                case "1":
                    historial_citas();
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
                case "5":
                    mostrar_citas();
                    break;
                case "6":
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.print("Introduce una opcion correcta: ");
                    break;
            }
        }while (!menu.equals("6"));
    }

    //1 - Ver resultados de consultas
    public void historial_citas(){
        String dniPaciente = getDni();

        //Obtenemos el día actual para buscar en las citas hasta este día
        int añoActual = LocalDateTime.now().getYear();
        int mesActual =LocalDateTime.now().getMonthValue();
        int diaActual = LocalDateTime.now().getDayOfMonth();
        String fechaActual="";
        if (diaActual < 10){
            fechaActual += "0"+diaActual+"-";
        }else {
            fechaActual += diaActual+"-";
        }
        if (mesActual < 10){
            fechaActual += "0"+mesActual+"-"+añoActual;
        }else {
            fechaActual += mesActual+"-"+añoActual;
        }

        //El primer día de funcionamiento del programa es el 28/04/2021
        //Buscaremos todas las citas del paciente desde ese día hasta el actual.
        int dia = 28;
        int mes = 4;
        int año = 2021;
        String fecha = dia+"-"+mes+"-"+año;

        while (fecha!=fechaActual){
            imprimirHistorial(fecha,dniPaciente);
            String[] separarFecha = fecha.split("-");
            dia = Integer.parseInt(separarFecha[0]);
            mes = Integer.parseInt(separarFecha[1]);
            año = Integer.parseInt(separarFecha[2]);
            fecha = siguienteDia(dia,mes,año);
        }
    }

    public void imprimirHistorial(String fecha, String dniPaciente){
        String ruta = "src/Ficheros/Citas/"+fecha+".jsonl";
        if (new File(ruta).exists()) {
            Gson gson = new Gson();
            Cita cita = null;
            File fichero = new File(ruta);
            try {
                FileReader fr = new FileReader(fichero);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                Paciente paciente = null;
                while ((linea = br.readLine()) != null) {
                    cita = gson.fromJson(linea, Cita.class);
                    if (cita.getDniPaciente().equalsIgnoreCase(dniPaciente)) {
                        System.out.println("Fecha: " + fecha + " Diagnóstico: " + cita.getDiagnostico() + " Receta: " + cita.getReceta());
                    }
                }
                br.close();
                fr.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public String siguienteDia(int dia, int mes, int año){
        boolean bisiesto = false;
        boolean mismoAnio = false;
        boolean mismoMes = false;
        int maxDias = 0;
        //año
        if(año == LocalDateTime.now().getYear()) mismoAnio = true;
        if ((año % 4 == 0) && ((año % 100 != 0) || (año % 400 == 0)) ) bisiesto = true;
        //mes
        if (mismoAnio && (mes == LocalDateTime.now().getMonthValue())){
            mismoMes = true;
        }else {
            if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                maxDias = 31;
            } else {
                if (mes == 2) {
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

        if(mismoMes){
            dia++;
        }else{
            if (dia<maxDias){
                dia++;
            }else{
                dia=1;
                if(mes<12){
                    mes++;
                }else {
                    mes=1;
                    año++;
                }
            }
        }

        String fecha="";
        if (dia < 10){
            fecha += "0"+dia+"-";
        }else {
            fecha += dia+"-";
        }
        if (mes < 10){
            fecha += "0"+mes+"-"+año;
        }else {
            fecha += mes+"-"+año;
        }
        return fecha;
    }

    //2 - Solicitar cita
    public void solicitar_cita(){

        try {
            Correo m = new Correo("src/config/Paciente.prop");

            m.enviarEmail("Solicitud Cita", "", "meditech.recepcionista@gmail.com");

            System.out.println("Se ha enviado!!");
        } catch (InvalidParameterException | IOException | MessagingException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //3 - Cancelar cita
    public void cancelar_cita(){
        try {
            Correo m = new Correo("src/config/Paciente.prop");

            m.enviarEmail("Cancelar Cita", "", "meditech.recepcionista@gmail.com");

            System.out.println("Se ha enviado!!");
        } catch (InvalidParameterException | IOException | MessagingException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //4 - Modificar cita
    public void modificar_citas(){
        try {
            Correo m = new Correo("src/config/Paciente.prop");

            m.enviarEmail("Modificar Cita", "", "meditech.recepcionista@gmail.com");

            System.out.println("Se ha enviado!!");
        } catch (InvalidParameterException | IOException | MessagingException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //5 - Ver citas pendientes
    public void mostrar_citas(){
        String dniPaciente = getDni();

        //Obtenemos el día actual para buscar en las citas desde este día
        int año = LocalDateTime.now().getYear();
        int mes =LocalDateTime.now().getMonthValue();
        int dia = LocalDateTime.now().getDayOfMonth();
        String fecha="";
        if (dia < 10){
            fecha += "0"+dia+"-";
        }else {
            fecha += dia+"-";
        }
        if (mes < 10){
            fecha += "0"+mes+"-"+año;
        }else {
            fecha += mes+"-"+año;
        }
        Boolean citaEncontrada = false;

        for(int i = 0; i < 90 ; i++){
            if(!citaEncontrada){
                citaEncontrada=imprimirCita(fecha,dniPaciente);
            }else{
                imprimirCita(fecha,dniPaciente);
            }
            String[] separarFecha = fecha.split("-");
            dia = Integer.parseInt(separarFecha[0]);
            mes = Integer.parseInt(separarFecha[1]);
            año = Integer.parseInt(separarFecha[2]);
            fecha = siguienteDia(dia,mes,año);
        }
        if(citaEncontrada){
            System.out.println("Estas son todas las citas que tienes en los próximos 3 meses.");
        }else {
            System.out.println("No tiene ninguna cita en los próximos 3 meses.");
        }
    }

    public Boolean imprimirCita (String fecha, String dniPaciente){
        String ruta = "src/Ficheros/Citas/"+fecha+".jsonl";
        Boolean citaEncontrada=false;
        if (new File(ruta).exists()) {
            Gson gson = new Gson();
            Cita cita = null;
            File fichero = new File(ruta);
            try {
                FileReader fr = new FileReader(fichero);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                Paciente paciente = null;
                while ((linea = br.readLine()) != null) {
                    cita = gson.fromJson(linea, Cita.class);
                    if (cita.getDniPaciente().equalsIgnoreCase(dniPaciente) && (cita.getReceta()==null && cita.getDiagnostico()==null)) {
                        Medico medico = buscarMedico(cita.getDniMedico());
                        System.out.println("Fecha: " + fecha + " Hora: " + cita.getHora() + " Medico: " + medico.getNombre()+" "+medico.getApellidos());
                        citaEncontrada=true;
                    }
                }
                br.close();
                fr.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return citaEncontrada;
    }

    public Medico buscarMedico(String dniMedico){
        Gson gson = new Gson();
        Medico medico = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/Medicos/"+ dniMedico +".jsonl"));
            String linea;
            linea = br.readLine();
            medico = gson.fromJson(linea, Medico.class);
            br.close();
        }catch (IOException e){
            System.out.println(e);
        }
        return medico;
    }

}