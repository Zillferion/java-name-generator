/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javanamegenerator2.pkg0;

import java.util.Random;

/**
 *
 * @author Armand
 */
public class NameGeneratorLogic implements Runnable {

    private final static char[] VOWELS = {'a', 'e', 'i', 'o', 'u', 'y'};
    private NameGeneratorGui gui;
    private int nameLength;
    private int nameAmount;

    public NameGeneratorLogic(NameGeneratorGui gui, int nameLength, int nameAmount) {
        this.gui = gui;
        this.nameLength = nameLength;
        this.nameAmount = nameAmount;
    }
    /*
    public String generateName(int lengthBase) {
    Random rand = new Random();
    
    int nameLengthBase;
    nameLengthBase = rand.nextInt(lengthBase / 2) + 1;
    String name = Character.toString((char) (rand.nextInt(25) + 97)).toUpperCase();
    for (int i = 0; i < nameLengthBase; i++) {
    name += Character.toString(VOWELS[rand.nextInt(6)])
    + (rand.nextBoolean()
    ? Character.toString((char) (rand.nextInt(25) + 97))
    : Character.toString((char) (rand.nextInt(25) + 97))
    + Character.toString((char) (rand.nextInt(25) + 97)));
    }
    return name;
    }
     */

    public void generateName(int length, int amount) {
        Random random = new Random();
        char[] name = new char[length];

        for (int a = 0; a < amount; a++ ) {
            for (int i = 0; i < length; i++) {
                name[i] = (char) (random.nextInt(25) + 97);
            }
            gui.writeName(new String(name));
        }
    }

    @Override
    public void run() {
        generateName(nameLength, nameAmount);
    }
}
