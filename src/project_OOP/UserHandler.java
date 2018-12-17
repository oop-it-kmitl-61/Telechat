package project_OOP;

import java.awt.Image;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;

import java.util.Scanner;

class UserHandler implements Runnable {

  private Server server;
  private User user;
  private String mode = "Mode -> <span style='color:#447728'>Global</span>";

  public UserHandler(Server server, User user) {
    this.server = server;
    this.user = user;
    this.server.broadcastAllUsers();
  }

  public void run() {
    String message;

   
    Scanner scn = new Scanner(this.user.getInputStream());
    while (scn.hasNextLine()) {
      message = scn.nextLine();

      // smiley
      
      message = message.replace(":)", "<img src='http://4.bp.blogspot.com/-ZgtYQpXq0Yo/UZEDl_PJLhI/AAAAAAAADnk/2pgkDG-nlGs/s1600/facebook-smiley-face-for-comments.png'>");
      message = message.replace(":D", "<img src='http://2.bp.blogspot.com/-OsnLCK0vg6Y/UZD8pZha0NI/AAAAAAAADnY/sViYKsYof-w/s1600/big-smile-emoticon-for-facebook.png'>");
      message = message.replace(":d", "<img src='http://2.bp.blogspot.com/-OsnLCK0vg6Y/UZD8pZha0NI/AAAAAAAADnY/sViYKsYof-w/s1600/big-smile-emoticon-for-facebook.png'>");
      message = message.replace(":(", "<img src='http://2.bp.blogspot.com/-rnfZUujszZI/UZEFYJ269-I/AAAAAAAADnw/BbB-v_QWo1w/s1600/facebook-frown-emoticon.png'>");
      message = message.replace("-_-", "<img src='http://3.bp.blogspot.com/-wn2wPLAukW8/U1vy7Ol5aEI/AAAAAAAAGq0/f7C6-otIDY0/s1600/squinting-emoticon.png'>");
      message = message.replace(";)", "<img src='http://1.bp.blogspot.com/-lX5leyrnSb4/Tv5TjIVEKfI/AAAAAAAAAi0/GR6QxObL5kM/s400/wink%2Bemoticon.png'>");
      message = message.replace(":P", "<img src='http://4.bp.blogspot.com/-bTF2qiAqvi0/UZCuIO7xbOI/AAAAAAAADnI/GVx0hhhmM40/s1600/facebook-tongue-out-emoticon.png'>");
      message = message.replace(":p", "<img src='http://4.bp.blogspot.com/-bTF2qiAqvi0/UZCuIO7xbOI/AAAAAAAADnI/GVx0hhhmM40/s1600/facebook-tongue-out-emoticon.png'>");
      message = message.replace(":o", "<img src='http://1.bp.blogspot.com/-MB8OSM9zcmM/TvitChHcRRI/AAAAAAAAAiE/kdA6RbnbzFU/s400/surprised%2Bemoticon.png'>");
      message = message.replace(":O", "<img src='http://1.bp.blogspot.com/-MB8OSM9zcmM/TvitChHcRRI/AAAAAAAAAiE/kdA6RbnbzFU/s400/surprised%2Bemoticon.png'>");
      message = message.replace("cat", "<img src='https://i.ytimg.com/vi/YCaGYUIfdy4/maxresdefault.jpg' height='60' width='60'>");
      message = message.replace("dog", "<img src='https://thenypost.files.wordpress.com/2018/05/180516-woman-mauled-by-angry-wiener-dogs-feature.jpg?quality=90&strip=all&w=618&h=410&crop=1' height='60' width='60'>");
      message = message.replace("elephant", "<img src= 'http://topicstock.pantip.com/chalermthai/topicstock/2010/06/A9395021/A9395021-vote9.jpg' height= '60' width= '60'>");
      message = message.replace("kangaroo", "<img src='https://png.pngtree.com/element_origin_min_pic/16/06/24/09576c88a9aba6b.jpg' height='60' width='60'>");
      message = message.replace("monkey", "<img src='https://us.123rf.com/450wm/dazdraperma/dazdraperma1302/dazdraperma130200009/17695914-cute-baby-monkey-on-a-tree-holding-banana-.jpg?ver=6' height='60' width='60'>");
      message = message.replace("rabbit", "<img src='https://imgix.bustle.com/rehost/2016/9/13/f046b660-a9ff-4cdd-bd99-b5a83fdad984.jpg?w=970&h=582&fit=crop&crop=faces&auto=format&q=70' width='60' height='60'>");
      message = message.replace("bird", "<img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhRhOPSRGneTi6ap4XhykSgOStkJIv_F8c4nAfEX6qJn9TTmIm' width='60' height='60'>");
      message = message.replace("merry christmas", "<img src=\"https://3.bp.blogspot.com/-UBhNyX1limQ/UMlfTLYBqXI/AAAAAAAACPs/HDVGHkZ4QhU/s1600/cncy_p_decoration000386.gif\" width=\"60\" height=\"60\">");
      message = message.replace("thank you","<img src=\"https://4.bp.blogspot.com/-79nC5qQFs68/Wl8GFRgygjI/AAAAAAAATjs/1KgLHY6uEl0wEAsZxd1VWWlXXzdXRrHtQCLcBGAs/s1600/stitch%2B%252822%2529.gif\" width=\"60\" height=\"60\">");
      message = message.replace("hi", "<img src=\"https://4.bp.blogspot.com/-gew8xyCGNk0/UMcCP560zEI/AAAAAAAAB_c/00ftqOvU3pg/s1600/llk35.gif\" width=\"60\" height=\"60\">");
      message = message.replace("<3", "<img src=\"https://2.bp.blogspot.com/-FYI2rRcGmTs/UMrD3DF-IvI/AAAAAAAADpc/I4GkP0VQXNc/s1600/graphic1_mini058.gif\" width=\"60\" height=\"60\">");
      message = message.replace("bye", "<img src=\"https://1.bp.blogspot.com/-ySbVq-RqyPE/Wl8GIP8CzBI/AAAAAAAATkY/F6HfiSh6SIEn5OryjemOZrgiDJmlp38PgCLcBGAs/s1600/stitch%2B%25287%2529.gif\" width=\"60\" height=\"60\">");
      message = message.replace("555", "<img src=\"https://3.bp.blogspot.com/-oHbS0SBDJ3Q/UwcU00fnKUI/AAAAAAAARZY/Qae8fwLwHnM/s1600/3e3.gif\" width=\"60\" height=\"60\">");
      message = message.replace("no", "<img src=\"https://4.bp.blogspot.com/-vjkjQVpUuQU/Ua2DOPz6LbI/AAAAAAAAKOg/r8NesDrJ1gg/s1600/graphic3d-china-pig_08.gif\" width=\"60\" height=\"60\">");
      message.toLowerCase();

      
     if (message.charAt(0) == '@'){
    	 this.mode = "Mode -> <span style='color:#a51313;'>Private</span>";
    	 if(message.contains("mode")) {
    		 server.serverMessageToUser(this.mode, user);
         } 
    	 else if(message.contains("whoami")) {
    		 server.serverMessageToUser("Your name is <span style='color:#752364;'><b>"+user.getName()+"</b></span>", user);
    		 
    	 }
    	 else if(message.contains(" ")){
          System.out.println("private msg : " + message);
          int firstSpace = message.indexOf(" ");
          String userPrivate = message.substring(1, firstSpace);
          server.sendMessageToUser(message.substring(firstSpace+1, message.length()), user, userPrivate);
        }
      }else if (message.charAt(0) == '#'){
        user.changeColor(message);
        // update color for all other users
        this.server.broadcastAllUsers();
      }
      else if(message.equals("whoami")) {
    	  server.serverMessageToUser("Your name is <span style='color:#752364;'><b>"+user.getName()+"</b></span>", user);
      }
      else if(message.equals("global")) {
    	  this.mode = "Mode -> <span style='color:#447728'>Global</span>";
      }
      else if(message.equals("mode")) {
    	  server.serverMessageToUser(this.mode, user);
      }
      else{
        // update user list
        server.broadcastMessages(message, user);
      }
    }
    // end of Thread
    server.removeUser(user);
    this.server.broadcastAllUsers();
    scn.close();
  }
}
