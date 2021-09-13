package com.anand.coding.problems.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Format the words of a text in multiline each containing maxWidth characters and is fully justified.
 * Evenly distribute extra spaces between the words if possible else adjust more spaces from left to right.
 * Keep the last line left justified without extra spaces between the words.
 *
 * Example:
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 */
public class _08_TextJustification {

    public static List<String> fullJustify(String[] words, int maxWidth) {

        List<String> list = new ArrayList<>();

        // Lines full justified
        int charCount=0, wordCount=0;
        for(int i=0; i<words.length; ){

            if(charCount+words[i].length()+wordCount <= maxWidth){
                charCount +=words[i].length();
                wordCount++; i++;
                continue;
            }

            if(wordCount==0){
                continue;
            }
            StringBuilder sb = new StringBuilder();
            int totalSpaces = maxWidth-charCount;

            int spacePerWord = wordCount==1 ? totalSpaces : totalSpaces / (wordCount-1);
            int extraSpace   = wordCount==1 ? 0 : totalSpaces % (wordCount-1);

            for (int j=i-wordCount; j<i-1; j++) {
                sb.append(words[j]);
                for (int k = 0; k < spacePerWord; k++) {
                    sb.append(' ');
                }
                if(extraSpace-->0){
                    sb.append(' ');
                }
            }
            sb.append(words[i-1]);
            while (sb.length()<maxWidth) sb.append(' ');

            list.add(sb.toString());
            charCount=0; wordCount=0;
        }

        // Last line left justified
        if(wordCount>0) {
            StringBuilder sb = new StringBuilder();
            int i = words.length;
            for (int j=i-wordCount; j<i-1; j++) {
                sb.append(words[j]).append(' ');
            }
            sb.append(words[i-1]);
            while (sb.length()<maxWidth) sb.append(' ');

            list.add(sb.toString());
        }
        return list;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        String[] text0 = {"What","must","be","acknowledgment","shall","be"};
        System.out.println(fullJustify(text0, 16));

        String[] text = {"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println(fullJustify(text, 16));

        String[] text1 = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        System.out.println(fullJustify(text1, 20));
    }
}
