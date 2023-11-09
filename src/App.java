import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;

public class App extends PApplet {

    ArrayList<PVector> particlesPos = new ArrayList<PVector>();
    ArrayList<PVector> particlesVel = new ArrayList<PVector>();
    PVector buffer;
    int size = 50;
    int startValue = 3;
    float afstand;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(600, 600);
    }

    public void setup() {
        for (int i = 0; i < startValue; i++) {
            particlesPos.add(new PVector(random(size / 2, width - size / 2), random(size / 2, height - size / 2)));
            particlesVel.add(new PVector(random(10) - 5, random(10) - 5));
        }
        buffer = new PVector(0, 0);
    }

    public void draw() {
        background(0);
        fill(255, 0, 0);

        for (int i = 0; i < particlesVel.size(); i++) {
            if (particlesPos.get(i).x >= width - size / 2) {
                particlesVel.get(i).x = -particlesVel.get(i).x;
                particlesPos.get(i).x = width - size / 2;
            }
            if (particlesPos.get(i).x <= size / 2) {
                particlesVel.get(i).x = -particlesVel.get(i).x;
                particlesPos.get(i).x = size / 2;
            }

            if (particlesPos.get(i).y >= height - size / 2) {
                particlesVel.get(i).y = -particlesVel.get(i).y;
                particlesPos.get(i).y = height - size / 2;
            }
            if (particlesPos.get(i).y <= size / 2) {
                particlesVel.get(i).y = -particlesVel.get(i).y;
                particlesPos.get(i).y = size / 2;
            }
        }

        for (int i = particlesVel.size() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                afstand = particlesPos.get(i).dist(particlesPos.get(j));
                if (afstand <= size) {
                    buffer = particlesVel.get(i).copy();
                    particlesVel.get(i).set(particlesVel.get(j));
                    particlesVel.get(j).set(buffer);

                    // particlesPos.get(i)
                }
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
