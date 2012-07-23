                    /**
                    // Test to see if the first letter of the word has been recorded.
                    if (markovRelations.containsKey(relationFirstPart)) {
                    // If it has been recorded, test to see if the relationship we want to add allready exists.
                    if (innerRelations.containsKey(relationSecondPart)) {
                    frequency = ((Integer) innerRelations.get(relationSecondPart)) + 1;
                    innerRelations.put(relationSecondPart, frequency);
                    } else {
                    innerRelations.put(relationSecondPart, 1);
                    }
                    } else {
                    // If the first part of the relationship is not there, a new relationship will be added.
                    markovRelations.put(relationFirstPart, innerRelations.put(relationSecondPart, 1));
                    }
                    /**/

/**
                    System.out.println(name);
                    System.out.println(relationFirstPart);
                    if (relationFirstPart.length() < 1){
                        System.out.println("it is null");
                    }
                    if (markovRelations.containsKey(relationFirstPart)) {
                        innerRelations = (HashMap) markovRelations.get(relationFirstPart);
//                        System.out.println(innerRelations);
                        frequency = innerRelations.containsKey(relationSecondPart) ? (((Integer) innerRelations.get(relationSecondPart)) + 1) : 1 ;
                        innerRelations.put(relationSecondPart, frequency);
                        markovRelations.put(relationFirstPart, innerRelations);
                        if (innerRelations.containsKey(relationSecondPart)) {
                            frequency = ((Integer) innerRelations.get(relationSecondPart)) + 1;
                            markovRelations.put(relationFirstPart, new HashMap<String, Integer>().put(relationSecondPart, frequency));
                        }
                    } else {
                        markovRelations.put(relationFirstPart, (new HashMap<String, Integer>().put(relationSecondPart, 1)));
                    }

/**/

                    /**/
//                    frequency = innerRelations.get(relationSecondPart) == null ? 1 : (((Integer) innerRelations.get(relationSecondPart)) + 1);
//                    innerRelations.put(relationSecondPart, frequency);
//                    markovRelations.put(relationFirstPart, innerRelations);

//                    OLD CODE  >>>>> >>>>>  OLD CODE  >>>>> >>>>>  OLD CODE  >>>>> >>>>>  OLD CODE  >>>>> >>>>>  OLD CODE  >>>>> >>>>>  OLD CODE  >>>>>
//                    partialWord = Character.toString(name.charAt(level0 + 1));
//                    frequency = (Integer) innerRelations.get(partialWord);
//                    innerRelations.put(partialWord, (innerRelations.containsKey(partialWord) ? 1 : frequency++));
//                    markovRelations.put(Character.toString(name.charAt(level0)), innerRelations);
//                    out.write(name.charAt(level0) + "," + name.charAt(level0 + 1) + "=00\n");
//                    OLD CODE  <<<<< <<<<<  OLD CODE  <<<<< <<<<<  OLD CODE  <<<<< <<<<<  OLD CODE  <<<<< <<<<<  OLD CODE  <<<<< <<<<<  OLD CODE  <<<<<