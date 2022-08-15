package com.anand.coding.design;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Discover DuplicateFileGroupList among all the files in a directory recursively.
 */
public class _05_DuplicateFiles {

    /**
     *
     * @param directoryPath
     * @param depth
     * @return
     */
    public List<DuplicateFileGroup> findDuplicates(final String directoryPath, int depth) {
        File file = new File(directoryPath);

        if(!(file.exists() || file.isDirectory())){
            System.out.println("WARN: Provided path doesn't exist or is not a directory!");
            return new ArrayList<>();
        }

        final Map<String, List<File>> hashFileListMap = new HashMap<>();
        processDirectory(file, 0, depth, hashFileListMap, new HashSet<>());

        //Create duplicateFileGroup list
        return hashFileListMap.values().stream().filter(list -> list.size()>1)
                .map(DuplicateFileGroup::new).collect(Collectors.toList());

    }

    /**
     *
     * @param file
     * @param currentDepth
     * @param depth
     * @param hashFileListMap
     * @param processed
     */
    private void processDirectory(File file,
                                  final int currentDepth,
                                  final int depth,
                                  final Map<String, List<File>> hashFileListMap,
                                  final Set<String> processed){

        if(currentDepth>depth){
            return;
        }

        File childFile = null;
        try {

            //For symbolic link and loop avoidance, use real path.
            file = file.toPath().toRealPath().toFile();
            final String absPath = file.getAbsolutePath();

            if (processed.contains(absPath)) {
                return;
            }
            processed.add(absPath);

            String[] directoryListing = file.list();
            if (directoryListing == null) {
                return;
            }
			
			System.out.println("INFO: Processing directory: " + absPath + "...");
            for (String name : directoryListing) {

                childFile = new File(String.format("%s/%s", absPath, name));

                if (childFile.isDirectory()) {
                    processDirectory(childFile, currentDepth+1, depth, hashFileListMap, processed);
                } else {
                    //create hash of the childFile and add to hashFileListMap
                    final String fileHash = getMD5Hash(childFile);
                    if (fileHash == null || fileHash.length() == 0) {
                        continue;
                    }
                    if (!hashFileListMap.containsKey(fileHash)) {
                        hashFileListMap.put(fileHash, new ArrayList<>());
                    }
                    hashFileListMap.get(fileHash).add(childFile);
                }
            }
        } catch (Exception e){
            System.out.printf("ERROR: error occurred while processing directory: %s, file: %s%n",
                    file.getAbsolutePath(), childFile == null ? null : childFile.getName());
        }
    }

    /**
     * MD5 Hash/checksum
     *
     * @param file
     * @return
     */
    private String getMD5Hash(final File file) throws NoSuchAlgorithmException {

        if(file.length()==0){
            return null;
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        //MessageDigest md = MessageDigest.getInstance("SHA-1");

        byte[] byteArray = new byte[1024];
        int bytesCount;

        try {
            //Read file data and update in message digest
            FileInputStream fis = new FileInputStream(file);
            while ((bytesCount = fis.read(byteArray)) != -1) {
                md.update(byteArray, 0, bytesCount);
            }
            fis.close();
        } catch (IOException ioe){
            System.out.println("WARN: error accessing file" + file.getAbsolutePath());
            return null;
        }

        //Get the hash's bytes
        byte[] bytes = md.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(byte aByte: bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }

    /**
     *
     */
    private static class DuplicateFileGroup {
        List<File> files;

        public DuplicateFileGroup(List<File> files) {
            this.files = files;
        }

        @Override
        public String toString() {
            return files.toString();
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String []args) {

        final String directoryPath = args.length >0 ? args[0].trim() : "/tmp/bikas";
        final int depth = args.length >1 ? Integer.parseInt(args[1].trim()) : 10;

        _05_DuplicateFiles duplicateFiles = new _05_DuplicateFiles();

        System.out.println(duplicateFiles.findDuplicates(directoryPath, depth).toString()
                .replace(", ", ",\n")
                .replace("[", "[\n")
                .replace("]", "\n]"));
    }


    /**
     *
     * @param files
     * @return
     * @throws Exception
     */
    public List<DuplicateFileGroup> findDuplicates(List<File> files)  throws NoSuchAlgorithmException{

        Map<String, List<File>> hashFileListMap = new HashMap<>();

        //create hash of the files
        for(File file : files){
            final String fileHash = getMD5Hash(file);
            if(fileHash == null || fileHash.length()==0){
                continue;
            }
            if(!hashFileListMap.containsKey(fileHash)){
                hashFileListMap.put(fileHash, new ArrayList<>());
            }
            hashFileListMap.get(fileHash).add(file);
        }

        return hashFileListMap.values().stream().filter(list -> list.size()>1)
                .map(DuplicateFileGroup::new).collect(Collectors.toList());
    }
}