/*
* n64SaveConverter.java
* Main core code which converts between different kinds of n64 save files.
* Currently ignores types of saves except those associated with n64.
* Written By Daniel Falk (with some edits from Andrew Gorbaty)
* */

package com.example.n64saveconverter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class N64SaveConverter {

    private static final int SIZE_EEP = 16384;
    private static final int SIZE_SRA = 32768;
    private static final int SIZE_FLA = 131072;
    private static final int SIZE_MPK = 131072;
    private static final int SIZE_SRM = 296960;
    private static final int SIZE_SRM_SRA_OFFSET = 133120;
    private static final int SIZE_SRM_MPK_OFFSET = 2048;
    private static final String EEP_EXT = ".eep";
    private static final String SRA_EXT = ".sra";
    private static final String FLA_EXT = ".fla";
    private static final String MPK_EXT = ".mpk";
    private static final String SRM_EXT = ".srm";
    private static final String REGEX = ".*\\.(eep|sra|fla|mpk|srm)";

    public Boolean ConvertFiles(String inDirectoryPath) {

        Boolean bConvertedAnyFiles = false;
        List<String> filenames = searchFiles(inDirectoryPath);
        for(String filepath : filenames) {
            if(filepath.endsWith(EEP_EXT)) {
                byte[] eep = readBytes(filepath);
                if (eep != null){
                    eep = bytePad(eep, 0, SIZE_EEP - eep.length);
                    bConvertedAnyFiles |= writeBytes(eep, newFilename(filepath, EEP_EXT));
                    bConvertedAnyFiles |= writeBytes(bytePad(eep, 0, SIZE_SRM - SIZE_EEP), newFilename(filepath, SRM_EXT));
                }
            }

            if(filepath.endsWith(SRA_EXT)) {
                byte[] sra = readBytes(filepath);
                if (sra != null){
                    sra = bytePad(sra, 0, SIZE_SRA - sra.length);
                    bConvertedAnyFiles |= writeBytes(byteSwap(sra), newFilename(filepath, SRA_EXT));
                    bConvertedAnyFiles |= writeBytes(bytePad(sra, SIZE_SRM_SRA_OFFSET, SIZE_SRM - SIZE_SRA - SIZE_SRM_SRA_OFFSET), newFilename(filepath, SRM_EXT));
                }
            }

            if(filepath.endsWith(FLA_EXT)) {
                byte[] fla = readBytes(filepath);
                if (fla != null){
                    fla = bytePad(fla, 0, SIZE_FLA - fla.length);
                    bConvertedAnyFiles |= writeBytes(byteSwap(fla), newFilename(filepath, FLA_EXT));
                    bConvertedAnyFiles |= writeBytes(bytePad(fla, SIZE_SRM - SIZE_FLA, 0), newFilename(filepath, SRM_EXT));
                }
            }

            if(filepath.endsWith(MPK_EXT)) {
                byte[] mpk = readBytes(filepath);
                if (mpk != null){
                    mpk = bytePad(mpk, 0, SIZE_MPK - mpk.length);
                    bConvertedAnyFiles |= writeBytes(mpk, newFilename(filepath, MPK_EXT));
                    bConvertedAnyFiles |= writeBytes(bytePad(mpk, SIZE_SRM_MPK_OFFSET, SIZE_SRM - SIZE_MPK - SIZE_SRM_MPK_OFFSET), newFilename(filepath, SRM_EXT));
                }
            }

            if(filepath.endsWith(SRM_EXT)) {
                byte[] srm = readBytes(filepath);
                if (srm != null){
                    bConvertedAnyFiles |= writeBytes(byteRemove(srm, 0, SIZE_SRM - SIZE_EEP), newFilename(filepath, EEP_EXT));
                    bConvertedAnyFiles |= writeBytes(byteRemove(srm, SIZE_SRM_SRA_OFFSET, SIZE_SRM - SIZE_SRA - SIZE_SRM_SRA_OFFSET), newFilename(filepath, SRA_EXT));
                    bConvertedAnyFiles |= writeBytes(byteRemove(srm, SIZE_SRM - SIZE_FLA, 0), newFilename(filepath, FLA_EXT));
                    bConvertedAnyFiles |= writeBytes(byteRemove(srm, SIZE_SRM_MPK_OFFSET, SIZE_SRM - SIZE_MPK - SIZE_SRM_MPK_OFFSET), newFilename(filepath, MPK_EXT));
                }
            }
        }
        return bConvertedAnyFiles;
    }

    private static List<String> searchFiles(String inDirectoryPath) {
        List<String> filenames = new ArrayList<>();
        for(File file : new File(inDirectoryPath).listFiles()) {
            if (file.isFile()) {
                if (file.getName().matches(REGEX)) {
                    filenames.add(file.getAbsolutePath());
                }
            }
        }
        return filenames;
    }

    private static String newFilename(String filename, String extension) {
        return filename.substring(0, filename.lastIndexOf(".")) + "#" + extension;
    }

    private static Boolean writeBytes(byte[] output, String location) {
        Boolean bOutWroteBytes = false;
        if (output != null) {
            try {
                Path path = Paths.get(location);
                Files.write(path, output);
                bOutWroteBytes = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return bOutWroteBytes;
    }

    private static byte[] readBytes(String location) {
        byte[] fileBytes = null;
        try
        {
            File file = new File(location);
            fileBytes = Files.readAllBytes(file.toPath());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return fileBytes;
    }

    private static byte[] bytePad(byte[] input, int frontPad, int backPad) {
        byte[] output = new byte[input.length + frontPad + backPad];
        for(int i = 0; i < output.length; i++) {
            if (i < frontPad) {
                output[i] = 0;
            } else if (i < input.length + frontPad) {
                output[i] = input[i - frontPad];
            } else {
                output[i] = 0;
            }
        }
        return output;
    }

    private static byte[] byteRemove(byte[] input, int frontRemove, int backRemove) {
        byte[] output = null;
        // If this check isn't here, sometimes crashes will happen because of IndexArrayOutOfBounds issues.
        if (input.length - frontRemove - backRemove > 0) {
            output = new byte[input.length - frontRemove - backRemove];
            for (int i = 0; i < output.length; i++) {
                output[i] = input[i + frontRemove];
            }
        }
        return output;
    }

    private static byte[] byteSwap(byte[] input) {
        byte[] output = new byte[input.length];
        for(int i = 0; i < input.length; i++) {
            if(i < input.length) {
                if(i % 4 == 0) {
                    if(i + 3 < input.length) {
                        output[i] = input[i + 3];
                        output[i + 1] = input[i + 2];
                        output[i + 2] = input[i + 1];
                        output[i + 3] = input[i];
                    } else if(i + 2 < input.length) {
                        output[i] = input[i + 2];
                        output[i + 1] = input[i + 1];
                        output[i + 2] = input[i];
                    } else if(i + 1 < input.length) {
                        output[i] = input[i + 1];
                        output[i + 1] = input[i];
                    } else {
                        output[i] = input[i];
                    }
                }
            }
        }
        return output;
    }
}


