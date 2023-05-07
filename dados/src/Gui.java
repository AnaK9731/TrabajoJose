import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class Gui extends JFrame {
    //atributos
    private JPanel score, inactiveDice, activeDice, usedDice;
    private JPanel containerImage, inactiveDiceLayout, usedDiceLayout, scoreLayout;
    private JPanel panelPrincipal;
    private boolean primerClick = true;
    private int estado = 0;
    private int contadorDados = 0;
    //Label para mostrar una imagen con la ayuda en el JOPTION PANE
    private JLabel Guide;
    private int contadorActivos = 0;
    //imagenes para los dados
    private JLabel dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
    private JButton startRound, help;
    private ModelDados modelDados;
    private Escucha escucha;
    private ImageIcon imageDados;
    private Title title;
    private Dados dice = new Dados();
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
        /**
         * se crea un panel con el diseno que queremos aplicar Osea gridBagLayout
          */
        panelPrincipal = new JPanel(new GridLayout(2, 2));
        score = new JPanel(new BorderLayout());
        inactiveDice = new JPanel(new BorderLayout());
        activeDice = new JPanel(new BorderLayout());
        usedDice = new JPanel(new BorderLayout());
        /**
         * Paneles que van dentro de los paneles, para poder meter dentro las imagenes o txt area.
         */
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
        escucha = new Escucha();
        modelDados = new ModelDados();
        //Titulo para la ventana mostrando el nombre de geekoutMasters
        //panelPrincipal.add(title=new Title("Geek Out Masters", Color.GRAY));
        //Ponemos bordes en cada panel xd
        Border border = BorderFactory.createLineBorder(Color.black);
        score.setBorder(border);
        inactiveDice.setBorder(border);
        activeDice.setBorder(border);
        usedDice.setBorder(border);
        /**
         * se anaden los paneles al panel con el diseno de cuatro partes
         */
        /**
         * Panel de dados activos.
         */
        panelPrincipal.add(activeDice);
        activeDice.add(title = new Title("Dados activos", Color.GRAY));
        activeDice.add(title, BorderLayout.NORTH);
        activeDice.add(containerImage, BorderLayout.CENTER);
        startRound = new JButton("Gira los dados");
        startRound.addActionListener(escucha);
        activeDice.add(startRound, BorderLayout.SOUTH);
        /**
         * Escuchas para añadir a panel de dados activos
         */
        dado1.addMouseListener(escucha);
        dado2.addMouseListener(escucha);
        dado3.addMouseListener(escucha);
        dado4.addMouseListener(escucha);
        dado5.addMouseListener(escucha);
        dado6.addMouseListener(escucha);
        dado7.addMouseListener(escucha);
        /**
         * Panel de score
         */
        panelPrincipal.add(score);
        score.add(title = new Title("Puntaje", Color.black));
        score.add(title, BorderLayout.NORTH);
        help = new JButton("Guia");
        help.addActionListener(escucha);
        score.add(help, BorderLayout.PAGE_END);
        //Panel dados inactivos
        panelPrincipal.add(inactiveDice);
        inactiveDice.add(title = new Title("Dados Inactivos", Color.GRAY));
        inactiveDice.add(title, BorderLayout.NORTH);
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
     * Metodo que nos deja visualizar o no un componente de un conteneder.
     * @param container
     * @param enabled
     */
    private void setComponentsEnabled(Container container, boolean enabled) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof Container) {
                // Si el componente es un contenedor, se llama recursivamente al método para habilitar o deshabilitar sus componentes internos
                setComponentsEnabled((Container) component, enabled);
            }
            component.setEnabled(enabled); // Habilitar o deshabilitar el componente
        }
    }
    /**
     * Metodo que me deja el panel de dados usados despues de empezar una ronda.
     */
    public void empezarRonda() {
        containerImage.revalidate();
        containerImage.repaint();
        inactiveDiceLayout.revalidate();
        inactiveDiceLayout.repaint();
        usedDiceLayout.revalidate();
        usedDiceLayout.repaint();
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
        @Override
        public void actionPerformed(ActionEvent e) {
            /**
             * Añadimos la opcion de visualizar una ayuda en la GUI. Lo que hacen los dados y las puntuaciones.
             */
            if (e.getSource() == help) {
                imageDados = new ImageIcon(getClass().getResource("/recursos/ayuda.png"));
                Guide = new JLabel(imageDados);
                JOptionPane.showMessageDialog(null, Guide, "Guia de Juego", JOptionPane.PLAIN_MESSAGE);
            }
            /**
             * Iniciar los valores de los dados al empezar ronda.
             */
            if (e.getSource() == startRound ) {
                JLabel arrayLabels[] = {dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10};
                int[] caras = modelDados.getCaras();
                modelDados.obtenerValorCaras();
                for (int i = 0; i < arrayLabels.length; i++) {
                    if (i >= 7) {
                        imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[i] + ".png"));
                        arrayLabels[i].setIcon(imageDados);
                        inactiveDiceLayout.add(arrayLabels[i]);
                        setComponentsEnabled(inactiveDiceLayout, false);
                    } else {
                        imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[i] + ".png"));
                        arrayLabels[i].setIcon(imageDados);
                        containerImage.add(arrayLabels[i]);
                    }
                }
                empezarRonda();
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            //Creacion de Jlabels en un array para iterarlas de forma sencilla.
            JLabel arrayLabel[] = {dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10};
            int[] caras = modelDados.getCaras();
            /**
             * Acciones dados. PRIMER CLICK
             */
            for (int i = 0; i < arrayLabel.length; i++) {
                if (e.getSource() == arrayLabel[i]) {
                    if (primerClick) {
                        /**
                         * Accion MEEPLE
                         */
                        if (caras[i] == 1) {
                            JOptionPane.showMessageDialog(null, "Primer clic en MEEPLE");
                            arrayLabel[i].removeMouseListener(this);
                            containerImage.remove(arrayLabel[i]);
                            usedDiceLayout.add(arrayLabel[i]);
                            containerImage.revalidate();
                            containerImage.repaint();
                            estado= 1;
                            primerClick = false;
                        }
                        /**
                         * Accion COHETE
                         */
                        if (caras[i] == 6) {
                            JOptionPane.showMessageDialog(null, "Primer clic en Cohete");
                            arrayLabel[i].removeMouseListener(this);
                            containerImage.remove(arrayLabel[i]);
                            containerImage.revalidate();
                            containerImage.repaint();
                            usedDiceLayout.add(arrayLabel[i]);
                            estado= 6;
                            primerClick = false;
                        }
                        /**
                         * Accion CORAZON
                         */
                        if (caras[i] == 5) {
                            JOptionPane.showMessageDialog(null, "Primer clic en corazon");
                            arrayLabel[i].removeMouseListener(this);
                            dado8.addMouseListener(escucha);
                            dado9.addMouseListener(escucha);
                            dado10.addMouseListener(escucha);
                            containerImage.remove(arrayLabel[i]);
                            usedDiceLayout.add(arrayLabel[i]);
                            containerImage.revalidate();
                            containerImage.repaint();
                            setComponentsEnabled(inactiveDiceLayout, true);
                            setComponentsEnabled(containerImage, false);
                            estado= 5;
                            primerClick = false;
                        }
                        /**
                         * Accion HERO
                         */
                        if (caras[i] == 3) {
                            JOptionPane.showMessageDialog(null, "Primer clic en Hero");
                            arrayLabel[i].removeMouseListener(this);
                            containerImage.remove(arrayLabel[i]);
                            usedDiceLayout.add(arrayLabel[i]);
                            containerImage.revalidate();
                            containerImage.repaint();
                            estado= 3;
                            primerClick = false;
                        }
                    } else {
                        /**
                         * Acciones dados. SEGUNDO CLICK
                         * Accion MEEPLE
                         */
                        if (e.getSource() == arrayLabel[i] && estado == 1) {
                            JOptionPane.showMessageDialog(null, "Segundo clic aplicar efecto meeple");
                            caras[i]=dice.getCara();
                            imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[i] + ".png"));
                            arrayLabel[i].setIcon(imageDados);
                            arrayLabel[i].addMouseListener(this);
                            containerImage.revalidate();
                            containerImage.repaint();
                            estado=0;
                            primerClick = true;
                        }
                        /**
                         * Accion COHETE
                         */
                        if (e.getSource() == arrayLabel[i] && estado == 6) {
                            JOptionPane.showMessageDialog(null, "Segundo clic aplicar efecto cohete");
                            //arrayLabel[i].removeMouseListener(this);
                            containerImage.remove(arrayLabel[i]);
                            containerImage.repaint();
                            inactiveDiceLayout.add(arrayLabel[i]);
                            inactiveDiceLayout.revalidate();
                            inactiveDiceLayout.repaint();
                            setComponentsEnabled(inactiveDiceLayout, false);
                            estado=0;
                            primerClick = true;
                        }
                        /**
                         * Accion CORAZON
                         */
                        if (e.getSource() == arrayLabel[i] && estado == 5) {
                            JOptionPane.showMessageDialog(null, "Segundo clic aplicar efecto corazon");
                            arrayLabel[i].addMouseListener(this);
                            caras[i] = dice.getCara();
                            imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[i] + ".png"));
                            arrayLabel[i].setIcon(imageDados);
                            inactiveDiceLayout.remove(arrayLabel[i]);
                            inactiveDiceLayout.revalidate();
                            inactiveDiceLayout.repaint();
                            containerImage.add(arrayLabel[i]);
                            containerImage.revalidate();
                            setComponentsEnabled(containerImage, true);
                            setComponentsEnabled(inactiveDiceLayout, false);
                            dado8.removeMouseListener(this);
                            dado9.removeMouseListener(this);
                            dado10.removeMouseListener(this);
                            estado = 0;
                            primerClick = true;
                        }
                        /**
                         * Accion HERO
                         */
                        if (e.getSource() == arrayLabel[i] && estado == 3) {
                            JOptionPane.showMessageDialog(null, "Segundo clic aplicar efecto hero");
                            int caraOpuesta=7;
                            int resta= caraOpuesta-caras[i];
                            caras[i]= resta;
                            imageDados = new ImageIcon(getClass().getResource("/recursos/" + caras[i] + ".png"));
                            arrayLabel[i].setIcon(imageDados);
                            containerImage.revalidate();
                            containerImage.repaint();
                            estado=0;
                            primerClick = true;
                        }
                    }
                    break;
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

