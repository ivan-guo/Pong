import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends GameObject{

    Handler handler;
    private String sideStart = "right";
    Random r = new Random();

    public Ball(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 7; velY = r.nextInt(10);
    }

    @Override
    public void tick() {

        if (x < 0 || x > Game.WIDTH) {
            x = (float) Game.WIDTH / 2 - 32;
            y = (float) Game.HEIGHT / 2 - 32;

            if (sideStart.equals("right")) {
                sideStart = "left";
                velX = -7;
                velY = r.nextInt(40) - 20;
            } else if (sideStart.equals("left")) {
                sideStart = "right";
                velX = 7;
                velY = r.nextInt(40) - 20;
            }
        }

        x += velX;
        y += velY;

        if (velX > 0) {
            velX += 0.1;
        } else if (velX < 0) {
            velY += 0.1;
        }

        if (y <= 0 || y >= Game.HEIGHT - 70) velY *= -1;

        checkCollision();
        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.cyan, 32, 32, handler, 0.1f));
    }

    public void checkCollision() {
        for (GameObject object : handler.objects) {
            if (object.getId() == ID.Player1 || object.getId() == ID.Player2) {
                if (getBounds().intersects(object.getBounds())) {
                    velX*= -1;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillOval((int) x, (int) y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
