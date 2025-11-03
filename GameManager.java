import sas.*;
import java.awt.Color;

public class GameManager
{

    Fenster fenster = new Fenster();
    
    static Spieler spieler = new Spieler();
    public Picture spielerBody = spieler.spieler_body;


    public static void main(String[] args) {
        new GameManager();
    }
    
    public GameManager() {





        
        final int FPS = 60;
        final long frameTime = 1000 / FPS; // Time between each frame -> 16ms
        
        // Setup Environment

        spielerBody.move(100,0); // Move the bird to the required position


        
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
    
    private void update() {

        spielerBody.turn(spielerBody.getCenterX(),spielerBody.getCenterY(),0.1);


        if (fenster.fenster.keyPressed('a')) {

            spielerBody.move(-10, 0);

        } if (fenster.fenster.keyPressed('d')) {

            spielerBody.move(10, 0);

        } if (fenster.fenster.keyPressed('w')) {

            spielerBody.move(0, -10);

        } if (fenster.fenster.keyPressed('s')) {

            spielerBody.move(0, 10);

        } // Do not use "else if", as it will only allow one key-press per update loop (03.11)

    }
    
    
    
    
    
    
    

    
    
    
}