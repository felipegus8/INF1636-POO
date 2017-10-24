package view;

import javax.swing.JFrame;
import controller.GamblerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import java.awt.Point;

import javax.swing.JButton;

public class GamblerFrame extends JFrame{	
	JButton hit = new JButton("HIT");
	JButton stand = new JButton("STAND");
	
	public GamblerPanel p = new GamblerPanel();
	public GamblerController gc = null;
	public final int LARG_DEFAULT=300; public final int ALT_DEFAULT=300;
	
	public GamblerFrame(double x, double y,double posX, double posY) {
		setSize((int) x,(int) y);
		Point po = new Point((int) posX,(int) posY);
		hit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				gc.hit();
			}
			
		});
		
		stand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
		this.setLocation(po);
		this.add(p);
		p.add(hit);
		p.add(stand);
		this.setTitle("teste");
		this.setVisible(true);
	}
	
	public void drawCard() {
		
	}
}
