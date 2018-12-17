package project_OOP;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.net.*;
import java.nio.ByteBuffer;
import java.text.AttributedString;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import java.util.*;


public class ClientGUI extends Thread{

  final JTextPane jtextFilDiscu = new JTextPane();
  final JTextPane jtextListUsers = new JTextPane();
  final JTextField jtextInputChat = new JTextField();
  private JFrame jfr;
  private String oldMsg = "";
  private Thread read;
  private String serverName;
  private int PORT;
  private String name;
  BufferedReader input;
  PrintWriter output;
  Socket server;
  private Font font;
  private int privc = 0;
  private String priname = "";
  Calendar d;
  private int sec;
  private int min;
  private int hour;
  private String myname;
  private boolean mynameFlag = false;
  

public void init() {
    this.serverName = "localhost";
    this.PORT = 12345;
    this.name = "nickname";

    String fontfamily = "Arial, sans-serif";
    font = new Font(fontfamily, Font.PLAIN, 15);

    jfr = new JFrame("TeleChat");
    jfr.getContentPane().setLayout(null);
    jfr.setSize(700, 500);
    jfr.setResizable(false);
    jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Module du fil de discussion
    jtextFilDiscu.setBounds(25, 25, 490, 320);
    jtextFilDiscu.setFont(font);
    jtextFilDiscu.setMargin(new Insets(6, 6, 6, 6));
    jtextFilDiscu.setEditable(false);
    JScrollPane jtextFilDiscuSP = new JScrollPane(jtextFilDiscu);
    jtextFilDiscuSP.setBounds(25, 25, 490, 320);

    jtextFilDiscu.setContentType("text/html");
    jtextFilDiscu.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);

    
    jtextListUsers.setBounds(520, 25, 156, 320);
    jtextListUsers.setEditable(true);
    jtextListUsers.setFont(font);
    jtextListUsers.setMargin(new Insets(6, 6, 6, 6));
    jtextListUsers.setEditable(false);
    JScrollPane jsplistuser = new JScrollPane(jtextListUsers);
    jsplistuser.setBounds(520, 25, 156, 320);

    jtextListUsers.setContentType("text/html");
    jtextListUsers.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);

    // Field message user input
    jtextInputChat.setBounds(0, 350, 400, 50);
    jtextInputChat.setFont(font);
    jtextInputChat.setMargin(new Insets(6, 6, 6, 6));
    final JScrollPane jtextInputChatSP = new JScrollPane(jtextInputChat);
    jtextInputChatSP.setBounds(25, 350, 650, 50);

    // button send
    final JButton jsbtn = new JButton("Send");
    jsbtn.setFont(font);
    jsbtn.setBounds(575, 410, 100, 35);
   
    final JButton imgBtn = new JButton("Choose img");
    imgBtn.setFont(font);
    imgBtn.setBounds(400, 410, 120, 35);
    
 // button Disconnect
    final JButton jsbtndeco = new JButton("Disconnect");
    jsbtndeco.setFont(font);
    jsbtndeco.setBounds(25, 410, 130, 35);

    jtextInputChat.addKeyListener(new KeyAdapter() {
      // send message on Enter
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          sendMessage();
        }

        // Get last message typed
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          String currentMessage = jtextInputChat.getText().trim();
          jtextInputChat.setText(oldMsg);
          oldMsg = currentMessage;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          String currentMessage = jtextInputChat.getText().trim();
          jtextInputChat.setText(oldMsg);
          oldMsg = currentMessage;
        }
      }
    });

    // Click on send button
    jsbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        sendMessage();
      }
    });
    // Connection view
    final JTextField jtfName = new JTextField(this.name);
    final JTextField jtfport = new JTextField(Integer.toString(this.PORT));
    final JTextField jtfAddr = new JTextField(this.serverName);
    final JButton jcbtn = new JButton("Connect");


    jtfName.getDocument().addDocumentListener(new TextListener(jtfName, jtfport, jtfAddr, jcbtn));
    jtfport.getDocument().addDocumentListener(new TextListener(jtfName, jtfport, jtfAddr, jcbtn));
    jtfAddr.getDocument().addDocumentListener(new TextListener(jtfName, jtfport, jtfAddr, jcbtn));

    jcbtn.setFont(font);
    jtfAddr.setBounds(25, 380, 135, 40);
    jtfName.setBounds(375, 380, 135, 40);
    jtfport.setBounds(200, 380, 135, 40);
    jcbtn.setBounds(575, 380, 100, 40);

    jtextFilDiscu.setBackground(Color.LIGHT_GRAY);
    jtextListUsers.setBackground(Color.LIGHT_GRAY);

    jfr.add(jcbtn);
    jfr.add(jtextFilDiscuSP);
    jfr.add(jsplistuser);
    jfr.add(jtfName);
    jfr.add(jtfport);
    jfr.add(jtfAddr);
    jfr.setVisible(true);


    appendToPane(jtextFilDiscu, "<h2 style='text-align:center;'>--|| ข้อแนะนำในการใช้ ||--</h2>"
        +"<ul>"
        +"<li style='margin-bottom:5px'>Telechat <b style='color:#a03929;'> มี 2 mode</b> 1.Global (คุยรวม) 2.Private (คุยเดี่ยว)</li>"
        +"<li style='margin-bottom:5px'><b style='color:#a03929;'>@ชื่อ</b> เพื่อเข้าสู่ mode <b>Private</b></li>"
        +"<li style='margin-bottom:5px'>พิมพ์ <b style='color:#a03929;'>global</b> เพื่อเข้าสู่โหมด <b>Global</b></li>"
        +"<li style='margin-bottom:5px'>พิมพ์ <b style='color:#a03929;'>Mode</b> เพื่อเช็คดูว่าตัวเองอยู่ Mode ไหน</li>"
        +"<li style='margin-bottom:5px'>พิมพ์รหัสสี เช่น <b>#d3961b</b> สามารถเปลี่ยนสีชื่อของตัวเองได้</li>"
        +"<li style='margin-bottom:5px'>ขอให้ chat ให้สนุกนะครับ</li>"
        +"</ul><br/>");
    
    //Connect to server
    jcbtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        try {
          mynameFlag = false;
          name = jtfName.getText();
          String port = jtfport.getText();
          serverName = jtfAddr.getText();
          PORT = Integer.parseInt(port);

          appendToPane(jtextFilDiscu, "<span>Connecting to " + serverName + " on port " + PORT + "...</span>");
          server = new Socket(serverName, PORT);

          appendToPane(jtextFilDiscu, "<span>Connected to " +
              server.getRemoteSocketAddress()+"</span>");

          input = new BufferedReader(new InputStreamReader(server.getInputStream()));
          output = new PrintWriter(server.getOutputStream(), true);

          // send nickname to server
          output.println(name);

          // create new Read Thread
          read = new Read();
          read.start();
          //create new ReadImg Thread
          jfr.remove(jtfName);
          jfr.remove(jtfport);
          jfr.remove(jtfAddr);
          jfr.remove(jcbtn);
          jfr.add(jsbtn);
          jfr.add(jtextInputChatSP);
          jfr.add(jsbtndeco);
          jfr.revalidate();
          jfr.repaint();
          jtextFilDiscu.setBackground(Color.WHITE);
          jtextListUsers.setBackground(Color.WHITE);
        } catch (Exception ex) {
          appendToPane(jtextFilDiscu, "<span>Could not connect to Server</span>");
          JOptionPane.showMessageDialog(jfr, ex.getMessage());
        }
      }

    });

    //disconnect
    jsbtndeco.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent ae) {
    	privc = 0;
    	mynameFlag = false;
//    	appendToPane(jtextFilDiscu, "<h2>Global mode</h2>");
        jfr.add(jtfName);
        jfr.add(jtfport);
        jfr.add(jtfAddr);
        jfr.add(jcbtn);
        jfr.remove(jsbtn);
        jfr.remove(jtextInputChatSP);
        jfr.remove(jsbtndeco);
        jfr.revalidate();
        jfr.repaint();
        read.interrupt();
        jtextListUsers.setText(null);
        jtextFilDiscu.setBackground(Color.LIGHT_GRAY);
        jtextListUsers.setBackground(Color.LIGHT_GRAY);
        appendToPane(jtextFilDiscu, "<span style='margin-top:10px;'>See You Again (Telechat)</span>");
        output.close();
      }
    });

  }
	public JFrame getJfr() {
	   return jfr;
 }

  // check if all field are not empty
  public class TextListener implements DocumentListener{
    JTextField jtf1;
    JTextField jtf2;
    JTextField jtf3;
    JButton jcbtn;

    public TextListener(JTextField jtf1, JTextField jtf2, JTextField jtf3, JButton jcbtn){
      this.jtf1 = jtf1;
      this.jtf2 = jtf2;
      this.jtf3 = jtf3;
      this.jcbtn = jcbtn;
    }

    public void changedUpdate(DocumentEvent e) {}

    public void removeUpdate(DocumentEvent e) {
      if(jtf1.getText().trim().equals("") ||
          jtf2.getText().trim().equals("") ||
          jtf3.getText().trim().equals("")
          ){
        jcbtn.setEnabled(false);
      }else{
        jcbtn.setEnabled(true);
      }
    }
    public void insertUpdate(DocumentEvent e) {
      if(jtf1.getText().trim().equals("") ||
          jtf2.getText().trim().equals("") ||
          jtf3.getText().trim().equals("")
          ){
        jcbtn.setEnabled(false);
      }else{
        jcbtn.setEnabled(true);
      }
    }

  }

  // send message
  public void sendMessage() {
    try {
      String message = jtextInputChat.getText().trim();
      if (message.equals("")) {
        return;
      }
      else if(message.charAt(0) == '@' && !message.substring(1, message.length()).equals(myname)) {
    	  priname = message;
    	  privc = 1;
    	  appendToPane(jtextFilDiscu, "<h2> --|| Private mode ||-- </h2>");
    	  
    	  jfr.revalidate();
          jfr.repaint();
       }
      else if(message.substring(1, message.length()).equals(myname)) {
    	  appendToPane(jtextFilDiscu, "<p style='color:red'>You can not Chat with yourself</p>");
    	  privc = 2;
    	  jfr.revalidate();
          jfr.repaint();
          
      }
      if(privc == 0) {

    	  this.oldMsg = message;
          output.println(message);
          jtextInputChat.requestFocus();
          jtextInputChat.setText(null);
      }
      else if(privc == 1) {
    	  this.oldMsg = message;
    	  if(message.equals("global") || message.equals("Global")) {
    		  output.println("global");
    		  priname = "";
    		  privc = 0;
    		  appendToPane(jtextFilDiscu, "<h2> --|| Global mode ||-- </h2>");
    		  jtextInputChat.requestFocus();
              jtextInputChat.setText(null);
        	  jfr.revalidate();
              jfr.repaint();
    	  }
    	  else {
    		  output.println(priname + " " + message);
              jtextInputChat.requestFocus();
              jtextInputChat.setText(null);
    	  }
      }
      else if(privc == 2) {
    	  privc = 0;
    	  jtextInputChat.requestFocus();
          jtextInputChat.setText(null);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
 
	  
  public static void main(String[] args) throws Exception {
    ClientGUI client = new ClientGUI();
    client.init();
  }

  
  // read new incoming messages
  class Read extends Thread {
    public void run() {
      String message;
      ArrayList<String> ListUser;
      while(!Thread.currentThread().isInterrupted()){
        try {
          d = Calendar.getInstance();
          sec = d.get(Calendar.SECOND);
          min = d.get(Calendar.MINUTE);
          hour = d.get(Calendar.HOUR_OF_DAY);
        	
          message = input.readLine();
          System.out.println(message);
          //Append user
         if(message != null){
            if (message.charAt(0) == '[') {
              message = message.substring(1, message.length()-1);
              ListUser = new ArrayList<String>(Arrays.asList(message.split(", ")));
              jtextListUsers.setText(null);
              //List all user to JPane
              appendToPane(jtextListUsers, "<span style='color:#78f45f'>Online</span><hr style:'display:inline'>");
              for (String user : ListUser) {
                appendToPane(jtextListUsers, "@" + user);
              }
              if(mynameFlag==false) {
            	  myname = ListUser.get(ListUser.size()-1).substring(ListUser.get(ListUser.size()-1).indexOf("'>")+2, ListUser.get(ListUser.size()-1).indexOf("</"));
            	  System.out.println(myname);
            	  mynameFlag = true;
              }
            }
            else{
              //Append Message
              message =  "<p>" + message + "  " + "<span style='font-size:8px'>" + String.format("%02d:%02d:%02d", hour, min, sec)+ "</span>" + "</p>";
              appendToPane(jtextFilDiscu, message);
            }
          }
        }
        catch (IOException ex) {
          System.err.println("Failed to parse incoming message");
        }
      }
    }
  }

  // send html to pane
  private void appendToPane(JTextPane tp, String msg){
    HTMLDocument doc = (HTMLDocument)tp.getDocument();
    HTMLEditorKit editorKit = (HTMLEditorKit)tp.getEditorKit();
    try {
      editorKit.insertHTML(doc, doc.getLength(), msg, 0, 0, null);
      tp.setCaretPosition(doc.getLength());
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}

