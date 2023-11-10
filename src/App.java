import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;

public class App extends PApplet {

    ArrayList<PVector> particlesPos = new ArrayList<PVector>();
    ArrayList<PVector> particlesVel = new ArrayList<PVector>();
    PVector buffer;
    int size = 20;
    int startValue = 50;
    float energyloss = 1f;
    PVector afstand;
    float overlap;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        for (int i = 0; i < startValue; i++) {
            particlesPos.add(new PVector(random(size / 2, width - size / 2), random(size / 2, height - size / 2)));
            particlesVel.add(new PVector(random(4) - 2f, random(4) - 2f));
        }
        buffer = new PVector(0, 0);
        frameRate(144);
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

                afstand = particlesPos.get(i).copy().sub(particlesPos.get(j).copy());

                if (afstand.mag() <= size) {
                    overlap = size - afstand.mag();
                    afstand.normalize();
                    particlesPos.get(i).add(PVector.mult(afstand, overlap
                            * (particlesVel.get(i).mag() / (particlesVel.get(i).mag() + particlesVel.get(j).mag()))));
                    particlesPos.get(j).sub(PVector.mult(afstand, overlap
                            * (particlesVel.get(j).mag() / (particlesVel.get(i).mag() + particlesVel.get(j).mag()))));

                    particlesVel.get(i).mult(energyloss);
                    particlesVel.get(j).mult(energyloss);

                    buffer = particlesVel.get(i).copy();
                    particlesVel.get(i).set(particlesVel.get(j));
                    particlesVel.get(j).set(buffer);
                }
            }
        }

        for (int i = 0; i < particlesVel.size(); i++) {
            particlesPos.get(i).x = particlesPos.get(i).x + particlesVel.get(i).x;
            particlesPos.get(i).y = particlesPos.get(i).y + particlesVel.get(i).y;

            // particlesVel.get(i).y = particlesVel.get(i).y + 9.82f;
        }

        for (PVector dot : particlesPos) {
            circle(dot.x, dot.y, size);
        }
    }
}
