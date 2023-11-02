import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class App extends PApplet {

    ArrayList<PVector> particlesPos = new ArrayList<PVector>();
    ArrayList<PVector> particlesVel = new ArrayList<PVector>();

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        for (int i = 0; i < 1; i++) {
            particlesPos.add(new PVector(random(width), random(height)));
        }
    }

    public void draw() {
        background(0);
        fill(255, 0, 0);
        for (PVector dot : particlesPos) {
            circle(dot.x, dot.y, 10);
        }
    }
}
