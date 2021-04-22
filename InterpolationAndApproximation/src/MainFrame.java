import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 2269971701250845501L;
	
	public MainFrame() {
		setResizable(false);
		setTitle("Interpolation/Approximation");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(150, 150, 400, 150);
		
		JPanel contentPane = new JPanel(new FlowLayout(0, 50, 50));
		JButton interpolation = new JButton("Interpolation");
		interpolation.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new InterpolationFrame().setVisible(true);
			}
		});
		contentPane.add(interpolation);
		JButton approximation = new JButton("Approximation");
		approximation.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ApproximationFrame().setVisible(true);
			}
		});
		contentPane.add(approximation);
		setContentPane(contentPane);
		
	}
}
