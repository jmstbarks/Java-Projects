/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EchoServer;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

public class EchoServer {

    private static HashSet<String> usernames = new HashSet<String>();
    private static HashSet<String> passwords = new HashSet<String>();
    private static String user;

    public static void main(String args[]) {
        File file = new File("C:\\Users\\James\\Documents\\NetBeansProjects\\EchoServer\\src\\DataFiles\\UserInfo.txt");
        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                  if (line.length() == 0) continue;
                if (line != null) {
                    String[] lineSplitted = line.split(",");
                    usernames.add(lineSplitted[0]);
                    passwords.add(lineSplitted[1]);
                    
                }
         
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        try {
            ServerSocket echoServer = new ServerSocket(13301);
            //Try not to use port number < 2000. 
            System.out.println("Waiting for a client to connect...");
            while (true) {
                Socket s = echoServer.accept();
                System.out.println("Client Connected.");
                BufferedReader ins = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintStream outs = new PrintStream(s.getOutputStream());

                String line;
                String[] commands;

                while (true) {
                    line = ins.readLine();
                    commands = line.split(" ");
                    if (commands[0].equals("login")) {

                        if (usernames.contains(commands[1]) && passwords.contains(commands[2])) {
                            user = commands[1];
                            System.out.println(user + " login");
                            outs.println(user + " joins");
                            break;
                        } else {
                            outs.println("Invalid username or password please try again");

                        }
                    } else {
                        outs.println("Error, Please login first");
                    }
                }

                while ((line = ins.readLine()) != null) {

                    commands = line.split(" ");
                    int commandLength = commands.length;
                    switch (commands[0]) {
                        case "newuser":

                            if (commandLength != 3) {
                                outs.println("Server: improper number of arguments");
                                break;
                            }

                            String newUser = commands[1];
                            String newPassword = commands[2];

                            if (usernames.contains(newUser)) {
                                outs.println("Server: username already exists");
                            }

                            FileWriter writer = new FileWriter(file, true);
                            BufferedWriter bufferWriter = new BufferedWriter(writer);
        
                            String data = newUser + "," + newPassword;
                            bufferWriter.write(data);
                            bufferWriter.close();
                            outs.println("Server: New user created");

                            break;

                        case "send":

                            String message = "";
                            for (int i = 1; i < commandLength; ++i) {
                                message = message.concat(commands[i]);
                                message = message.concat(" ");
                            }
                            System.out.println(user + ": " + message);
                            outs.println(user + ": " + message);
                            break;

                        case "logout":
                            try {
                                System.out.println(user + " left");
                                outs.println(user + " logout");
                                s.close();
                            } catch (IOException ex) {
                                Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
                            } finally {
                                outs.println("ServerClient Closed.");
                            }
                            break;

                        default:
                            outs.println("Server: Invalid command");

                    }

                }
            }

        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
