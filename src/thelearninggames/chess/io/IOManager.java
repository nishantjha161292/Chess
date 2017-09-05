package thelearninggames.chess.io;

/**
 * @author njha
 */
public class IOManager{
	public InputManager inpMgr;
	public OutputManager outMgr;
	
	public static enum InputType{
		NETWORK_SERVER, NETWORK_CLIENT, UI, CLI;
	}
}
