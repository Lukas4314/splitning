import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class App extends PApplet {

    ArrayList<PVector> particlesPos = new ArrayList<PVector>();
    ArrayList<PVector> particlesVel = new ArrayList<PVector>();

    int size = 50;
    int startValue = 200;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(1600, 1000);
    }

    public void setup() {
        for (int i = 0; i < startValue; i++) {
            particlesPos.add(new PVector(random(width), random(height)));
            particlesVel.add(new PVector(random(10) - 5, random(10) - 5));
        }
    }

    public void draw() {
        background(0);
        fill(255, 0, 0);

        for (int i = 0; i < particlesVel.size(); i++) {
            if (particlesPos.get(i).x >= width || particlesPos.get(i).x <= 0) {
                particlesVel.get(i).x = -particlesVel.get(i).x;
            }
            if (particlesPos.get(i).y >= height || particlesPos.get(i).y <= 0) {
                particlesVel.get(i).y = -particlesVel.get(i).y;
            }
        }

        for (int i = 0; i < particlesVel.size(); i++) {
            particlesPos.get(i).x = particlesPos.get(i).x + particlesVel.get(i).x;
            particlesPos.get(i).y = particlesPos.get(i).y + particlesVel.get(i).y;
        }
        for (PVector dot : particlesPos) {
            circle(dot.x, dot.y, size);
        }
    }
}
