package IO;

import java.util.Scanner;

public class CommandLineInput implements IOManager{

    Scanner Cin ;

    CommandLineInput(){
       Cin = new Scanner(System.in);
    }

    @Override
    public int getFrom() {
        return Cin.nextInt();
    }

    @Override
    public int getTo() {
        return Cin.nextInt();
    }

	@Override
	public void setFrom(int a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTo(int a) {
		// TODO Auto-generated method stub
		
	}

	
}
