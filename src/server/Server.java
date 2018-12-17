package server;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.net.Socket;
import java.nio.*;
import java.sql.SQLException;
import java.util.*;

import javax.imageio.ImageIO;
public class Server {
	private int port;
	private ArrayList<User> clients;
	private ServerSocket server;
	public Server(int port) {
	    this.port = port;
	    this.clients = new ArrayList<User>();
	  }
	public void run() throws IOException {
	    server = new ServerSocket(port);
	    System.out.println("Port " + this.port + " is now open.");
	    while (true) {
	      // accepts a new client
	      Socket client = server.accept();
	      // get nickname of newUser
	      Scanner scn = new Scanner (client.getInputStream());
	      String nickname = scn.nextLine();
	      nickname = nickname.replace(",", "");
	      nickname = nickname.replace(" ", "_");
	      System.out.println("New Client: \"" + nickname + "\"\n\t     Host:" + client.getInetAddress().getHostAddress());

	      // create new User
	      User newUser = new User(client, nickname);

	      // add newUser message to Arraylist
	      this.clients.add(newUser);

	      // Welcome msg
	      newUser.getOutStream().println(
	    	"<center><img src='https://www.retreatinabag.net/wp-content/uploads/637-words-free.jpg' height='100' width='300'>"
	         + "<br>"+ "<img src='https://1.bp.blogspot.com/-H4VsPInWIPU/UNmcZpyNmpI/AAAAAAAAF_c/dODBiiGpeZI/s1600/graphic_pacoo01.GIF' height='42' width='42'>"
	          + "<b>Welcome</b> " + newUser.toString() +
	          "<img src='https://1.bp.blogspot.com/-H4VsPInWIPU/UNmcZpyNmpI/AAAAAAAAF_c/dODBiiGpeZI/s1600/graphic_pacoo01.GIF' height='42' width='42'></center>"
	          );

	      // Dreate a new thread for newUser incoming messages handling
	      Thread t1 = new Thread(new UserHandler(this, newUser));
	      t1.start();
	    }
	  }
	// Delete a user from the list
	public void removeUser(User user){
		  this.clients.remove(user);
	}
	// Send incoming msg to all Users
	public void broadcastMessages(String msg, User userSender) {
		 	for (User client : this.clients) {
		 		client.getOutStream().println("<b>(Global : ) </b>" + userSender.toString() + "<span>: " + msg+ "</span>");
		    }
	}
	// Send list of clients to all Users
	public void broadcastAllUsers(){
		    for (User client : this.clients) {
		      client.getOutStream().println(this.clients);
		    }
	}
	// Send message between User 
	public void sendMessageToUser(String msg, User userSender, String receiver){
		    boolean find = false;
		    for (User client : this.clients) {
		      if (client.getName().equals(receiver) && client != userSender) {
		        find = true;
		        userSender.getOutStream().println(userSender.toString() + " -> " + client.toString() +": " + msg);
		        client.getOutStream().println("(<b>Private : </b>) " + userSender.toString() + " <span>: " + msg+"</span>");
		      }
		    }
		    if (!find) {
		      userSender.getOutStream().println(userSender.toString() + " -> (<b>no one!</b>): " + msg);
		    }
		  }
	//Server send message to user
	public void serverMessageToUser(String msg, User userSender){
	        userSender.getOutStream().println(msg);
	  }
	public static void main(String[] args) throws IOException {
		Server s = new Server(12345);
		s.run();
	}
}
