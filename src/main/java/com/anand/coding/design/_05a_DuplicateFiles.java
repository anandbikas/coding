package com.anand.coding.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Discover DuplicateFileGroupList among all the files.
 *
 * leetcode.com/problems/find-duplicate-file-in-system
 */
public class _05a_DuplicateFiles {

    public static List<List<String>> findDuplicate(String[] paths) {

        final Map<String, List<String>> hashFileListMap = new HashMap<>();

        for(String path: paths){
            String [] split = path.split(" ");
            String dir = split[0];
            for(int i=1; i<split.length; i++){
                String s = split[i];
                String file = dir + "/" + s.substring(0, s.indexOf('('));
                String text = s.substring(s.indexOf('(')+1);

                //create hash of the file and add to hashFileListMap
                //final String fileHash = getMD5Hash(text.getBytes());
                final String fileHash = text;
                if (fileHash == null || fileHash.length() == 0) {
                    continue;
                }
                if (!hashFileListMap.containsKey(fileHash)) {
                    hashFileListMap.put(fileHash, new ArrayList<>());
                }
                hashFileListMap.get(fileHash).add(file);
            }
        }

        //Create duplicateFileGroup list
        return hashFileListMap.values().stream().filter(list -> list.size()>1).collect(Collectors.toList());
    }

    /**
     *
     * @param args
     */
    public static void main(String []args) {

        String [] input = {"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"};
        System.out.println(findDuplicate(input));
    }

}