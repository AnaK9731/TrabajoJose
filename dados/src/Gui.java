import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
public class Gui extends JFrame {
    //atributos
    private int contadorDadosActivos=1, puntajeTotal=0;
    private JPanel score, inactiveDice, activeDice, usedDice, Botones, Decoracion;
    private JPanel containerImage, inactiveDiceLayout, usedDiceLayout, scoreLayout;
    private JPanel panelPrincipal;
    private  JTextArea textGame;
    private boolean primerClick = true;
    private int puntaje =0;
    private int estado = 0, ronda=0;
    //Label para mostrar una imagen con la ayuda en el JOPTION PANE
    private JLabel Guide;
    //imagenes para los dados
    private JLabel dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10, Deco;
    private JButton startRound, help, NextRound;
    private ModelDados modelDados;
    private JScrollPane scrollPane;
    private Escucha escucha;
    private ImageIcon imageDados;
    private Title title;
    private Dados dice = new Dados();
    private ImageIcon imagen;
    //crear la ventana
    public Gui() {
            initGUI();
            this.setTitle("Geek out masters");
            this.setSize(750, 500);
            this.setVisible(true);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void initGUI() {
        /**
         * se crea un panel con el diseno que queremos aplicar Osea gridBagLayout
         */
        panelPrincipal = new JPanel(new GridLayout(2, 3));
        score = new JPanel(new BorderLayout());
        inactiveDice = new JPanel(new BorderLayout());
        activeDice = new JPanel(new BorderLayout());
        usedDice = new JPanel(new BorderLayout());
        Botones = new JPanel(new BorderLayout());
        Decoracion= new JPanel(new BorderLayout());

        /**
         * Paneles que van dentro de los paneles, para poder meter dentro las imagenes o txt area.
         */
        containerImage = new JPanel();
        inactiveDiceLayout = new JPanel();
        usedDiceLayout = new JPanel();
        scoreLayout = new JPanel();
        Botones= new JPanel();
        Decoracion= new JPanel();
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
        textGame= new JTextArea();
        scrollPane = new JScrollPane(textGame, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Create listener object and control object
        escucha = new Escucha();
        modelDados = new ModelDados();

        //Titulo para la ventana mostrando el nombre de geekoutMasters
        //panelPrincipal.add(title=new Title("Geek Out Masters", Color.GRAY));
        //Ponemos bordes en cada panel xd
        Border border = BorderFactory.createLineBorder(Color.red);
        Border border2 = BorderFactory.createLineBorder(Color.blue);
        inactiveDice.setBorder(border);
        activeDice.setBorder(border);
        usedDice.setBorder(border);
        Botones.setBorder(border2);
        score.setBorder(border2);
        Decoracion.setBorder(border2);
        /**
         * se anaden los paneles al panel con el diseno de cuatro partes
         */
        /**
         * Panel de dados activos.
         */
        panelPrincipal.add(activeDice);
        activeDice.setBackground(Color.black);
        activeDice.add(title = new Title("Dados activos", Color.BLACK));
        title.setForeground(Color.white);
        Font font1 = new Font("Arial", Font.BOLD, 24);
        title.setFont(font1);
        activeDice.add(title, BorderLayout.NORTH);
        activeDice.add(containerImage, BorderLayout.CENTER);




        //Panel dados inactivos

        panelPrincipal.add(inactiveDice);
        inactiveDice.setBackground(Color.white);
        inactiveDice.add(title = new Title("Dados Inactivos", Color.black));
        inactiveDice.add(title, BorderLayout.NORTH);
        title.setForeground(Color.white);
        Font font2 = new Font("Arial", Font.BOLD, 24);
        title.setFont(font2);
        inactiveDice.add(inactiveDiceLayout, BorderLayout.CENTER);


        //Panel dados usados.

        panelPrincipal.add(usedDice);
        usedDice.setBackground(Color.white);
        usedDice.add(title = new Title("Dados usados", Color.black));
        usedDice.add(title, BorderLayout.NORTH);
        title.setForeground(Color.white);
        Font font3 = new Font("Arial", Font.BOLD, 24);
        title.setFont(font3);
        usedDice.add(usedDiceLayout, BorderLayout.CENTER);

        /**
         * Panel de score
         */
        panelPrincipal.add(score); // Agregar el panel de puntaje al panel principal
        score.setBackground(Color.white);
        //scoreGame.setEditable(false);
        // Creación y configuración del panel de puntaje
        score.add(title = new Title("Puntaje", Color.black));
        score.add(title, BorderLayout.NORTH);
        title.setForeground(Color.white);
        Font font4 = new Font("Arial", Font.BOLD, 24);
        title.setFont(font4);
        textGame.setEditable(false);
        score.add(scrollPane, BorderLayout.CENTER);
        //score.add(scoreGame, BorderLayout.CENTER);
        //score.add(textoPuntajeTotal, BorderLayout.CENTER);

        /**
         * Panel de Botones
         */
        panelPrincipal.add(Botones);
        Botones.setLayout(new FlowLayout(FlowLayout.CENTER)); //Establecemos un FlowLayout centrado
        Botones.setBackground(Color.white);

        //boton guia
        help = new JButton("Guia");
        help.addActionListener(escucha);
        help.setBackground(Color.red);
        help.setPreferredSize(new Dimension(120, 30)); //Establecemos un tamaño preferido pequeño
        Botones.add(help);


        //boton inicio de juego
        startRound = new JButton("Gira los dados");
        startRound.addActionListener(escucha);
        startRound.setBackground(Color.green);
        startRound.setPreferredSize(new Dimension(120, 30)); //Establecemos un tamaño preferido pequeño
        Botones.add(startRound);

        //boton Siguiente Ronda
        NextRound = new JButton("Siguente Ronda");
        NextRound.setBackground(Color.orange);
        NextRound.addActionListener(escucha);
        NextRound.setPreferredSize(new Dimension(150, 30)); //Establecemos un tamaño preferido pequeño
        Botones.add(NextRound);

        //PANEL DECORATIVO
        panelPrincipal.add(Decoracion);
        imagen = new ImageIcon(getClass().getResource("/recursos/Deco.png"));
        Deco = new JLabel();
        Deco.setIcon(imagen);
        Decoracion.add(Deco, BorderLayout.CENTER );
        Decoracion.setBackground(Color.white);

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
     * Intento de puntaje.
     */
    private void gameScore(){
        JLabel arrayLabel[] = {dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10};
        int[] caras = modelDados.getCaras();
        for (int i = 0; i < arrayLabel.length; i++) {
            // Verificar si quedaron dados con la cara de 42 activos
            if (caras[i]==2 && activeDice.isAncestorOf(arrayLabel[i] ) && contadorDadosActivos==0) {
                puntaje++;
            } else if (caras[i]==4 && activeDice.isAncestorOf(arrayLabel[i] ) && contadorDadosActivos==0) {
                puntaje=0;
                textGame.append("\nPERDIO TODOS LOS PUNTOS ACUMULADOS!.\n Puntaje de ronda= "+ puntaje);
            }
        }
        if (contadorDadosActivos == 0 && primerClick==true && puntaje==1){
            textGame.append("\nSu puntaje fue de 1");
        } else if (contadorDadosActivos == 0 && primerClick==true && puntaje==2) {
            textGame.append("\nSu puntaje fue de 3");
        }else if (contadorDadosActivos == 0 && primerClick==true && puntaje==3) {
            textGame.append("\nSu puntaje fue de 6");
        }else if (contadorDadosActivos == 0 && primerClick==true && puntaje==4) {
            textGame.append("\nSu puntaje fue de 10");
        }else if (contadorDadosActivos == 0 && primerClick==true && puntaje==5) {
            textGame.append("\nSu puntaje fue de 15");
        }else if (contadorDadosActivos == 0 && primerClick==true && puntaje==6) {
            textGame.append("\nSu puntaje fue de 21");
        }else if (contadorDadosActivos == 0 && primerClick==true && puntaje==7) {
            textGame.append("\nSu puntaje fue de 28");
        }else if (contadorDadosActivos == 0 && primerClick==true && puntaje==8) {
            textGame.append("\nSu puntaje fue de 36");
        }else if (contadorDadosActivos == 0 && primerClick==true && puntaje==9) {
            textGame.append("\nSu puntaje fue de 45");
        }else if (contadorDadosActivos == 0 && primerClick==true && puntaje==10) {
            textGame.append("\nSu puntaje fue de 55");
        }
    }
    private void reiniciarRonda() {
        // Restablecer los valores predeterminados de la ronda
        estado = 0;
        primerClick = true;

        // Restablecer la visibilidad de los componentes y limpiar los paneles
        containerImage.removeAll();
        inactiveDiceLayout.removeAll();
        usedDiceLayout.removeAll();
        scoreLayout.removeAll();
        setComponentsEnabled(containerImage, true);
        setComponentsEnabled(inactiveDiceLayout, false);

        // Añadir los componentes por defecto
        containerImage.add(dado1);
        containerImage.add(dado2);
        containerImage.add(dado3);
        containerImage.add(dado4);
        containerImage.add(dado5);
        containerImage.add(dado6);
        containerImage.add(dado7);
        /**
         * Escuchas para añadir a panel de dados activos
         */
        dado1.removeMouseListener(escucha);
        dado2.removeMouseListener(escucha);
        dado3.removeMouseListener(escucha);
        dado4.removeMouseListener(escucha);
        dado5.removeMouseListener(escucha);
        dado6.removeMouseListener(escucha);
        dado7.removeMouseListener(escucha);

        // Actualizar los paneles
        containerImage.revalidate();
        containerImage.repaint();
        inactiveDiceLayout.revalidate();
        inactiveDiceLayout.repaint();
        usedDiceLayout.revalidate();
        usedDiceLayout.repaint();
        contadorDadosActivos=1;
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
    }

    /**
     * Cuenta la cantidad de dados activos
     * Se itera en un for y usando el metodo isAncestorOf, verifica solo los componentes que pertenecen al panel de active dice y solo verifica a las labels que pertenecen a el
     * Si son distintas de 4 y 2, se cumple el condicional y entra y lo suma a dados activos.
     * Luego guardo en una variable lo que dio en el for, si en el esta variable dio 0 y ademas el estado de primerClick es true se ejecuta el JoptionPane
     * Por que? Porque si la variable primer click es false, me esta diciendo que aun hay un evento por jugarse. Sumamos a cantidad de rondas.
     * Aca añadimos un condicional al habilitar siguiente ronda, inicia en 1 siempre para que cuando se giren los dados el boton next round no pueda ser clickeado. Si el valor de dados activos es 0 si podra ser clickeado
     */
    public void contarDadosActivos() {
        JLabel arrayLabel[] = {dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10};
        int[] caras = modelDados.getCaras();
        int cantidadDadosActivos=0;

        for (int i = 0; i < arrayLabel.length; i++) {
            // Verificar si la cara del dado no es 4 ni 2
            if (caras[i] != 4 && caras[i] != 2 && activeDice.isAncestorOf(arrayLabel[i])) {
                cantidadDadosActivos++;
                System.out.println(cantidadDadosActivos);
            }
        }
        contadorDadosActivos = cantidadDadosActivos;
        if (contadorDadosActivos == 0 && primerClick==true) {
            JOptionPane.showMessageDialog(null, "Reinicie la ronda y gire los dados");
            NextRound.setEnabled(true); // Habilitar el botón "NextRound"
            ronda++;
            gameScore();
            cantidadRondas();
        }
    }
    private void cantidadRondas(){
        if (ronda==5 && puntaje<8){
            JOptionPane.showMessageDialog(null, "Lo siento Usted perdio!!!");
        } else if (ronda == 5 && puntaje >= 8) {
            JOptionPane.showMessageDialog(null, "Usted Gano! ");
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
        @Override
        public void actionPerformed(ActionEvent e) {
            //Mostramos la cantidad de rondas
            if (e.getSource()==startRound){
                textGame.append("\nRonda #"+ronda+"\n");
            }
            //Reiniciamos la ronda y activamos los botones.
            if (e.getSource() == NextRound && contadorDadosActivos==0) {
                startRound.setEnabled(true);
                NextRound.setEnabled(false); //  Deshabilitar el botón "NextRound"
                reiniciarRonda();
            }
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
                NextRound.setEnabled(false); //  Deshabilitar el botón "NextRound"
                startRound.setEnabled(false);//  Deshabilitar el botón "GirarDados"
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
                        setComponentsEnabled(activeDice, true);
                    }
                }
                contarDadosActivos();
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
                            contarDadosActivos();
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
                            contarDadosActivos();
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
                            contarDadosActivos();
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
                            contarDadosActivos();
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
                           // arrayLabel[i].addMouseListener(this);
                            containerImage.revalidate();
                            containerImage.repaint();
                            estado=0;
                            primerClick = true;
                            contarDadosActivos();
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
                            contarDadosActivos();
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
                            contarDadosActivos();
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
                            contarDadosActivos();
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

