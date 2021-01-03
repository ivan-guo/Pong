import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Display {

    private Handler handler;

    private int points1 = 0;
    private int points2 = 0;
    private int fontsize = 150;

    public Display(Handler handler) {
        this.handler = handler;
    }

    public void tick() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);

            if (object.getId() == ID.Ball) {
                if (object.getX() < 0) {
                    points2 += 1;
                }

                if (object.getX() > Game.WIDTH) {
                    points1 += 1;
                }
            }
        }

        if (points1 >= 11) {
            handler.clearDisplay();
            Game.win1 = true;
        } else if (points2 >= 11) {
            handler.clearDisplay();
            Game.win2 = true;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Helvetia", Font.BOLD, fontsize));
        g.drawString("" + points1, Game.WIDTH / 2 - 200, 125);
        g.drawString(""+ points2, Game.WIDTH / 2 + 76, 125);
    }
}
