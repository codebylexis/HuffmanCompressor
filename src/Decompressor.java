package src;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Decompressor {

    private static Map<Byte, Integer> readFrequencyMap(DataInputStream in) throws IOException {
        int size = in.readInt();
        Map<Byte, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            byte b = in.readByte();
            int freq = in.readInt();
            freqMap.put(b, freq);
        }
        return freqMap;
    }

    public static void decompress(String inputPath, String outputPath) throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(inputPath))) {
            Map<Byte, Integer> freqMap = readFrequencyMap(in);
            int padding = in.readInt();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                bos.write(b);
            }
            byte[] compressedData = bos.toByteArray();

            StringBuilder bitString = new StringBuilder();
            for (byte data : compressedData) {
                bitString.append(String.format("%8s", Integer.toBinaryString(data & 0xFF)).replace(' ', '0'));
            }

            if (padding > 0) {
                bitString.setLength(bitString.length() - padding);
            }

            HuffmanTree tree = new HuffmanTree(freqMap);
            HuffmanNode node = tree.root;
            ByteArrayOutputStream originalData = new ByteArrayOutputStream();

            for (int i = 0; i < bitString.length(); i++) {
                node = bitString.charAt(i) == '0' ? node.left : node.right;
                if (node.isLeaf()) {
                    originalData.write(node.data);
                    node = tree.root;
                }
            }

            Files.write(new File(outputPath).toPath(), originalData.toByteArray());
            System.out.println("Decompression complete: " + outputPath);
        }
    }
}
