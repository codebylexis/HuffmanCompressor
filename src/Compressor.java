package src;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Compressor {

    private static void writeFrequencyMap(Map<Byte, Integer> freqMap, DataOutputStream out) throws IOException {
        out.writeInt(freqMap.size());
        for (Map.Entry<Byte, Integer> entry : freqMap.entrySet()) {
            out.writeByte(entry.getKey());
            out.writeInt(entry.getValue());
        }
    }

    public static void compress(String inputPath, String outputPath) throws IOException {
        byte[] fileBytes = Files.readAllBytes(new File(inputPath).toPath());

        Map<Byte, Integer> freqMap = new HashMap<>();
        for (byte b : fileBytes) {
            freqMap.put(b, freqMap.getOrDefault(b, 0) + 1);
        }

        HuffmanTree tree = new HuffmanTree(freqMap);

        StringBuilder encoded = new StringBuilder();
        for (byte b : fileBytes) {
            encoded.append(tree.encodingMap.get(b));
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int padding = 8 - (encoded.length() % 8);
        if (padding != 8) {
            for (int i = 0; i < padding; i++) encoded.append('0');
        } else {
            padding = 0;
        }

        for (int i = 0; i < encoded.length(); i += 8) {
            String byteString = encoded.substring(i, i + 8);
            bos.write(Integer.parseInt(byteString, 2));
        }

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(outputPath))) {
            writeFrequencyMap(freqMap, out);
            out.writeInt(padding);
            out.write(bos.toByteArray());
        }

        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        long originalSize = inputFile.length();
        long compressedSize = outputFile.length();

        System.out.println("Compression complete: " + outputPath);
        System.out.println("Original size: " + originalSize + " bytes");
        System.out.println("Compressed size: " + compressedSize + " bytes");

        if (originalSize > 0) {
            double ratio = (1 - ((double) compressedSize / originalSize)) * 100;
            System.out.printf("Compression ratio: %.2f%%\n", ratio);
        }
    }
}
