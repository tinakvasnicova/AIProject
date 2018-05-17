package tina.game;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import rita.RiTa;

public class Dictionary {

    ArrayList <String> dictionary;

//    constructor ; when dictionary created it will load words from library Rita returned as string array
    Dictionary () {
        String [] lexicon = RiTa.words();
        dictionary = new ArrayList<>();
         for (String string : lexicon) {
             dictionary.add(string);
         }
    }

    public ArrayList<String> getDictionary() {
        return dictionary;
    }
    public ArrayList<String> findMatchingWords (ArrayList<Character>characters) {
        ArrayList<String>bagOfWords = new ArrayList<>();

        for (String string : dictionary) {

            boolean validWord = true;
//            check each word in the dictionary
        char[] letters = string.toCharArray();

        if (characters.size()==letters.length){
//            this checks the length of words match

            int indexCount = 0;

            for (Character character : characters) {


                if (Character.isLetter(character)){
//                    is a letter that needs to be matched
                        if (character != letters[indexCount]){

                            validWord = false;
                            break;

                        }
                }
                else {
//than it doesn't need to match and is wildcard
                }

                indexCount = indexCount +1;
            }


        } else {
            validWord = false;
        }

        if (validWord == true) {
            bagOfWords.add(string);
        }
        }


        Collections.shuffle(bagOfWords);

        return bagOfWords;
    }

    public boolean isWordValid(ArrayList<Character> characters) {

        for (char character : characters){

            if (Character.isLetter(character) == false){
                return false;
            }
        }

        String wordToValidate = null;

        for (char character : characters){

            wordToValidate = wordToValidate + Character.toString(character);
        }


        if (dictionary.contains(wordToValidate)){
            return true;
        } else {
            return  false;
        }



    }
}
