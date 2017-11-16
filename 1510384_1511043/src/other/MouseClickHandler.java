package other;

import java.awt.Point;
import java.awt.event.MouseEvent;
import other.ObservadoIF;
import other.ObservadorIF;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MouseClickHandler implements MouseListener, ObservadoIF {

	
	private ArrayList<ObservadorIF> observers = new ArrayList<ObservadorIF>();
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		System.out.println("olha o ponto ai" + p);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerObserver(ObservadorIF observer) {
		observers.add(observer);
		
	}

	@Override
	public void removeObserver(ObservadorIF observer) {
		for(ObservadorIF o: observers) {
			if(o == observer) {
				observers.remove(o);
			}
		}
		
	}

	@Override
	public void notifyObservers(String mensagem, Object obj) {
		// TODO Auto-generated method stub
		
	}

}
