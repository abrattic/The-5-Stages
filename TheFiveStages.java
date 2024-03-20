import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

abstract class Enemy {
    String name;
    int health;
    int damage;
    boolean alive;
    Enemy (String name, int health, boolean alive, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.alive = alive;
    }

    void takeDamage(int damage) {
        if (!alive)
            return;
        else
            health -= damage;

        if (health <= 0)
            alive = false;
    }
}

class Goblin extends Enemy {
    Goblin() {
        super("Goblin", 20, true, 1);
    }
    void takeDamage(int damage) {
        super.takeDamage(damage);
    }
}

class Zombie extends Enemy {
    Zombie() {
        super("Zombie", 50, true, 2);
    }
    void takeDamage(int damage) {
        super.takeDamage(damage);
    }
}

class Troll extends Enemy {
    Troll() {
        super("Troll", 50, true, 3);
    }
    void takeDamage(int damage) {
        super.takeDamage(damage);
    }
}

class Spectre extends Enemy {
    Spectre() {
        super("Spectre", 120, true, 4);
    }
    void takeDamage(int damage) {
        super.takeDamage(damage);
    }
}

class Dragon extends Enemy {
    Dragon() {
        super("Dragon", 150, true, 5);
    }
    void takeDamage(int damage) {
        super.takeDamage(damage);
    }
}

class Battlefield {
    int stage;
    ArrayList<Enemy> roster = new ArrayList<Enemy>();
    static Random rand = new Random();

    Enemy randomizer() {
        switch (rand.nextInt(stage)) {
            case 0:
                return new Goblin();

            case 1:
                return new Zombie();

            case 2:
                return new Troll();

            case 3:
                return new Spectre();

            case 4:
                return new Dragon();

            default:
                return null;
        }
    }

    Battlefield(int stage) {
        this.stage= stage; // Do I even need to do this or could I just pass the value to randomizer below?

        for (int i = 0; i < stage; i++)
            roster.add(randomizer());
    }

    void displayHP() {
        int i = 1;
        for (Enemy enemy : roster) {
            if (!enemy.alive)
                System.out.println(i + ". " + enemy.name + ": " + "Dead");
            else
                System.out.println(i + ". " + enemy.name + ": " + enemy.health);

            i++;
        }
    }

    boolean remaining() {
        for (Enemy enemy : roster)
            if (enemy.alive)
                return true;

        return false;
    }
}

public class TheFiveStages {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static class Player {
        static int health = 50;
        static int damage = 5;
        static boolean alive = true;

        static void heal(int heal) {
            health += heal;
            if (health > 50)
                health = 50;
        }

        static void dealDamage(Enemy enemy) {
            enemy.takeDamage(damage);
        }

        static void takeDamage(Enemy enemy) {
            health -= enemy.damage;

            if (health <= 0) {
                alive = false;

                System.out.print("\033[H\033[2J");
                for (int i = 0; i < 5; i++)
                    System.out.println("You Died.");
            }
        }

        static void displayHP() {
            System.out.println("Your Health: " + health);
        }

        static Enemy chooser(ArrayList<Enemy> roster, int choice) {
            int i = 1;
            for (Enemy enemy : roster) {
                if (i == choice)
                    return enemy;
                i++;
            }
            return roster.getFirst();
        }
    }

    public static void main(String[] args) {

        System.out.println("""
                        ,----,                                 ,----,.                        ,----,                                                     \s
                      ,/   .`|                               ,'   ,' |                      ,/   .`|                                                     \s
                    ,`   .'  :   ,---,                     ,'   .'   |     .--.--.        ,`   .'  :    ,---,          ,----..        ,---,.   .--.--.   \s
                  ;    ;     / ,--.' |                   ,----.'    .'    /  /    '.    ;    ;     /   '  .' \\        /   /   \\     ,'  .' |  /  /    '. \s
                .'___,/    ,'  |  |  :                   |    |   .'     |  :  /`. /  .'___,/    ,'   /  ;    '.     |   :     :  ,---.'   | |  :  /`. / \s
                |    :     |   :  :  :                   :    :  |--,    ;  |  |--`   |    :     |   :  :       \\    .   |  ;. /  |   |   .' ;  |  |--`  \s
                ;    |.';  ;   :  |  |,--.    ,---.      :    |  ;.' \\   |  :  ;_     ;    |.';  ;   :  |   /\\   \\   .   ; /--`   :   :  |-, |  :  ;_    \s
                `----'  |  |   |  :  '   |   /     \\     |    |      |    \\  \\    `.  `----'  |  |   |  :  ' ;.   :  ;   | ;  __  :   |  ;/|  \\  \\    `. \s
                    '   :  ;   |  |   /' :  /    /  |    `----'.'\\   ;     `----.   \\     '   :  ;   |  |  ;/  \\   \\ |   : |.' .' |   :   .'   `----.   \\\s
                    |   |  '   '  :  | | | .    ' / |      __  \\  .  |     __ \\  \\  |     |   |  '   '  :  | \\  \\ ,' .   | '_.' : |   |  |-,   __ \\  \\  |\s
                    '   :  |   |  |  ' | : '   ;   /|    /   /\\/  /  :    /  /`--'  /     '   :  |   |  |  '  '--'   '   ; : \\  | '   :  ;/|  /  /`--'  /\s
                    ;   |.'    |  :  :_:,' '   |  / |   / ,,/  ',-   .   '--'.     /      ;   |.'    |  :  :         '   | '/  .' |   |    \\ '--'.     / \s
                    '---'      |  | ,'     |   :    |   \\ ''\\       ;      `--'---'       '---'      |  | ,'         |   :    /   |   :   .'   `--'---'  \s
                               `--''        \\   \\  /     \\   \\    .'                                 `--''            \\   \\ .'    |   | ,'               \s
                                             `----'       `--`-,-'                                                     `---`      `----'                 \s
                                                                                                                                                         \s
                                
                """);

        System.out.println("By your friendly neighborhood idiot Shreshth Goyal\n\n"+
                "Press RETURN to continue...");

        sc.nextLine();
        System.out.print("\033[H\033[2J");

        System.out.println("""
                Rules:
                You will face five stages of enemies.
                The number of enemies you face on each battlefield will correspond to the battlefield numbering.
                
                You will start out with 100 health.
                For every turn you will deal some damage and take some as well.
                
                The type of enemies on each stage will be decided randomly. (Subject to change)
                
                On each turn, you might heal yourself.
                
                Press RETURN to continue...
                """);

        sc.nextLine();
        System.out.print("\033[H\033[2J");

        Battlefield battlefield;

        for (int stage = 1; stage <= 5; stage++) {
            battlefield = new Battlefield(stage);
            Player.health = 50;

            while (battlefield.remaining() && Player.alive) {
                System.out.print("\033[H\033[2J");
                battlefield.displayHP();
                System.out.println("\n");
                Player.displayHP();

                System.out.print("\nChoose enemy to hit: ");
                Player.dealDamage(Player.chooser(battlefield.roster, sc.nextInt()));
                Player.takeDamage(Player.chooser(battlefield.roster, rand.nextInt(stage) + 1));
                Player.heal(rand.nextInt(2));
            }
        }
    }
}
