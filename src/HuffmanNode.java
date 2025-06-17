package src;

public class HuffmanNode implements Comparable<HuffmanNode> {
    public int frequency;
    public byte data;
    public HuffmanNode left, right;

    public HuffmanNode(byte data, int frequency) {
        this.data = data;
        this.frequency = frequency;
    }

    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.left = left;
        this.right = right;
        this.frequency = left.frequency + right.frequency;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}
