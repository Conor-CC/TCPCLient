import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence = "";
        String ip;
        int port;
        Scanner dataScan = new Scanner(System.in);

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("IP: ");
        ip = dataScan.nextLine();
        System.out.print("\nPORT: ");
        port = dataScan.nextInt();

        Socket clientSocket = new Socket(ip, port);
        System.out.println("Connected to " + ip + ":" + port);
        boolean run = true;
        while (run) {
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            if (modifiedSentence == null) {
                run = false;
            }
            System.out.println("FROM SERVER: " + modifiedSentence);
        }
        clientSocket.close();
    }
}