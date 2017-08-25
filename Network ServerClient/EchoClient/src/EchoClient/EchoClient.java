/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EchoClient;

import java.io.*;
import java.net.*;

public class EchoClient {

    public static void main(String args[]) {
        if (args.length != 1) {
            System.out.println("EchoClient MachineName");
        }

        InputStreamReader convert = new InputStreamReader(System.in);
        BufferedReader stdin = new BufferedReader(convert);

        try (Socket echoClient = new Socket("Barksdale-PC", 13301)) {
            PrintStream outs = new PrintStream(echoClient.getOutputStream());
            BufferedReader ins = new BufferedReader(new InputStreamReader(echoClient.getInputStream()));
            String userResponse;

            while (true) {

                System.out.println("~");
                userResponse = stdin.readLine();

                outs.println(userResponse);
                if (userResponse.equals("logout")) {
                    System.out.println("Logging Out");
                    try{
           
                    outs.println("socket closed check");
                    }
                    catch(Exception ex){
                          echoClient.close();
                    }
                    break;
                }
                System.out.println(ins.readLine());
            }
          
        } catch (IOException e) {

            System.out.println(e);
        }
    }
}
