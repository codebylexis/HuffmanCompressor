package src;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java -cp bin src.Main compress|decompress <inputFile> <outputFile>");
            return;
        }

        String command = args[0];
        String inputPath = args[1];
        String outputPath = args[2];

        try {
            if (command.equalsIgnoreCase("compress")) {
                Compressor.compress(inputPath, outputPath);
            } else if (command.equalsIgnoreCase("decompress")) {
                Decompressor.decompress(inputPath, outputPath);
            } else {
                System.out.println("Invalid command. Use compress or decompress.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred.");
        }
    }
}
