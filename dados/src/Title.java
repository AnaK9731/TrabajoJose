import javax.swing.*;
import java.awt.*;

public class Title extends JLabel {
    public Title (String title, Color backgroundColor){
        setText(title);
        setBackground(backgroundColor);
        setForeground(Color.BLUE); //Color
        setFont(new Font ("Calibre", Font.BOLD+ Font.ITALIC,24));
        setHorizontalAlignment(JLabel.CENTER);
        setOpaque(true);
    }

}
