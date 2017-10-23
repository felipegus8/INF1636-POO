import java.awt.*;

import javax.swing.*;

public class Frame extends JFrame {
	JButton j1 = new JButton("First");
	JButton j2 = new JButton("Second");
	JPanel p = new JPanel();
	JLabel label = new JLabel();
	public final int LARG_DEFAULT=891; public final int ALT_DEFAULT=707;
	
	Image i;
	
	public Frame() {
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		
		p.add(j1);
		p.add(j2);
		this.add(p);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
