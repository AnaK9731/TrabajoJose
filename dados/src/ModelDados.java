import java.util.Arrays;

public class ModelDados {
    /**
     * Atributos de mi clase
     */
    private Dados [] dado;
    private int [] caras;

    /**
     * Constructor
     */
    public ModelDados(){
        dado = new Dados[10];
        for (int i=0; i<10;i++){
            dado[i] =new Dados();
        }
    caras = new int[10];
}
    /**
     * Obtenemos el valor de caras
     */
    public void obtenerValorCaras(){
        for (int i=0; i<caras.length; i++){
            caras[i]=dado[i].getCara();
        }
}
public int[] getCaras(){
        return caras;
    }
    public static void main(String[] args) {
            ModelDados dado = new ModelDados();
            dado.getCaras();;
    }
}
