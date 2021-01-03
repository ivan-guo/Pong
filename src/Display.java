import java.awt.Color;
import java.awt.Graphics;

public class Display {

    private Handler handler;

    private int points1 = 0;
    private int points2 = 0;

    public Display(Handler handler) {
        this.handler = handler;
    }

    public void tick() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);

            if (object.getId() == ID.Ball) {
                if (object.getX() < 0) {
                    points1 += 1;
                }

                if (object.getX() > Game.WIDTH) {
                    points2 += 1;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(Game.WIDTH / 2 - 120, 20, 200, 32);

        g.setColor(Color.green);
        g.drawString("P1 : " + points1, Game.WIDTH / 2 - 90, 40);
        g.drawString("P2 : " + points2, Game.WIDTH / 2 + 15, 40);

    }
}
