import java.util.Arrays;

public class ModelDados {

    /**
     * Atributos de mi clase
     */
    private Dados [] dado;
    //private Dados dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
private int meeple, hero, drake, cohete, points, extraLife;
private int [] caras;
private int dadosActivos;
private int dadosInactivos;
private String[] valor = {"", "", "", "", "", "", "", null, null, null};
private int cantidadActivos, cantidadInactivos;


    /**
     * Constructor
     */
    public ModelDados(){
        dado = new Dados[10];
        for (int i=0; i<10;i++){
            dado[i] =new Dados();
        }
        /*
    dado1 = new Dados();
    dado2 = new Dados();
    dado3 = new Dados();
    dado4 = new Dados();
    dado5 = new Dados();
    dado6 = new Dados();
    dado7 = new Dados();
    dado8 = new Dados();
    dado9 = new Dados();
    dado10 = new Dados();
         */
    caras = new int[10];
}

    /**
     * Obtenemos el valor de caras
     */
    public void obtenerValorCaras(){
        for (int i=0; i<caras.length; i++){
            caras[i]=dado[i].getCara();
        }
        /*
    caras[0]= dado1.getCara();
    caras[1]= dado2.getCara();
    caras[2]= dado3.getCara();
    caras[3]= dado4.getCara();
    caras[4]= dado5.getCara();
    caras[5]= dado6.getCara();
    caras[6]= dado7.getCara();
    caras[7]= dado8.getCara();
    caras[8]= dado9.getCara();
    caras[9]= dado10.getCara();

         */
}

    /**
     * Este metodo me asigna un poder especifico dentro de la variable valor
     * recorre la variable caras en la cual se asigna un numero y si ese numero corresponde
     * con el valor que se pide, sera entonces un meeple o un cohete etc.
     */
    public void asignarPoder(){
    obtenerValorCaras();
    for (int i = 0; i<7; i++){
        if (caras[i] == 1){
            valor[i] = "meeple";
        }else if (caras[i] == 6){
            valor[i] = "cohete";
        }else if (caras[i] == 2){
            valor[i] = "points";
        }else if (caras[i] == 5){
            valor[i] = "life";
        }else if (caras[i] == 3){
            valor[i] = "hero";
        }else if (caras[i] == 4){
            valor[i] = "drake";
        }
    }

    public String[] asignarValor(){
        return valor;
    }

    /**
     * Metodos que retornan la cantidad de objetos que hay en los dados inactivos y activos.
     * @return cantidad de dados activos.
     */

    public int getDadosActivos() {
        dadosActivos = caras.length - 3;
        System.out.println(dadosActivos);
        return dadosActivos;
    }

    public int getDadosInactivos() {
        dadosInactivos =3;
        System.out.println(dadosInactivos);
        return dadosInactivos;
    }

public int[] getCaras(){
        return caras;
    }
    public static void main(String[] args) {
            ModelDados dado = new ModelDados();
            dado.getCaras();
            dado.asignarPoder();
            dado.getDadosActivos();
            dado.getDadosInactivos();
       // String[] valores = dado.asignarValor();
       // System.out.println(Arrays.toString(valores));
    }
}
