import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Window extends Canvas {

    private static final long serialVersionUID = 9034494958129720942L;

    public Window(int width, int height, String title, Game game) {
        JFrame window = new JFrame(title);

        window.setPreferredSize(new Dimension(width, height));
        window.setMaximumSize(new Dimension(width, height));
        window.setMinimumSize(new Dimension(width, height));

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.add(game);
        window.setVisible(true);
        game.start();
    }
}
