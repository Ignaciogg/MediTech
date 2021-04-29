import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Principal {

    public static boolean iniciarSesion (int tipo, String email, String contraseña){
        Gson gson = new Gson();

        switch (tipo){
            case 1: //Recepcionista

                break;
            case 2: //Paciente

                try (BufferedReader br = new BufferedReader(new FileReader("src/ficheros/123456789c.jsonl"))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        Persona persona = gson.fromJson(linea, Persona.class);
                        if (persona.getEmail().equals(email)){
                            if (persona.getContraseña().equals(contraseña)){
                                System.out.println("Has iniciado sesión correctamente");
                                return true;
                            }else {
                                System.out.println("la contraseña introducida no es correcta");
                                return false;
                            }
                        }
                        System.out.println(persona.toString());
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            case 3: //Medico
                try (BufferedReader br = new BufferedReader(new FileReader("src/ficheros/123456789b.jsonl"))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        Persona persona = gson.fromJson(linea, Persona.class);
                        if (persona.getEmail().equals(email)){
                            if (persona.getContraseña().equals(contraseña)){
                                System.out.println("Has iniciado sesión correctamente");
                                return true;
                            }else {
                                System.out.println("la contraseña introducida no es correcta");
                                return false;
                            }
                        }
                        System.out.println(persona.toString());
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

                break;
            case 4: //Administrador

                try (BufferedReader br = new BufferedReader(new FileReader("src/ficheros/123456789a.jsonl"))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        Persona persona = gson.fromJson(linea, Persona.class);
                        if (persona.getEmail().equals(email)){
                            if (persona.getContraseña().equals(contraseña)){
                                System.out.println("Has iniciado sesión correctamente");
                                return true;
                            }else {
                                System.out.println("la contraseña introducida no es correcta");
                                return false;
                            }
                        }
                        System.out.println(persona.toString());
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                break;
        }
        System.out.println("Los datos de inicio de sesion no son correctos");
        return false;
    }

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        Boolean inicioSesion = false;
        int tipo_usuario=0;
        String email, contraseña;

        System.out.println("   --- Bienvenido a MediTech (varsión 1.0) --- \n");

        do {
            //Preguntamos el tipo de usuario
            System.out.print(
                    "\t 1) Recepcionista\n" +
                    "\t 2) Paciente\n" +
                    "\t 3) Medico\n" +
                    "\t 4) Administrador\n" +
                    "Introduce el tipo de usuario con el que vas a iniciar sesion: ");

            if(input.hasNextInt())
                tipo_usuario = input.nextInt();

            else
                System.out.println("ERROR al introducir el tipo de usuario");


            input.nextLine(); //limpiar buffer Scanner

            //Solicitamos los datos de inicio de sesion
            System.out.print("Introduce el email:");
            email = input.nextLine();
            System.out.print("Introduce la contraseña:");
            contraseña = input.nextLine();

            inicioSesion = iniciarSesion(tipo_usuario, email, contraseña);
        }while (!inicioSesion);

    }
}
