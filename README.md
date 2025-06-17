# Huffman File Compressor (Java)

A simple command-line tool to compress and decompress files using Huffman encoding.

## 🚀 Features
- Compresses text files using Huffman coding
- Decompresses back to original
- Shows compression ratio
- Written in pure Java

## 🧱 Project Structure
```
HuffmanCompressor/
├── src/              # Java source files
├── bin/              # Compiled .class files (created on compile)
├── input.txt         # Example input
├── output.huff       # Compressed binary
├── recovered.txt     # Decompressed result
```

## 🧪 How to Use

### 1. Compile
```bash
javac -d bin src/*.java
```

### 2. Compress
```bash
java -cp bin src.Main compress input.txt output.huff
```

### 3. Decompress
```bash
java -cp bin src.Main decompress output.huff recovered.txt
```

## ✅ Notes
- Make sure `input.txt` exists before compressing
- `recovered.txt` should exactly match the original

## 🛠 Java Version
Tested with Java 17+