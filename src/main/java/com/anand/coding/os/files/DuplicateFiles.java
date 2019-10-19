package com.anand.coding.os.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Discover DuplicateFileGroupList among all the files in a directory recursively.
 */
public class DuplicateFiles {

    private File file;
    private int depth;

    private Map<String, List<File>> hashFileListMap = new HashMap<>();
    private List<DuplicateFileGroup> dfgList= new ArrayList<>();

    private Set<String> processedDirectorySet = new HashSet<>();

    /**
     *
     * @param directoryPath
     */
    public DuplicateFiles(final String directoryPath, int depth) {
        this.depth = depth;
        this.file = new File(directoryPath);

        if(!(file.exists() || file.isDirectory())){
            System.out.println("WARN: Provided path doesn't exist or is not a directory!");
            return;
        }
        processDirectory(file, 0);
        calculateDuplicateFileGroupList();
    }

    /**
     *
     * @return
     */
    public List<DuplicateFileGroup> getDfgList() {
        return dfgList;
    }

    /**
     *
     * @param file
     * @param currentDepth
     */
    private void processDirectory(File file, int currentDepth){
		

        File childFile = null;
        try {
            //For symbolic link and loop avoidance, use real path.
            file = file.toPath().toRealPath().toFile();
            if (processedDirectorySet.contains(file.getAbsolutePath())) {
                return;
            }
            processedDirectorySet.add(file.getAbsolutePath());

            if(currentDepth>depth){
                return;
            }

            String[] directoryListing = file.list();
            if (directoryListing == null) {
                return;
            }
			
			System.out.println("Processing directory: " + file.getAbsolutePath() + "...");
            for (String name : directoryListing) {

                childFile = new File(String.format("%s/%s", file.getAbsolutePath(),name));

                if (childFile.isDirectory()) {
                    processDirectory(childFile, currentDepth+1);
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
            System.out.println(String.format("ERROR: error occurred while processing directory: %s, file: %s",
                    file.getAbsolutePath(), childFile == null? null : childFile.getName()));
        }
    }

    /**
     *
     * @return
     */
    public void calculateDuplicateFileGroupList(){

        //Create duplicateFileGroup list
        for(String fileHash: hashFileListMap.keySet()){
            if(hashFileListMap.get(fileHash).size()>1) {
                DuplicateFileGroup dfg = new DuplicateFileGroup();
                dfg.files = hashFileListMap.get(fileHash);
                dfgList.add(dfg);
            }
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
        int bytesCount = 0;

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
    private class DuplicateFileGroup {
        List<File> files;

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
        final int depth = args.length >1 ? Integer.valueOf(args[1].trim()) : 10;

        DuplicateFiles duplicateFiles = new DuplicateFiles(directoryPath, depth);

        System.out.println(duplicateFiles.getDfgList().toString()
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

        //Create duplicateFileGroup list
        List<DuplicateFileGroup> dfgList = new ArrayList<>();
        for(String fileHash: hashFileListMap.keySet()){
            if(hashFileListMap.get(fileHash).size()>1) {
                DuplicateFileGroup dfg = new DuplicateFileGroup();
                dfg.files = hashFileListMap.get(fileHash);
                dfgList.add(dfg);
            }
        }

        return dfgList;
    }
}