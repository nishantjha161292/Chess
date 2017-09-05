package IO;

public interface IOManager {
	
	public static enum InputType{
		NETWORK_SERVER, NETWORK_CLIENT, UI, CLI;
	}

    int getFrom();
    int getTo();

    void setFrom(int a);
    void setTo(int a);
}
