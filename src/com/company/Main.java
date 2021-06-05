package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 2900;
    public static int bossDamage = 290;
    public static String bossDefenceType = " ";
    public static int[] heroesHealth = {400, 400, 400, 800, 700};
    public static int[] heroesDamage = {45, 35, 75, 140};
    public static int[] heroesReturnDamage = {0, 0, 0, 285, 0};
    public static int[] heroesHeal = {0, 0, 0, 0, 500};
    public static String[] heroesAttackType = {"Tank", "Dodger", "Bersek", "Tor", "Medical"};

    public static void main(String[] args) {
        fightInfo();
        while (!isFinished()) {
            round();
        }

    }

    public static void round() {
        changeBossDefence();
        bossHit();
        heroesHit();
        fightInfo();
    }

    public static void changeBossDefence() {
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won! ");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesReturnDamage[3] <= 0){
            System.out.println("Boss won");
            return true;

        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++)
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else heroesHealth[i] = heroesHealth[i] + heroesReturnDamage[i] + heroesHeal[i] - bossDamage;
                    }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random random2 = new Random();
                    int koeff = random2.nextInt(9)+ 2; //критический урон
                    if (bossHealth - heroesDamage[i] - heroesReturnDamage[i] * koeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * koeff;
                    }
                    System.out.println(heroesAttackType[i] + " critical hit" + heroesDamage[i] + koeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] + heroesReturnDamage[i] + heroesHeal[i];
                    }
                    }
                }
            }
        }


        //Статистика боя
        public static void fightInfo () {
            System.out.println("_______________________");
            System.out.println("Boss health " + bossHealth);
            System.out.println("Warrior health:" + heroesHealth[0]);
            System.out.println("Magic health:" + heroesHealth[1]);
            System.out.println("Kinetic health:" + heroesHealth[2]);
            System.out.println ("Bersek health:" + heroesHealth[3]);
            System.out.println("Medical health:" + heroesHealth[4]);
            System.out.println("_______________________");
        }

    }


