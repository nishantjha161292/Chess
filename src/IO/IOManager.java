package IO;

public abstract class IOManager implements InputManager, OutputManager{
	public static InputManager inpMgr;
	public static OutputManager outMgr;
	
	public static enum InputType{
		NETWORK_SERVER, NETWORK_CLIENT, UI, CLI;
	}
}
