import java.util.Random;

public class Main {
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenseType;

    /*-----------------------------------------------*/
    public static int[] heroesHealth = {280, 270, 250, 400, 300, 250, 200,600};
    public static int[] heroesDamage = {10, 15, 20, 0, 10, 15, 10,5};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Lucky", "Thor", "Berserk","Golem"};

    /*-----------------------------------------------*/


    /*-----------------------------------------------*/
    public static int round;

    /*-----------------------------------------------*/
    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            System.out.println();
            round();
        }
    }

    public static void chooseBossDefense() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenseType = heroesAttackType[randomIndex];
    }

    public static void round() {
        round++;
        chooseBossDefense();
        bossHits();
        heroesHits();
        printStatistics();
        Medic();
        Lucky();
        Thor();
        Berserk();
        Golem();

    }

    /*-----------------------------------------------*/
    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }


        }
    }


    /*-----------------------------------------------*/
    public static void heroesHits() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {


                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefenseType) {
                    Random random = new Random();
                    int coeff = random.nextInt(9);
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);


                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;

                }
            }
        }
    }

    public static void Medic() {

        for (int i = 0; i < heroesHealth.length; i++) {
            if (i != 3) {

            }
            if (heroesHealth[i] > 0 && heroesHealth[i] <= 150 && heroesHealth[3] > 0) {

                int treat = 60;
                heroesHealth[i] = heroesHealth[i] + treat;
                System.out.println("Медик вылечил: " + heroesAttackType[i] + " и добавил + " + treat + " жизней");
                break;
            }


        }
    }

    /*-----------------------------------------------*/
    public static void Lucky() {
        Random random = new Random();
        boolean aliveLucky = random.nextBoolean();
        if (heroesHealth[4] > 0 && aliveLucky == true) {
            heroesHealth[4] = heroesHealth[4] + 50;
            System.out.println("Lucky увернулся от удара");

        }

    }

    public static void Thor() {
        Random random = new Random();
        boolean superPower = random.nextBoolean();
        if (superPower == true) {
            bossDamage = 0;
            System.out.println("Тор оглушил Босса на 1 раунд");

        } else {
            bossDamage = 50;
        }
    }

    public static void Berserk() {
        heroesHealth[6] = heroesHealth[6] - bossDamage / 2;
        int powerBerserk=bossDamage/2;
        if (heroesHealth[6]>0) {
            heroesDamage[6]= heroesDamage[6]+powerBerserk;

            System.out.println("Berserk hit the Boss "+heroesDamage[6]);

        }
    }


    public static void Golem(){
        int getDamage=bossDamage/5;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[7] >0 &&heroesHealth[i]>0 ) {
                heroesHealth[i]+=getDamage;
                heroesHealth[7]-=getDamage;
            }
            
        }System.out.println("Golem get 1/5 damage from Boss");
    }



    /*-----------------------------------------------*/


    public static void printStatistics() {
        System.out.println("ROUND: " + round + "-----");

        System.out.println();

        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defense: " + (bossDefenseType == null ? "No defense" : bossDefenseType));

        System.out.println();

        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " Health: " + heroesHealth[i] + " Damage " + heroesDamage[i]);

        }
    }

    /*-----------------------------------------------*/


    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!!!!!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!!!!!!!!!");
        }
        return allHeroesDead;

    }
}

