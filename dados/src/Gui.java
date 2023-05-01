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

    //Label para mostrar una imagen con la ayuda en el JOPTION PANE
    private JLabel Guide;

    //imagenes para los dados
    private JLabel dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
    private JButton startRound, help;
    private ModelDados modelDados;
    private Escucha escucha;
    private ImageIcon imageDados;
    private  Title title;


    //crear la ventana

    public Gui() {
        initGUI();
        this.setTitle("Geek out masters");
        this.setSize(400, 400);
        this.setVisible(true);
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
        inactiveDiceLayout.add(dado8);
        inactiveDiceLayout.add(dado9);
        inactiveDiceLayout.add(dado10);

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
            if (e.getSource()== startRound) {
                modelDados.obtenerValorCaras();
                modelDados.asignarPoder();
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
                empezarRonda();
            }
        }


        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getComponent() instanceof JLabel) {
                if (e.getSource() == dado1) {
                    activeDice.remove(dado1);
                    usedDiceLayout.add(dado1);
                    activeDice.revalidate();
                    activeDice.repaint();
                }else if (e.getSource() == dado2) {
                    activeDice.remove(dado2);
                    usedDiceLayout.add(dado2);
                    activeDice.revalidate();
                    activeDice.repaint();
                }else if (e.getSource() == dado3) {
                    activeDice.remove(dado3);
                    usedDiceLayout.add(dado3);
                    activeDice.revalidate();
                    activeDice.repaint();
                }else if (e.getSource() == dado4) {
                    activeDice.remove(dado4);
                    usedDiceLayout.add(dado4);
                    activeDice.revalidate();
                    activeDice.repaint();
                }else if (e.getSource() == dado5) {
                    activeDice.remove(dado5);
                    usedDiceLayout.add(dado5);
                    activeDice.revalidate();
                    activeDice.repaint();
                }else if (e.getSource() == dado6) {
                    activeDice.remove(dado6);
                    usedDiceLayout.add(dado6);
                    activeDice.revalidate();
                    activeDice.repaint();
                }else if (e.getSource() == dado7) {
                    activeDice.remove(dado7);
                    usedDiceLayout.add(dado7);
                    activeDice.revalidate();
                    activeDice.repaint();
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

