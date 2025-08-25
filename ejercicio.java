import java.util.Scanner;

public class ejercicio {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        StringBuilder NuevaFrase = new StringBuilder();

        System.out.println("ingresa la cadena que quieras");
        String cadena = leer.nextLine();
        System.out.println("ingresa la letra que quieres eliminar");
        char letra = leer.next().charAt(0);


        for(int i = 0; i<cadena.length();i++){
            char letraActual = cadena.charAt(i);
            if(letraActual != letra){
                NuevaFrase.append(letraActual);
            }
        }

        System.out.println("la cadena: " + cadena + " con la letra: " + letra + " eliminada se veria: " + NuevaFrase.toString());
        
    }

    
}
