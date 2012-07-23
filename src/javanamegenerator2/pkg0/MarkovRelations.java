/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javanamegenerator2.pkg0;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Armand
 */
public class MarkovRelations {

    /* This function will generate the Markov Retations to be used when 
     * generating the names.
     * 
     * Parameters: String filePath - This is the path to the file containing a 
     * list of names to be used for the relationships.
     */
    public void generateMarkovRelations(String filePath) {
        try {
            // Some standard file input and output objects.
            FileInputStream input = new FileInputStream(filePath);
            DataInputStream dStream = new DataInputStream(input);
            BufferedReader in = new BufferedReader(new InputStreamReader(dStream));
//            File output = new File("src/NameData/MarkovRelations.csv");
//            BufferedWriter out = new BufferedWriter(new FileWriter(output));

            String name;        // This variable will be used to store the name that is being analyzed.
            int nameLength;     // This variable will store the amount of characters in the name that still needs to be analyzed.
            HashMap innerRelations = new HashMap<String, Integer>();
            HashMap markovRelations = new HashMap<String, HashMap>();   // Finally this variable will store the actual relationships.

            /*
             * This is where the actual relationships are being generated.  The 
             * principle is very simple:  We look at each character in the name
             * and list it's following character.  The idea is to build up a 
             * data-table that list the amount of combinations.  Those numbers
             * will be re-worked to be used as probabiolity values.  For instance:
             * in the name "Abba" we will find that a "b" can be followed by either
             * another "b" or an "a."  We will do this for up to three levels making
             * strings no longer than four characters.  In this case, it would 
             * retsult in something like this: Abba = a->b, b->b, b->a, ab->b, abb->a.
             */
            String relationFirstPart;
            String relationSecondPart;
            while ((name = in.readLine()) != null) {
                nameLength = name.length();     // Set the initial length (the full length of the name).
                for (int level0 = 0; level0 < (nameLength - 1); level0++) {       // Loop through the name to generate the first level of the relationships.
                    relationFirstPart = Character.toString(name.charAt(level0));
                    relationSecondPart = Character.toString(name.charAt(level0 + 1));
                    InsertData(markovRelations, innerRelations, relationFirstPart, relationSecondPart);
                }
                for (int level1 = 0; level1 < nameLength - 2; level1++) {       // Loop through the name to generate the second level of the relationships.
                    relationFirstPart = Character.toString(name.charAt(level1)) + name.charAt(level1 + 1);
                    relationSecondPart = Character.toString(name.charAt(level1 + 2));
                    InsertData(markovRelations, innerRelations, relationFirstPart, relationSecondPart);
                }
                for (int level2 = 0; level2 < nameLength - 3; level2++) {       // Loop through the name to generate the third level of the relationships.
                    relationFirstPart = Character.toString(name.charAt(level2)) + name.charAt(level2 + 1) + name.charAt(level2 + 2);
                    relationSecondPart = Character.toString(name.charAt(level2 + 3));
                    InsertData(markovRelations, innerRelations, relationFirstPart, relationSecondPart);
                }
            }


            java.util.Iterator it = markovRelations.entrySet().iterator();
            int counter = 0;
            while (it.hasNext()) {
                java.util.Map.Entry pairs = (java.util.Map.Entry) it.next();
                System.out.println(pairs.getKey() + " = " + pairs.getValue());
                System.out.println(counter++);
                it.remove(); // avoids a ConcurrentModificationException
            }

            input.close();
            in.close();
//            out.close();
        } catch (IOException ex) {
            Logger.getLogger(NameGeneratorGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void InsertData(
            HashMap<String, HashMap> markovRelations,
            HashMap<String, Integer> innerRelations,
            String relationFirstPart,
            String relationSecondPart) {
        if (markovRelations.containsKey(relationFirstPart)) {
            innerRelations = (HashMap) markovRelations.get(relationFirstPart);
            Integer frequency = ((Integer) innerRelations.get(relationSecondPart));
            frequency = frequency == null ? new Integer(1) : frequency + 1;
            innerRelations.put(relationSecondPart, frequency);
            markovRelations.put(relationFirstPart, innerRelations);
        } else {
            innerRelations = new HashMap<String, Integer>();
            innerRelations.put(relationSecondPart, new Integer(1));
            markovRelations.put(relationFirstPart, innerRelations);
        }
    }
}
