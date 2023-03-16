package adventure_game;

/*
 * Project-01: Adventure Game
 * Name: Mary Ella Scroggie
 */

import java.util.Scanner;



import adventure_game.items.HealingPotion;

import java.util.Random;
/**Class Game runs the game */
public class Game {
    static Scanner in = new Scanner(System.in);
    public static Random rand = new Random(1);
    private Player player;
    
    public static void main(String[] args){

        Game game = new Game();

        game.createPlayer();
        System.out.println(game.player.toString());

        NPC opponent = new NPC("Geoff", 200, 0, 10);
        opponent.setWeakness(1);
        System.out.println(opponent.toString());
        game.enterCombat(opponent);

        in.close();
    }

    public Game() {
        
    }
    /**distribute points to different attributes */
    public void createPlayer(){
        /* TO-DO */
        /* Modify this method to allow the user to create their own player */
        /* The user will specify the player's name and description, and spend */
        /* 20 points on health, mana, and baseDamage as they see fit. */
        int points = 20;
    
        
        System.out.print("Name:");
        String username = Game.in.next();
        int myAttack = 0;
        int myHealth = 0;
        int myMana = 0;
        int choice = 0;
        while(points > 0){
        System.out.printf("Assign points to each value.  You have %d left \n", points);
        System.out.printf(" (1) Health: %d\n", myHealth);
        System.out.printf(" (2) Mana: %d\n", myMana);
        System.out.printf(" (3) Base Damage: %d\n", myAttack);
        System.out.println(" (4) Quick Start");
        choice = Game.in.nextInt();
        switch(choice){
            case 1:
                System.out.print("How many points do you want to add to health\n");
                int pointsAdd = Game.in.nextInt();
                points -= pointsAdd;
                myHealth = pointsAdd * 20;
                System.out.printf(" Health: %d\n", myHealth);
                break;
            case 2:
                System.out.print("How many points do you want to add to mana\n");
                 pointsAdd = Game.in.nextInt();
                points -= pointsAdd;
                 myMana = pointsAdd * 3;
                System.out.printf(" Mana: %d\n", myMana*3);
                break;
            case 3:
                System.out.print("How many points do you want to add to Attack\n");
                 pointsAdd = Game.in.nextInt();
                points -= pointsAdd;
                myAttack = pointsAdd;
                System.out.printf(" Attack: %d\n", myAttack);
                break;
            case 4:
                points = 0;
                myHealth = 200;
                myMana = 12;
                myAttack = 10;
                break;
        }

            
        }

        player = new Player(username, myHealth, myMana, myAttack);
        
        player.obtain(new HealingPotion());
    }
    /**
     * Combat loop
     * 
     * player and opponent take turns until one reaches 0 hp
     * @param opponent is the NPC the player is fighting
     */
    public void enterCombat(NPC opponent){
        System.out.printf("%s and %s are in a brawl to the bitter end.\n", this.player.getName(), opponent.getName());
        while(true){
            this.player.takeTurn(opponent);
            if(!opponent.isAlive()){
                System.out.printf("%S is SLAIN!!\n",opponent.getName());
                break;
            }

            opponent.takeTurn(this.player);
            if(!this.player.isAlive()){
                System.out.printf("%S is SLAIN!!\n",this.player.getName());
                break;
            }
        }
    }
}
