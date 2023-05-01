public class ModelDados {

    //atributos
private Dados dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
private int meeple, hero, drake, cohete, points, extraLife;
private int [] caras;
private int dadosActivos;
private int dadosInactivos;
private int cantidadActivos, cantidadInactivos;

public ModelDados(){
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
    caras = new int[10];
}

public void obtenerValorCaras(){
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
}
//Aca validamos el poder de cada uno dependiendo de si callo en cierta posicion.  Luego creamos el metodo para cada una de las caras. y lo mandamos a llamar dentro de su respectivo condicional.
public void asignarPoder(){
    obtenerValorCaras();
    for (int i = 0; i<=6; i++){
        if (caras[i] == 1){
            System.out.println("dado:"+ i+ "= Meeple " );
        }else if (caras[i] == 6){
            System.out.println("dado:"+ i+ "= Cohete " );
        }else if (caras[i] == 2){
            System.out.println("dado:"+ i+ "= Points " );
        }else if (caras[i] == 5){
            System.out.println("dado:"+ i+ "= Extra Life " );
        }else if (caras[i] == 3){
            System.out.println("dado: "+i+ "= Hero");
        }else if (caras[i] == 4){
            System.out.println("dado:"+ i+ "= Drake " );
        }
    }
}

    /**
     * Metodos que retornan la cantidad de objetos que hay en los dados inactivos y activos.
     * @return
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
    }
}
