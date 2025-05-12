package tcp.server;

import java.io.*;
import java.net.Socket;

public class workerThread extends Thread{

    private Socket socket = null;
    public workerThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        BufferedReader buffer = null;
        PrintWriter writer = null;

        try{
            System.out.println("Connected: " + socket.getInetAddress() + socket.getPort());
            buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line = null;

            while (!(line = buffer.readLine()).isEmpty()){
                System.out.println(line);

                writer.write(line);
                writer.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {

            if (buffer != null){
                try {
                    buffer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (writer != null){
                writer.close();
            }
            if (socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
