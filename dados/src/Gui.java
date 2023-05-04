import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class Gui extends JFrame {
    //atributos
    private JPanel score, inactiveDice,activeDice, usedDice;
    private JPanel containerImage, inactiveDiceLayout, usedDiceLayout, scoreLayout;
    private JPanel panelPrincipal;
    private  Meeple meeple;

    //Label para mostrar una imagen con la ayuda en el JOPTION PANE
    private JLabel Guide;
    private int contadorActivos = 0;

    //imagenes para los dados
    private JLabel dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
    private JButton startRound, help;
    private ModelDados modelDados;
    private Escucha escucha;
    private ImageIcon imageDados;
    private  Title title;
    private  Dados dice = new Dados();

    //crear la ventana

    public Gui() {
        initGUI();
        this.setTitle("Geek out masters");
        this.setSize(705, 500);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initGUI() {
        // se crea un panel con el diseno que queremos aplicar
        panelPrincipal = new JPanel(new GridLayout(2,2));
        score = new JPanel(new BorderLayout());
        inactiveDice = new JPanel(new BorderLayout());
        activeDice = new JPanel(new BorderLayout());
        usedDice = new JPanel(new BorderLayout());

        //Paneles que van dentro de los paneles, para poder meter dentro las imagenes o txt area.
        containerImage = new JPanel();
        inactiveDiceLayout = new JPanel();
        usedDiceLayout = new JPanel();
        scoreLayout = new JPanel();

        dado1 = new JLabel(imageDados);
        dado2 = new JLabel(imageDados);
        dado3 = new JLabel(imageDados);
        dado4 = new JLabel(imageDados);
        dado5 = new JLabel(imageDados);
        dado6 = new JLabel(imageDados);
        dado7 = new JLabel(imageDados);
        dado8 = new JLabel(imageDados);
        dado9 = new JLabel(imageDados);
        dado10 = new JLabel(imageDados);

        //Create listener object and control object
        escucha= new Escucha();
        modelDados= new ModelDados();
        meeple = new Meeple();

    //Titulo para la ventana mostrando el nombre de geekoutMasters
        //panelPrincipal.add(title=new Title("Geek Out Masters", Color.GRAY));

        //Ponemos bordes en cada panel xd
        Border border = BorderFactory.createLineBorder(Color.black);
        score.setBorder(border);
        inactiveDice.setBorder(border);
        activeDice.setBorder(border);
        usedDice.setBorder(border);

        //se anaden los paneles al panel con el diseno de cuatro partes
        //Panel de dados activos.
        panelPrincipal.add(activeDice);
        activeDice.add(title = new Title("Dados activos", Color.GRAY));
        activeDice.add(title, BorderLayout.NORTH);
        activeDice.add(containerImage, BorderLayout.CENTER);

        startRound = new JButton("Gira los dados");
        startRound.addActionListener(escucha);
        activeDice.add(startRound, BorderLayout.SOUTH);

        //Escuchas para añadir a panel de dados usados
        dado1.addMouseListener(escucha);
        dado2.addMouseListener(escucha);
        dado3.addMouseListener(escucha);
        dado4.addMouseListener(escucha);
        dado5.addMouseListener(escucha);
        dado6.addMouseListener(escucha);
        dado7.addMouseListener(escucha);

        //Panel de score
        panelPrincipal.add(score);
        score.add(title = new Title("Puntaje", Color.black));
        score.add(title, BorderLayout.NORTH);

        help = new JButton("Guia");
        help.addActionListener(escucha);
        score.add(help, BorderLayout.PAGE_END);

        //Panel dados inactivos
        panelPrincipal.add(inactiveDice);
        inactiveDice.add(title = new Title("Dados Inactivos", Color.GRAY));
        inactiveDice.add(title,BorderLayout.NORTH);
        inactiveDice.add(inactiveDiceLayout, BorderLayout.CENTER);

        //Panel dados usados.
        panelPrincipal.add(usedDice);
        usedDice.add(title = new Title("Dados usados", Color.black));
        usedDice.add(title, BorderLayout.NORTH);
        usedDice.add(usedDiceLayout, BorderLayout.CENTER);

        //anadimos el panel principal con el diseno a la ventana
        getContentPane().add(panelPrincipal);
    }
    /**
     * Metodo que me deja el panel de dados usados despues de empezar una ronda.
     */
    public void empezarRonda(){
        usedDiceLayout.revalidate();
        usedDiceLayout.repaint();
        contadorActivos++;
    }
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui myGui = new Gui();
            }
        });
    }
    private class Escucha implements ActionListener, MouseListener {
        //Para el boton de empezar ronda.
        @Override
        public void actionPerformed(ActionEvent e) {
            //Añadimos la opcion de visualizar una ayuda en la GUI. Lo que hacen los dados y las puntuaciones.
            if (e.getSource()==help){
                imageDados = new ImageIcon(getClass().getResource("/recursos/ayuda.png"));
                Guide = new JLabel(imageDados);
                JOptionPane.showMessageDialog(null, Guide, "Guia de Juego", JOptionPane.PLAIN_MESSAGE);
            }
            //Iniciar los valores de los dados al empezar ronda.
            if (e.getSource()== startRound && contadorActivos==0) {
               // modelDados.asignarPoder();
                JLabel arrayLabels[]={dado1,dado2,dado3,dado4,dado5,dado6,dado7,dado8,dado9,dado10};
                int[] caras = modelDados.getCaras();
                modelDados.obtenerValorCaras();
                for (int i= 0; i<arrayLabels.length; i++){
                    if (i>=7){
                        imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[i] + ".png"));
                        arrayLabels[i].setIcon(imageDados);
                        inactiveDiceLayout.add(arrayLabels[i]);
                    } else {
                        imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[i] + ".png"));
                        arrayLabels[i].setIcon(imageDados);
                        containerImage.add(arrayLabels[i]);
                    }
                }

                //Para contar si hay un drake o un 42
                for (int i=0; i< caras.length; i++){
                    if (caras[i]==4  && arrayLabels[i].isAncestorOf(containerImage)) {
                        contadorActivos++;
                        System.out.println(contadorActivos);
                    } else if (caras[i]==2 && arrayLabels[i].isAncestorOf(containerImage)) {
                        contadorActivos++;
                        System.out.println(contadorActivos);
                    }
                }
                empezarRonda();
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            //Creacion de Jlabels en un array para iterarlas de forma sencilla.
            JLabel arrayLabel[]={dado1,dado2,dado3,dado4,dado5,dado6,dado7,dado8,dado9,dado10};
            int[] caras = modelDados.getCaras();
            //En este array anadimos una funcion para que cuando se de click en un dado en paneles activos, estos se quiten y se vayan
            //A paneles usados.
            for (int i=0; i<arrayLabel.length; i++){
                if (e.getSource()==arrayLabel[i] && caras[i] !=4 && caras[i] != 2){
                    System.out.println(contadorActivos);
                    contadorActivos++;
                    activeDice.remove(arrayLabel[i]);
                    usedDiceLayout.add(arrayLabel[i]);
                    activeDice.revalidate();
                    activeDice.repaint();
                }
            } if (contadorActivos >7) {
                JOptionPane.showMessageDialog(null, "NO HAY MAS ACTIVOS LANZA LOS DADOS DE NUEVO.");
                contadorActivos=0;
            }
                //Para probar que se esta contando bien los dados activos.
                //Crear un metodo que verifique si es un dragon o un PUNTO, y sume a los dados activos.
                //Crear un metodo que cuando se seleccione un corazon no se sume a los dados activos.
                //Quitarle el evento del click al 42 y al drake.

            //En este ciclo reconocemos el valor que tiene caras[] y si es ==1 seria un meeple y asi con cada cara y su valor.
            //Lo que buscamos aca es intentar implementar una forma en la cual se llamen los escuchas de cada accion
            //Para asi poder aplicarlos.
            for (int i= 0; i<arrayLabel.length; i++){
                if (e.getSource()==arrayLabel[i] &&caras[i]==1){
                    JOptionPane.showMessageDialog(null, "MEEPLE");
                    arrayLabel[i].removeMouseListener(escucha);
                    arrayLabel[i].addMouseListener(meeple);
                } else if (e.getSource()==arrayLabel[i] && caras[i]==3) {
                    JOptionPane.showMessageDialog(null, "HERO");
                }else if (e.getSource()==arrayLabel[i] && caras[i]==5) {
                    JOptionPane.showMessageDialog(null, "CORAZON");
                }else if (e.getSource()==arrayLabel[i] && caras[i]==6) {
                    JOptionPane.showMessageDialog(null, "COHETE");
                }
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    private class Meeple implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel arrayLabel[]={dado1,dado2,dado3,dado4,dado5,dado6,dado7,dado8,dado9,dado10};
            int[] caras = modelDados.getCaras();

            //En este array anadimos una funcion para que cuando se de click en un dado en paneles activos, estos se quiten y se vayan
            //A paneles usados.

            for (int i=0; i<arrayLabel.length; i++) {
                if (e.getSource() == arrayLabel[i] ) {
                    caras[i]= dice.getCara();
                    imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[i] + ".png"));
                    arrayLabel[i].setIcon(imageDados);
                    containerImage.add(arrayLabel[i]);
                    activeDice.revalidate();
                    activeDice.repaint();
                    arrayLabel[i].removeMouseListener(this);
                    addMouseListener(escucha);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}

