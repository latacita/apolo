import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FrameScrollVertical extends JFrame {
    private JPanel      jContentPane    = null;
    private JScrollPane jMainScrollPane = null;
    private JPanel      jMainPanel      = null;

    public FrameScrollVertical(){
        super();
        initialize();
    }

    private void initialize(){
        this.setSize(480, 339);
        this.setContentPane(getJContentPane());
        this.setTitle("JFrame");
    }

    private JPanel getJContentPane(){
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJMainScrollPane(), BorderLayout.CENTER);
        }
        return jContentPane;
    }

    private JScrollPane getJMainScrollPane(){
        if (jMainScrollPane == null) {
            jMainScrollPane = new JScrollPane();
            jMainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jMainScrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            jMainScrollPane.setViewportView(getJMainPanel());
        }
        return jMainScrollPane;
    }

    private JPanel getJMainPanel(){
        if (jMainPanel == null) {
            jMainPanel = new JPanel();
            jMainPanel.setLayout(new BorderLayout());
            jMainPanel.setPreferredSize(new Dimension(30, 30));
        }
        return jMainPanel;
    }
}