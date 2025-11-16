import sas.*;
import java.awt.Color;

public class GameManager
{

    Fenster fenster = new Fenster();
    
    static Player player = new Player();
    public Picture playerBody = player.player_body;


    public static void main(String[] args) {
        new GameManager();
    }
    
    public GameManager() {





        
        final int FPS = 60;
        final long frameTime = 1000 / FPS; // Time between each frame -> 16ms
        
        // Setup Environment

        playerBody.move(100,0); // Move the bird to the required position


        
        while(true){
        
            long start = System.currentTimeMillis();
            
            update();
            
            
            long elapsed = System.currentTimeMillis() - start;
            long sleepTime = frameTime - elapsed;
            
            if (sleepTime > 0){
                try {
                    
                    Thread.sleep(sleepTime);
                
                } catch (InterruptedException e) {
                    
                    e.printStackTrace();
                    
                }
            
            }
            
            
            
        
        }


    }

    private double tilt = 0; // keep track of current tilt
    private double idleanimationCount = 0; // keep track of current idleanimationcount
    boolean idleanimationSetting = false;
    boolean goingUp = true;


    private void update() {
        boolean left  = fenster.fenster.keyPressed('a');
        boolean right = fenster.fenster.keyPressed('d');
        boolean up    = fenster.fenster.keyPressed('w');
        boolean down  = fenster.fenster.keyPressed('s');

        // How tilt works -> If tilt is under the max limit, the sprite will rotate along its own axis to the correlated direction each loop. If it is above its limit, nothing will happen.

        // Move and tilt left
        if (left) {
            playerBody.move(-10, 0);
            if (tilt > -10) { // limit max tilt
                playerBody.turn(playerBody.getCenterX(), playerBody.getCenterY(), -1);
                tilt -= 1;
            }
        }

        // Move and tilt right
        if (right) {
            playerBody.move(10, 0);
            if (tilt < 10) { // limit max tilt
                playerBody.turn(playerBody.getCenterX(), playerBody.getCenterY(), 1);
                tilt += 1;
            }
        }

        // Move and no tilt up
        if (up) {

            playerBody.move(0, -10);

        }

        // Move and no tilt down
        if (down) {

            playerBody.move(0, 10);

        } // Do not use "else if", as it will only allow one key-press per update loop (03.11)


        // UNDO tilt. If left/right key is not being pressed and it higher than its lowest value, tilt is being lowered by 1 each loop.

        if (!left && !right) {
            if (tilt > 0) {
                playerBody.turn(playerBody.getCenterX(), playerBody.getCenterY(), -1);
                tilt -= 1;
            } else if (tilt < 0) {
                playerBody.turn(playerBody.getCenterX(), playerBody.getCenterY(), 1);
                tilt += 1;
            }
        }




        if (!left && !right && !up && !down) {

            if (idleanimationSetting) {

                if (goingUp) {
                    idleanimationCount += 0.2;
                    playerBody.move(0, -1);

                    if (idleanimationCount >= 6) { // reached the top
                        goingUp = false;
                    }

                } else {
                    idleanimationCount -= 0.2;
                    playerBody.move(0, 1);

                    if (idleanimationCount <= 0) { // reached the bottom
                        goingUp = true;
                    }
                }

            }

        }

    }
    
    
    
    
    
    
    

    
    
    
}