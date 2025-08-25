import java.util.Scanner;

public class ejercicio {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int contador = 0;

        System.out.println("ingresa la cadena que quieras");
        String cadena = leer.nextLine();
        System.out.println("ingresa la letra que quieres comparar");
        char letra = leer.next().charAt(0);


        for(int i = 0; i<cadena.length();i++){
            char letraActual = cadena.charAt(i);
            if(letraActual == letra){
                contador ++;
            }
        }

        System.out.println("la letra: " + letra + " se repite " + contador + " veces en la frase: " + cadena);
        


    }

    
}
