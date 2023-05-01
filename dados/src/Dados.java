import java.util.Random;

public class Dados {

    private int cara;
    //Esta es mi funcion dado. Que le da un valor de 1 a 6 a cada uno de mis dados.
    public int getCara() {
        Random rand = new Random();
        cara = rand.nextInt(6) + 1;
        return cara;
    }

}
