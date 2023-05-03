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
    private  JLabel dadoAAcciona;
    private JLabel dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
    private JButton startRound, help;
    private ModelDados modelDados;
    private Escucha escucha;
    private ImageIcon imageDados;
    private  Title title;
    private  Dados dice = new Dados();

    private  String[] valor = {"", "", "", "", "", "", "", null, null, null};


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
    }

    public void accionesDados(){
        modelDados.getasignarPoder();
        //Cohete
        if ("meeple".equals(modelDados.getasignarPoder())){


        }

    }


    public void asignarPoder() {
        modelDados.obtenerValorCaras();
        for (int i = 0; i < 7; i++) {
            int[] caras = modelDados.getCaras();
            String[] valor = {"", "", "", "", "", "", "", null, null, null};
            if (caras[i] == 1) {
                valor[i] = "meeple";
            } else if (caras[i] == 6) {
                valor[i] = "cohete";
            } else if (caras[i] == 2) {
                valor[i] = "points";
            } else if (caras[i] == 5) {
                valor[i] = "life";
            } else if (caras[i] == 3) {
                valor[i] = "hero";
            } else if (caras[i] == 4) {
                valor[i] = "drake";
            }
        }
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
                imageDados=new ImageIcon(getClass().getResource("/recursos/ayuda.png"));
                Guide = new JLabel(imageDados);
                JOptionPane.showMessageDialog(null, Guide, "Guia de Juego", JOptionPane.PLAIN_MESSAGE);
            }


            //Iniciar los valores de los dados al empezar ronda.
            if (e.getSource()== startRound && contadorActivos==0) {
                modelDados.obtenerValorCaras();
                //modelDados.asignarPoder();
                int[] caras = modelDados.getCaras();
                imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[0] + ".png"));
                dado1.setIcon(imageDados);
                imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[1] + ".png"));
                dado2.setIcon(imageDados);
                imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[2] + ".png"));
                dado3.setIcon(imageDados);
                imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[3] + ".png"));
                dado4.setIcon(imageDados);
                imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[4] + ".png"));
                dado5.setIcon(imageDados);
                imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[5] + ".png"));
                dado6.setIcon(imageDados);
                imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[6] + ".png"));
                dado7.setIcon(imageDados);
                imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[7] + ".png"));
                dado8.setIcon(imageDados);
                imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[8] + ".png"));
                dado9.setIcon(imageDados);
                imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[9] + ".png"));
                dado10.setIcon(imageDados);
                containerImage.add(dado1);
                containerImage.add(dado2);
                containerImage.add(dado3);
                containerImage.add(dado4);
                containerImage.add(dado5);
                containerImage.add(dado6);
                containerImage.add(dado7);
                inactiveDiceLayout.add(dado8);
                inactiveDiceLayout.add(dado9);
                inactiveDiceLayout.add(dado10);
                empezarRonda();
                contadorActivos =0;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {



            /**for(int i = 0; i < modelDados.asignarValor().length; i++){
                if (e.getSource() == dado1){
                    if (valor[i].equals("meeple")){
                        JOptionPane.showMessageDialog(null, "MEEPLE");
                    }
                }
            }*/

            if (e.getComponent() instanceof JLabel) {
                if (e.getSource() == dado1) {
                    contadorActivos++;
                    activeDice.remove(dado1);
                    usedDiceLayout.add(dado1);
                    activeDice.revalidate();
                    activeDice.repaint();
                } if (e.getSource() == dado2) {
                    contadorActivos++;
                    activeDice.remove(dado2);
                    usedDiceLayout.add(dado2);
                    activeDice.revalidate();
                    activeDice.repaint();
                } if (e.getSource() == dado3) {
                    contadorActivos++;
                    activeDice.remove(dado3);
                    usedDiceLayout.add(dado3);
                    activeDice.revalidate();
                    activeDice.repaint();
                }if (e.getSource() == dado4) {
                    contadorActivos++;
                    activeDice.remove(dado4);
                    usedDiceLayout.add(dado4);
                    activeDice.revalidate();
                    activeDice.repaint();
                } if (e.getSource() == dado5) {
                    contadorActivos++;
                    activeDice.remove(dado5);
                    usedDiceLayout.add(dado5);
                    activeDice.revalidate();
                    activeDice.repaint();
                } if (e.getSource() == dado6) {
                    contadorActivos++;
                    activeDice.remove(dado6);
                    usedDiceLayout.add(dado6);
                    activeDice.revalidate();
                    activeDice.repaint();
                } if (e.getSource() == dado7) {
                    contadorActivos++;
                    activeDice.remove(dado7);
                    usedDiceLayout.add(dado7);
                    activeDice.revalidate();
                    activeDice.repaint();
                } if (e.getSource() == dado8 && containerImage.isAncestorOf(dado8)) {
                    contadorActivos++;
                    activeDice.remove(dado8);
                    usedDiceLayout.add(dado8);
                    activeDice.revalidate();
                    activeDice.repaint();
                } if (e.getSource() == dado9 && containerImage.isAncestorOf(dado9)) {
                    contadorActivos++;
                    activeDice.remove(dado9);
                    usedDiceLayout.add(dado9);
                    activeDice.revalidate();
                    activeDice.repaint();
                } if (e.getSource() == dado10 && containerImage.isAncestorOf(dado10)) {
                    contadorActivos++;
                    activeDice.remove(dado10);
                    usedDiceLayout.add(dado10);
                    activeDice.revalidate();
                    activeDice.repaint();
                }

                //Para probar que se esta contando bien los dados activos.
                //Crear un metodo que verifique si es un dragon o un PUNTO, y sume a los dados activos.
                //Crear un metodo que cuando se seleccione un corazon no se sume a los dados activos.
                //Quitarle el evento del click al 42 y al drake.
                }if (contadorActivos == 7) {
                JOptionPane.showMessageDialog(null, "NO HAY MAS ACTIVOS LANZA LOS DADOS DE NUEVO.");
                contadorActivos=0;
            }

            int[] caras = modelDados.getCaras();
            if (e.getSource() == dado1 && caras[0]== 1){
                JOptionPane.showMessageDialog(null, "Vuelve a lanzar uno de los dados activos.");
            } else  if (e.getSource() == dado2 && caras[1]== 1){
                JOptionPane.showMessageDialog(null, "Meeple");
            }else  if (e.getSource() == dado3 && caras[2]== 1){
                JOptionPane.showMessageDialog(null, "Meeple");
            } else if (e.getSource() == dado4 && caras[3]== 1){
                JOptionPane.showMessageDialog(null, "Meeple");
            }else  if (e.getSource() == dado5 && caras[4]== 1){
                JOptionPane.showMessageDialog(null, "Meeple");
            }else  if (e.getSource() == dado6 && caras[5]== 1){
                JOptionPane.showMessageDialog(null, "Meeple");
            }else  if (e.getSource() == dado7 && caras[6]== 1){
                JOptionPane.showMessageDialog(null, "Meeple");
            }





            //ACCION HERO
            if (e.getSource() == dado1 && caras[0]== 3){
                JOptionPane.showMessageDialog(null, "HERO");
            }else  if (e.getSource() == dado2 && caras[1]== 3){
                JOptionPane.showMessageDialog(null, "HERO");
            }else  if (e.getSource() == dado3 && caras[2]== 3){
                JOptionPane.showMessageDialog(null, "HERO");
            } else if (e.getSource() == dado4 && caras[3]== 3){
                JOptionPane.showMessageDialog(null, "HERO");
            }else  if (e.getSource() == dado5 && caras[4]== 3){
                JOptionPane.showMessageDialog(null, "HERO");
            }else  if (e.getSource() == dado6 && caras[5]== 3){
                JOptionPane.showMessageDialog(null, "HERO");
            }else  if (e.getSource() == dado7 && caras[6]== 3){
                JOptionPane.showMessageDialog(null, "HERO");
            }

            //ACCION COHETE
            if (e.getSource() == dado1 && caras[0]== 6){
                JOptionPane.showMessageDialog(null, "COHETE");
            }else  if (e.getSource() == dado2 && caras[1]== 6){
                JOptionPane.showMessageDialog(null, "COHETE");
            }else  if (e.getSource() == dado3 && caras[2]== 6){
                JOptionPane.showMessageDialog(null, "COHETE");
            } else if (e.getSource() == dado4 && caras[3]== 6){
                JOptionPane.showMessageDialog(null, "COHETE");
            }else  if (e.getSource() == dado5 && caras[4]== 6){
                JOptionPane.showMessageDialog(null, "COHETE");
            }else  if (e.getSource() == dado6 && caras[5]== 6){
                JOptionPane.showMessageDialog(null, "COHETE");
            }else  if (e.getSource() == dado7 && caras[6]== 6){
                JOptionPane.showMessageDialog(null, "COHETE");
            }

            //ACCION CORAZON
            if (e.getSource() == dado1 && caras[0]== 5){
                JOptionPane.showMessageDialog(null, "CORAZON");
            }else  if (e.getSource() == dado2 && caras[1]== 5){
                JOptionPane.showMessageDialog(null, "CORAZON");
            }else  if (e.getSource() == dado3 && caras[2]== 5){
                JOptionPane.showMessageDialog(null, "CORAZON");
            } else if (e.getSource() == dado4 && caras[3]== 5){
                JOptionPane.showMessageDialog(null, "CORAZON");
            }else  if (e.getSource() == dado5 && caras[4]== 5){
                JOptionPane.showMessageDialog(null, "CORAZON");
            }else  if (e.getSource() == dado6 && caras[5]== 5){
                JOptionPane.showMessageDialog(null, "CORAZON");
            }else  if (e.getSource() == dado7 && caras[6]== 5){
                JOptionPane.showMessageDialog(null, "CORAZON");
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

    private class Meeple implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

            //Para la cara 1 y 2
            int [] caras = modelDados.getCaras();
            caras[1] = dice.getCara();
            imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[1] + ".png"));
            dado2.setIcon(imageDados);
            dado2.removeMouseListener(this);
            dado2.addMouseListener(meeple);

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

