package thelearninggames.chess.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkInputOutput implements IOManager {

    static final int port = 24377;

    ServerSocket ss;
    Socket s;
    DataInputStream input;
    DataOutputStream output;
    

    @Override
    public int getFrom() {
        try {
            return input.readInt();
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getTo() {
        try {
            return input.readInt();
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public void setFrom(int from) {
        try {
            output.writeInt(from);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setTo(int to) {
        try {
            output.writeInt(to);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public NetworkInputOutput(){

        try {
            ss = new ServerSocket(port);
            s = ss.accept();
            input  = new DataInputStream(s.getInputStream());
            output = new DataOutputStream(s.getOutputStream());
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    public NetworkInputOutput(String IP){
        try {
            s = new Socket(IP, port);
            input  = new DataInputStream(s.getInputStream());
            output = new DataOutputStream(s.getOutputStream());
        }
        catch (IOException error){
            error.printStackTrace();
        }
    }

}
