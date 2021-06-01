import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Principal {

    public static Persona iniciarSesion (String email, String contraseña){
        Gson gson = new Gson();
        int inicioSesion = 0;
        Persona persona = null;
        try {
            FileReader fr = new FileReader("src/ficheros/login.jsonl");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null && inicioSesion==0) {
                persona = gson.fromJson(linea, Persona.class);
                if (persona.getEmail().toLowerCase().equals(email)){
                    if (persona.getContraseña().equals(contraseña)){
                        String ruta="";
                        switch (persona.getGenero()){ //Seleccionar la ruta
                            case "1":
                                ruta = "src/ficheros/Administradores/"+persona.getDni()+".jsonl";
                                try{
                                    br = new BufferedReader(new FileReader(ruta));
                                    persona = gson.fromJson(br.readLine(), Administrador.class);
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;

                            case "2":
                                ruta = "src/ficheros/Medicos/"+persona.getDni()+".jsonl";
                                try{
                                    br = new BufferedReader(new FileReader(ruta));
                                    persona = gson.fromJson(br.readLine(), Medico.class);
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;

                            case "3":
                                ruta = "src/ficheros/Pacientes/"+persona.getDni()+".jsonl";
                                try{
                                    br = new BufferedReader(new FileReader(ruta));
                                    persona = gson.fromJson(br.readLine(), Paciente.class);
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;

                            case "4":
                                ruta = "src/ficheros/Recepcionistas/"+persona.getDni()+".jsonl";
                                try{
                                    br = new BufferedReader(new FileReader(ruta));
                                    persona = gson.fromJson(br.readLine(), Recepcionista.class);
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                break;
                        }
                        System.out.println("Has iniciado sesión correctamente");
                        inicioSesion = 1;
                    }else {
                        System.out.println("La contraseña introducida no es correcta");
                        inicioSesion = 2;
                    }
                }
            }
            fr.close();
            br.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        if(inicioSesion==1){
            return persona;
        }
        System.out.println("Los datos de inicio de sesión no son correctos");
        return null;
    }

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        Persona usuarioActivo = null;
        String email, contraseña;

        System.out.println("   --- Bienvenido a MediTech (versión 1.0) --- \n");
        do{
            //Solicitamos los datos de inicio de sesion
            System.out.print("Introduce el email:");
            email = (input.nextLine()).toLowerCase();
            System.out.print("Introduce la contraseña:");
            contraseña = input.nextLine();

            usuarioActivo = iniciarSesion(email, contraseña);

        }while (usuarioActivo==null);
        System.out.println(usuarioActivo);
        usuarioActivo.Menu();
    }
}
