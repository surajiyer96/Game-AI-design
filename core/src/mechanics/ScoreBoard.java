package mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.concurrent.TimeUnit;
import static mechanics.GlobalState.flagsManager;

/**
 *
 * @author Kevin van Eenige and Daniël van der Laan
 */
public class ScoreBoard {
    private int playerScore;
    private int computerScore;
    private final int scoreTime;
    boolean toScore;
    long startTime;
    long elapsedTime;
    private final Texture hudScore;
    
    public ScoreBoard() {
        playerScore = 0;
        computerScore = 0;
        scoreTime = 3;
        toScore = true;
        startTime = System.currentTimeMillis();
        elapsedTime = 0;
        hudScore = new Texture(Gdx.files.internal("markers/hudScore.png"));
    }
    
    public void draw(SpriteBatch spriteBatch, Camera camera, BitmapFont font) {
        font.setColor(0,0,0,1);
        spriteBatch.draw(hudScore, camera.viewportWidth/2 - 250, camera.viewportHeight - 70);
        font.draw(spriteBatch, "" + getPS(), camera.viewportWidth/2 - 90, camera.viewportHeight - 20);
        font.draw(spriteBatch, "" + getCS(), camera.viewportWidth/2 + 80, camera.viewportHeight - 20);
        font.draw(spriteBatch, "" + String.valueOf(elapsedTime), camera.viewportWidth/2 -3, 
                camera.viewportHeight - 20);
    }
    
    public int getPS() {
        return playerScore;
    }
    
    public int getCS() {
        return computerScore;
    }
    
    public void setPS(int score) {
        playerScore = score;
    } 
    
    public void setCS(int score) {
        computerScore = score;
    }
    
    public void addPS(int score) {
        playerScore += score;
    } 
    
    public void addCS(int score) {
        computerScore += score;
    }    
    
    public void updateScore() {
        elapsedTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime);
        if(elapsedTime % scoreTime == 0 && toScore && elapsedTime != 0) {
            for(int i = 0; i < flagsManager.getFlagsList().size; i++) {
                switch(flagsManager.getOccupant(i)) {
                    case PLAYER:
                        addPS(flagsManager.getFlagWeight(i));
                        break;
                    case AI:
                        addCS(flagsManager.getFlagWeight(i));
                        break;
                }
            }
            toScore = false;
        } else if (elapsedTime % scoreTime != 0) {
            toScore = true;
        }
    }
}
