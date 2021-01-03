import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 4352559160725225636L;

    static final int WIDTH = 1400, HEIGHT = WIDTH / 12 * 9;

    private Handler handler;
    private Thread thread;
    private boolean running = false;
    private Display hud;

    static boolean win1 = false;
    static boolean win2 = false;

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Pong", this);

        hud = new Display(handler);

        handler.addObject(new Player(WIDTH / 2 - 600, HEIGHT / 2 - 80, ID.Player1, handler));
        handler.addObject(new Player(WIDTH / 2 + 600, HEIGHT / 2 - 80, ID.Player2, handler));
        handler.addObject(new Ball(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Ball, handler));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tick() {
        handler.tick();
        hud.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.white);
        for (int y = 0; y < HEIGHT; y += 45) {
            g.fillRect(Game.WIDTH / 2 - 30, y, 20, 20);
        }

        handler.render(g);
        hud.render(g);

        if (win1) {
            g.setColor(Color.green);
            g.setFont(new Font("Helvetia", Font.BOLD, 150));
            g.drawString(" Player 1 wins!", WIDTH / 2 - 580, HEIGHT / 2 + 10);
        } else if (win2) {
            g.setColor(Color.green);
            g.setFont(new Font("Helvetia", Font.BOLD, 150));
            g.drawString(" Player 2 wins!", WIDTH / 2 - 580, HEIGHT / 2 + 10);
        }

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running) render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public static float bound(float num, float min, float max) {
        if (num < min) return min;
        else if (num > max) return max;
        else return num;
    }

    public static void main(String[] args) {
        new Game();
    }
}
