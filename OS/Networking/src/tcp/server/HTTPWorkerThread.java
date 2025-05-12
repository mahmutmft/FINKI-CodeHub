package tcp.server;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HTTPWorkerThread extends Thread{

    private Socket socket;

    public HTTPWorkerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            System.out.println("Connected: " + " " + socket.getInetAddress() + " " + socket.getPort());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line;

            StringBuilder builder = new StringBuilder();
            while (!(line = reader.readLine()).isEmpty()){
                builder.append(line).append("\n");
                System.out.println(line);
            }
            RequestProcessor request = RequestProcessor.of(builder.toString());
            writer.write("HTTP/1.1 200 OK\n\n");
            if (request.getCommand().equals("GET") && request.getUri().equals("/time")){
                String now = LocalDateTime.now().format(DateTimeFormatter.ISO_TIME);
                writer.println("<html><body><h1>" + now+ "</h1></body><html>");
            }
            else {
                System.out.println("Hello World");
            }

            writer.flush();

        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(writer != null){
                writer.close();
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
