import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        y += velY;

        y = Game.bound(y, 0, Game.HEIGHT - 240);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, 20, 200);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 20, 250);
    }
}
