package racing;

import dao.PlayerDAO;
import entity.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerActivity {

    public PlayerActivity() {
    }

    public Player registration(){
        /*String firstName,
                             String lastName,
                             String login,
                             String password*/
        PlayerDAO playerDAO = new PlayerDAO();
        Player player = null;
        Player playerIn = null;

        Scanner in  = new Scanner(System.in);

        player      = new Player();
        playerIn    = new Player();

        System.out.println("You need to login:");
        System.out.println("------------------");
        System.out.println("Input First Name:");
        playerIn.setFirstName (in.nextLine());
        System.out.println("Input Last Name:");
        playerIn.setLastName  (in.nextLine());
        System.out.println("Input login:");
        playerIn.setLogin     (in.nextLine());
        System.out.println("Input password:");
        playerIn.setPassword  (in.nextLine());

        player = playerDAO.lookFor(playerIn);

        if(player.getId() == 0){
            System.out.println("Player not found. Would you register? [Y/N]");
            if ("Y".equals(in.nextLine())){
                player = playerDAO.save(playerIn);
            }
        }
        in.close();
        return player;
    }

    public Player changeMyInfo(){
        PlayerDAO playerDAO = new PlayerDAO();
        Player player = null;
        Player playerIn = null;

        Scanner in  = new Scanner(System.in);

        player      = new Player();
        playerIn    = new Player();

        System.out.println("Input First Name:");
        playerIn.setFirstName (in.nextLine());
        System.out.println("Input Last Name:");
        playerIn.setLastName  (in.nextLine());
        System.out.println("Input login:");
        playerIn.setLogin     (in.nextLine());
        System.out.println("Input password:");
        playerIn.setPassword  (in.nextLine());

        player = playerDAO.lookFor(playerIn);
        System.out.println(player);

        if(player.getId() == 0) {
            System.out.println("Player not found.");
        }
        else {
            System.out.println("Would you change info? [Y/N]");
            if ("Y".equals(in.nextLine())){
                /** Change everything except id */
                System.out.println("Input First Name:");
                playerIn.setFirstName (in.nextLine());
                System.out.println("Input Last Name:");
                playerIn.setLastName  (in.nextLine());
                System.out.println("Input login:");
                playerIn.setLogin     (in.nextLine());
                System.out.println("Input password:");
                playerIn.setPassword  (in.nextLine());
                player = playerDAO.update(playerIn, (long) player.getId());
            }
        }
        in.close();
        return player;
    }

}
