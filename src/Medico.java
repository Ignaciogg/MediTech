import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Medico extends Persona{

    // Generados parámetros de la clase
    private int no_seguridad_social;
    private int no_colegialo;

    // Constructor
    public Medico(String email, String dni, String nombre, String apellidos, String fechaNacimiento, String genero, int no_seguridad_social, int no_colegialo) {
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
                "3 - Diagnostico de una cita \n" +
                "4 - Mostrar datos de un paciente\n" +
                "5 - Salir\n" +
                "Introduce el número de la opcion que quieras realizar:"
            );
            switch (menu = input.nextLine()) {
                case "1":
                    ver_cita();
                    break;
                case "2":
                    historial_paciente();
                    break;
                case "3":
                    diagnosticarCita();
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
    public void ver_cita(){
        int contador = 0;
        //Obtenemos el DNI del medico
        String dniMedico = getDni();
        //Obtenemos el día actual para buscar en las citas de este día
        int año = LocalDateTime.now().getYear();
        int mes =LocalDateTime.now().getMonthValue();
        int dia = LocalDateTime.now().getDayOfMonth();
        String ruta = "src/Ficheros/Citas/";
        if (dia < 10){
            ruta += "0"+dia+"-";
        }else {
            ruta += dia+"-";
        }
        if (mes < 10){
            ruta += "0"+mes+"-";
        }else {
            ruta += mes+"-";
        }
        ruta+= año+".jsonl";
        //Compruebo si existe un fichero de citas para este día
        if (new File(ruta).exists()){
            ArrayList <Cita> citas = new ArrayList<Cita>();
            Gson gson = new Gson();
            Cita cita = null;
            File ficheroViejo = new File(ruta);
            int contadorCitas = 0;
            System.out.println();
            try {
                FileReader fr = new FileReader(ficheroViejo);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                Paciente paciente = null;
                while ((linea = br.readLine()) != null) {
                    cita = gson.fromJson(linea, Cita.class);
                    if (cita.getDniMedico().equalsIgnoreCase(dniMedico)) {
                        if (cita.getDiagnostico().equals("null") && cita.getReceta().equals("null")){
                            ++contadorCitas;
                            citas.add(cita);
                            paciente=buscarPaciente(cita.getDniPaciente());
                            System.out.println(contadorCitas+") Paciente: "+paciente.getNombre()+" "+paciente.getApellidos()+" Hora: "+cita.getHora());
                            contador++;
                        }
                    }
                }
                br.close();
                fr.close();
                System.out.println("Tienes " + contador + " citas hoy");
            }catch (IOException e) {
                System.out.println(e);
            }
            if (contadorCitas!=0){
                System.out.println("\nEstas son todas las citas restantes que tiene hoy.");
                System.out.print("Si desea atender una cita introduce el número de la misma, sino pulse 0: ");
                Scanner input = new Scanner(System.in);
                String opcion = input.nextLine();
                if(Integer.parseInt(opcion)>0 && Integer.parseInt(opcion)<=citas.size()){
                    diagnosticarCita(citas.get(Integer.parseInt(opcion)-1));
                }else {
                    if (Integer.parseInt(opcion)==0){
                        System.out.println("Volviendo al menú principal.");
                    }else{
                        System.out.println("Valor no valido, volviendo al menú principal.");
                    }
                }
            }
        }else {
            System.out.println("No hay citas para el día de hoy");
        }


    }

    //FUNCIONES UTILIZADAS EN 2) VER HISTORIAL DE UN PACIENTE
    public void historial_paciente(){
        Scanner input = new Scanner(System.in);
        System.out.print("Introduce el dni del paciente del que se quieren ver los datos:");
        String dniPaciente = input.nextLine();

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
        System.out.println(fechaActual);
        while (!fechaActual.equals(fecha)){
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
                    if (cita.getDniPaciente().equalsIgnoreCase(dniPaciente) && (cita.getReceta()!=null || cita.getDiagnostico()!=null)) {
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

    //FUNCIONES UTILIZADAS EN 3) DIAGNOSTICAR UNA CITA
    public void diagnosticarCita() {
        //Obtenemos el DNI del medico
        String dniMedico = this.getDni();
        //Obtenemos el día actual para buscar en las citas de este día
        int año = LocalDateTime.now().getYear();
        int mes =LocalDateTime.now().getMonthValue();
        int dia = LocalDateTime.now().getDayOfMonth();
        String ruta = "src/Ficheros/Citas/";
        if (dia < 10){
            ruta += "0"+dia+"-";
        }else {
            ruta += dia+"-";
        }
        if (mes < 10){
            ruta += "0"+mes+"-";
        }else {
            ruta += mes+"-";
        }
        ruta+= año+".jsonl";
        System.out.println(ruta);
        //Compruebo si existe un fichero de citas para este día
        if (new File(ruta).exists()){
            //Solicito el resto de datos necesarios para conocer la cita actual
            Scanner input = new Scanner(System.in);
            System.out.print("Introduce el DNI del paciente:");
            String dniPaciente = input.nextLine();
            System.out.print("Introduce la hora de la cita:");
            String hora = input.nextLine();
            //Solicito los datos del diagnostico de la cita
            System.out.print("Introduce el diagnóstico:");
            String diagnostico = input.nextLine();
            System.out.print("Introduce la receta:");
            String receta = input.nextLine();
            //Actualizo los ficheros
            escribirCita(new Cita(dniMedico,dniPaciente,hora,diagnostico,receta),ruta);
        }else {
            System.out.println("No hay citas a día de hoy.");
        }
    }

    public void diagnosticarCita(Cita cita) {
        int año = LocalDateTime.now().getYear();
        int mes =LocalDateTime.now().getMonthValue();
        int dia = LocalDateTime.now().getDayOfMonth();
        String ruta = "src/Ficheros/Citas/";
        if (dia < 10){
            ruta += "0"+dia+"-";
        }else {
            ruta += dia+"-";
        }
        if (mes < 10){
            ruta += "0"+mes+"-";
        }else {
            ruta += mes+"-";
        }
        ruta+= año+".jsonl";

        Scanner input = new Scanner(System.in);
        //Solicito los datos del diagnostico de la cita
        System.out.print("Introduce el diagnóstico:");
        String diagnostico = input.nextLine();
        System.out.print("Introduce la receta:");
        String receta = input.nextLine();
        cita.setDiagnostico(diagnostico);
        cita.setReceta(receta);
        //Actualizo los ficheros
        escribirCita(cita,ruta);
    }

    public void escribirCita(Cita nuevo, String ruta){
        Gson gson = new Gson();
        Cita cita;
        boolean citaBuscada = false;
        File ficheroViejo = new File(ruta);
        File ficheroNuevo = new File("src/ficheros/citas/cita.jsonl");
        try {
            FileReader fr = new FileReader(ficheroViejo);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(ficheroNuevo,true);
            BufferedWriter bw = new BufferedWriter(fw);
            String linea;
            //reescribo el fichero en uno auxiliar quitando la cita
            while ((linea = br.readLine()) != null) {
                cita = gson.fromJson(linea, Cita.class);
                if (!cita.getDniPaciente().equalsIgnoreCase(nuevo.getDniPaciente())) {
                    bw.append(gson.toJson(cita));
                    bw.flush();
                    bw.newLine();
                }else{
                    if (!cita.getHora().equalsIgnoreCase(nuevo.getHora())){
                        bw.append(gson.toJson(cita));
                        bw.flush();
                        bw.newLine();
                    }else{
                        citaBuscada = true;
                    }
                }
            }
            br.close();
            fr.close();
            bw.close();
            fw.close();
            //si hemos encontrado la cita, la hemos eliminado por lo que ahora la escribimos de nuevo.
            if (citaBuscada){
                if(ficheroViejo.delete()){
                    if(ficheroNuevo.renameTo(new File(ruta))) {
                        FileWriter fw2 = new FileWriter(ruta, true);
                        BufferedWriter bw2 = new BufferedWriter(fw2);
                        bw2.append(gson.toJson(nuevo));
                        bw2.flush();
                        System.out.println("Diagnostico realizado correctamente");
                        bw2.close();
                        fw2.close();
                    }
                }
            }else {
                System.out.println("Cita no encontrada.");
            }
        }catch (IOException e) {
            System.out.println(e);
        }
    }

    //FUNCIONES UTILIZADAS EN 4) VER LOS DATOS DE UN PACIENTE
    public void datos_paciente(){
        Scanner input = new Scanner(System.in);
        System.out.println("Introduce el dni del paciente del que se quieren ver los datos:");
        String dniPaciente = input.nextLine();

        Paciente paciente = buscarPaciente(dniPaciente);
        if(paciente != null){
            System.out.println(paciente);
        }
        else
            System.out.println("Paciente no encontrado con ese DNI.");

    }

    public Paciente buscarPaciente(String dniPaciente){
        Gson gson = new Gson();
        Paciente paciente = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/ficheros/Pacientes/"+ dniPaciente +".jsonl"));
            String linea;
            linea = br.readLine();
            paciente = gson.fromJson(linea, Paciente.class);
            br.close();
        }catch (IOException e){
            System.out.println(e);
        }
        return paciente;
    }
}