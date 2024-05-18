package examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;

public class FiveStarsPanel extends JPanel {
    private boolean[] starsColored = {true, false, false, false, false}; // Inicializamos la primera estrella coloreada
    private int estrellasRellenas = 1; // Inicializamos con una estrella rellena
    private StarRatingListener listener;

    public interface StarRatingListener {
        void ratingChanged(int rating);
    }

    public void setStarRatingListener(StarRatingListener listener) {
        this.listener = listener;
    }

    public FiveStarsPanel() {
        this.setPreferredSize(new Dimension(400, 100));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int starWidth = getWidth() / 5;
                int starIndex = e.getX() / starWidth;
                if (starIndex < 5) {
                    for (int i = 0; i <= starIndex; i++) {
                        starsColored[i] = true;
                    }
                    for (int i = starIndex + 1; i < 5; i++) {
                        starsColored[i] = false;
                    }
                    estrellasRellenas = starIndex + 1;
                    if (estrellasRellenas == 0) {
                        estrellasRellenas = 1;
                        starsColored[0] = true;
                    }
                    if (listener != null) {
                        listener.ratingChanged(estrellasRellenas);
                    }
                    repaint();
                }
            }
        });

        // Inicializar al menos una estrella rellena
        starsColored[0] = true;
    }

    private Path2D createStar(int centerX, int centerY, int outerRadius, int innerRadius) {
        Path2D path = new Path2D.Double();
        double angle = Math.PI / 5;

        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? outerRadius : innerRadius;
            double x = centerX + Math.cos(i * angle - Math.PI / 2) * r;
            double y = centerY + Math.sin(i * angle - Math.PI / 2) * r;
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.closePath();
        return path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Antialiasing for smooth drawing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int starWidth = width / 5;
        int outerRadius = starWidth / 2 - 10;
        int innerRadius = outerRadius / 2;

        for (int i = 0; i < 5; i++) {
            int centerX = starWidth * i + starWidth / 2;
            int centerY = height / 2;

            Path2D star = createStar(centerX, centerY, outerRadius, innerRadius);

            if (starsColored[i]) {
                g2d.setColor(Color.YELLOW); 
                g2d.fill(star);
            } else {
                g2d.setColor(Color.BLACK);
                g2d.draw(star);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Five Stars Example");
        FiveStarsPanel starsPanel = new FiveStarsPanel();
        frame.add(starsPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
