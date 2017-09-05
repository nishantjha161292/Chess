package thelearninggames.chess.io;

public class IODriver{
	public InputManager inpMgr;
	public OutputManager outMgr;
	
	public static enum InputType{
		NETWORK_SERVER, NETWORK_CLIENT, UI, CLI;
	}
}
