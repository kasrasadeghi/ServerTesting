package servertesting;

import java.io.*;
import java.net.Socket;

public class MultiServerThread extends Thread {

    private Socket socket = null;

    public MultiServerThread(Socket socket) {
        super("MultiServer Thread");
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            String inputLine;
            out.println("Welcome to RPGTown! Please enter a number...");
            while ((inputLine = in.readLine()) != null) {
                //do something with input and return something to client!
                int num = Integer.parseInt(inputLine);
                out.println(num + "^2 = " + num * num + "|Pick another number!");
                if (inputLine.equals("Bye")) {
                    break;
                }
            }
            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
