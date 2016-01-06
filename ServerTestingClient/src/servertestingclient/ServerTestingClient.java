package servertestingclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class ServerTestingClient {

    public static void main(String[] args) throws IOException {

        Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            kkSocket = new Socket("localhost", 7555);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        while ((fromServer = in.readLine()) != null) {
            System.out.println(processOutput(fromServer));
            System.out.print("---->");
            if (fromServer.equals("Bye.")) {
                break;
            }

            fromUser = stdIn.readLine();
            if (fromUser != null) {
                out.println(fromUser);
            }
        }

        out.close();
        in.close();
        stdIn.close();
        kkSocket.close();
    }

    private static String processOutput(String input) {
        String output;
        output = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '|') {
                output += input.charAt(i);
            } else {
                output += "\n";
            }
        }
        return output;
    }
}
