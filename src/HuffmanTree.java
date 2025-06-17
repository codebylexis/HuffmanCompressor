package src;

import java.util.*;

public class HuffmanTree {
    public HuffmanNode root;
    public Map<Byte, String> encodingMap = new HashMap<>();

    public HuffmanTree(Map<Byte, Integer> freqMap) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> entry : freqMap.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode parent = new HuffmanNode(left, right);
            queue.add(parent);
        }

        root = queue.poll();
        buildEncodingMap(root, "");
    }

    private void buildEncodingMap(HuffmanNode node, String code) {
        if (node.isLeaf()) {
            encodingMap.put(node.data, code);
            return;
        }
        buildEncodingMap(node.left, code + "0");
        buildEncodingMap(node.right, code + "1");
    }

    public HuffmanTree(Map<Byte, Integer> freqMap, boolean rebuild) {
        this(freqMap); // Alternate constructor
    }
}
