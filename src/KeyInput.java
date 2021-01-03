import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyPressed = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);

            if (object.getId() == ID.Player1) {
                if (key == KeyEvent.VK_W) {
                    object.setVelY(-15);
                    keyPressed[0] = true;
                }
                if (key == KeyEvent.VK_S) {
                    object.setVelY(15);
                    keyPressed[1] = true;
                }
            } else if (object.getId() == ID.Player2) {
                if (key == KeyEvent.VK_UP) {
                    object.setVelY(-15);
                    keyPressed[2] = true;
                }
                if (key == KeyEvent.VK_DOWN) {
                    object.setVelY(15);
                    keyPressed[3] = true;
                }
            }
        }
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();

        for (GameObject object : handler.objects) {
            if (object.getId() == ID.Player1) {
                if (key == KeyEvent.VK_W) {
                    keyPressed[0] = false;
                }
                if (key == KeyEvent.VK_S) {
                    keyPressed[1] = false;
                }

                if (!keyPressed[0] && !keyPressed[1]) object.setVelY(0);

            } else if (object.getId() == ID.Player2) {
                if (key == KeyEvent.VK_UP) {
                    keyPressed[2] = false;
                }
                if (key == KeyEvent.VK_DOWN) {
                    keyPressed[3] = false;
                }

                if (!keyPressed[2] && !keyPressed[3]) object.setVelY(0);
            }
        }
    }
}

