package other;

	public interface ObservadoIF {
		public void registerObserver (ObservadorIF observer);
	    public void removeObserver (ObservadorIF observer);
	    public void notifyObservers (int mensagem, Object obj);
	}

