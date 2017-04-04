package Split;

import java.io.*;


public class Split {

    private final String NameI;
    private final String NameO;
    private final int Number;
    private final boolean Numbering;

    public Split(String NameI, String NameO, int Number, boolean Numbering) {
        this.NameI = NameI;
        this.NameO = NameO;
        this.Number = Number;
        this.Numbering = Numbering;
    }

    public static void numberLines(String NameI, String NameO, int Number, boolean Numbering) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(NameI))) {
            StringBuilder str = new StringBuilder();
            String s = in.readLine();
            if (Number == 0) Number = 100;
            int numberlinesFile = 0;
            int numberFile = 1;
            if (Numbering) {
                while (s != null) {
                    while (numberlinesFile < Number && s != null) {
                        str.append(s).append("\n");
                        s = in.readLine();
                        numberlinesFile++;
                    }
                    String FileOutput = NameO + numberFile;
                    PrintWriter out = new PrintWriter(FileOutput);
                    out.print(str.toString());
                    out.close();
                    str.delete(0, str.length());
                    numberlinesFile = 0;
                    numberFile++;
                }
            } else {
                String ABC = "abcdefghijklmnopqrstuvwxyz";
                while (s != null) {
                    while (numberlinesFile < Number && s != null) {
                        str.append(s).append("\n");
                        s = in.readLine();
                        numberlinesFile++;
                    }
                    String FileOutput = NameO + ABC.charAt(numberFile - 1);
                    PrintWriter out = new PrintWriter(FileOutput);
                    out.print(str.toString());
                    out.close();
                    numberlinesFile = 0;
                    numberFile++;
                    str.delete(0, str.length());
                }
            }
        }
    }

    public static void numberSymbol(String NameI, String NameO, int Number, boolean Numbering) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(NameI))) {
            if (Number == 0) System.err.println("Impossible combination");
            StringBuilder str = new StringBuilder();
            int s = in.read();
            int numbersymbolFile = 0;
            int numberFile = 1;
            if (Numbering) {
                while (s != -1) {
                    while (numbersymbolFile < Number && s != -1) {
                        str.append((char) s);
                        s = in.read();
                        numbersymbolFile++;
                    }
                    String FileOutput = NameO + numberFile;
                    PrintWriter out = new PrintWriter(FileOutput);
                    out.print(str.toString());
                    out.close();
                    numbersymbolFile = 0;
                    numberFile++;
                    str.delete(0, str.length());
                }
            } else {
                String ABC = "abcdefghijklmnopqrstuvwxyz";
                while (s != -1) {
                    while (numbersymbolFile < Number && s != -1) {
                        str.append((char) s);
                        s = in.read();
                        numbersymbolFile++;
                    }
                    String FileOutput = NameO + ABC.charAt(numberFile - 1);
                    PrintWriter out = new PrintWriter(FileOutput);
                    out.print(str.toString());
                    out.close();
                    numbersymbolFile = 0;
                    numberFile++;
                    str.delete(0, str.length());
                }
            }
        }
    }

    public static void numberOutputFiles(String NameI, String NameO, int Number, boolean Numbering) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(NameI))) {
            if (Number == 0) System.err.println("Impossible combination");
            LineNumberReader linenumber = new LineNumberReader(new FileReader(NameI));
            StringBuilder str = new StringBuilder();
            String s = in.readLine();
            int numberlinesFile = 0;
            int numberFile = 1;
            int averageLines = 0;
            while (linenumber.readLine() != null) numberlinesFile++;
            numberlinesFile = (int) Math.ceil((double) numberlinesFile / Number);
            if (Numbering) {
                while (s != null) {
                    while (averageLines < numberlinesFile && s != null) {
                        str.append(s).append("\n");
                        s = in.readLine();
                        averageLines++;
                    }
                    String FileOutput = NameO + numberFile;
                    PrintWriter out = new PrintWriter(FileOutput);
                    out.print(str.toString());
                    out.close();
                    averageLines = 0;
                    numberFile++;
                    str.delete(0, str.length());
                }
            } else {
                String ABC = "abcdefghijklmnopqrstuvwxyz";
                while (s != null) {
                    while (averageLines < numberlinesFile && s != null) {
                        str.append(s).append("\n");
                        s = in.readLine();
                        averageLines++;
                    }
                    String FileOutput = NameO + ABC.charAt(numberFile - 1);
                    PrintWriter out = new PrintWriter(FileOutput);
                    out.print(str.toString());
                    out.close();
                    averageLines = 0;
                    numberFile++;
                    str.delete(0, str.length());
                }
            }
        }
    }
}
