import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Custom Canvas that draws a polygon from points stored in a
 * Bidirectional Circular Linked List (BCLL).
 *
 * Fixes applied:
 * - Constructor now assigns draw, x, y fields (was missing → NPE).
 * - paint() iterates the full linked list so ALL edges render on every repaint.
 */
class PolygonCanvas extends Canvas {

    private static final long serialVersionUID = 1L;
    private final BCLL draw;

    public PolygonCanvas(BCLL draw) {
        this.draw = draw;
        setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        if (draw == null || draw.size == 0) {
            return;
        }

        BCLL.Node current = draw.head;

        // Draw all vertices and edges by traversing the circular list
        for (int i = 0; i < draw.size; i++) {
            // Draw vertex dot
            g.setColor(Color.RED);
            g.fillOval(current.x - 3, current.y - 3, 6, 6);

            // Draw edge to next vertex (closes polygon on last iteration)
            if (draw.size > 1) {
                g.setColor(Color.BLACK);
                g.drawLine(current.x, current.y, current.next.x, current.next.y);
            }

            current = current.next;
        }
    }
}

/**
 * AWT Frame application that lets users enter X,Y coordinates
 * to dynamically draw a polygon on a canvas.
 *
 * Fixes applied:
 * - Removed unused Graphics field (getGraphics() returns null during construction).
 * - Removed broken "new newCanvas()" call that created an orphan object.
 * - Canvas now repaints after each new point is added.
 * - Added WindowListener so the window can be closed properly.
 * - Empty catch block now prints the error for debugging.
 * - Removed incorrect package declaration (files are in the default package).
 */
public class methods extends Frame {

    private static final long serialVersionUID = 1L;
    private final BCLL draw = new BCLL();
    private final PolygonCanvas canvas = new PolygonCanvas(draw);

    public methods() {
        super("AWT Polygon Drawing");

        // --- UI Components ---
        Label title = new Label("Enter Coordinates");
        title.setBounds(95, 40, 120, 20);
        add(title);

        TextField xField = new TextField("X");
        TextField yField = new TextField("Y");
        xField.setBounds(80, 100, 50, 20);
        yField.setBounds(170, 100, 50, 20);
        xField.setBackground(Color.LIGHT_GRAY);
        yField.setBackground(Color.LIGHT_GRAY);
        add(xField);
        add(yField);

        Button drawBtn = new Button("Draw");
        drawBtn.setBounds(120, 150, 60, 20);
        drawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1 = xField.getText().trim();
                String s2 = yField.getText().trim();
                try {
                    int xx = Integer.parseInt(s1);
                    int yy = Integer.parseInt(s2);
                    draw.append(xx, yy);
                    canvas.repaint();   // Trigger canvas redraw with new point
                } catch (NumberFormatException ex) {
                    System.err.println("Invalid coordinates: " + s1 + ", " + s2);
                }
            }
        });
        add(drawBtn);

        // --- Canvas ---
        canvas.setBounds(0, 200, 300, 500);
        add(canvas);

        // --- Frame setup ---
        setSize(300, 750);
        setLayout(null);
        setVisible(true);

        // Window close handler
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new methods();
    }
}
