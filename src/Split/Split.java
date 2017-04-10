package Split;

import java.io.*;

public class Split implements NumberFiles {
    private final String nameI;
    private final String nameO;
    private final int number;
    private final boolean numbering;

    public Split(String nameI, String nameO, int number, boolean numbering) {
        this.nameI = nameI;
        this.nameO = nameO;
        this.number = number;
        this.numbering = numbering;
    }

    @Override
    public String number(boolean numbering, int number, String nameO) {
        String fileOutput;
        if (numbering) {
            fileOutput = nameO + number;
        } else {
            String ABC = "abcdefghijklmnopqrstuvwxyz";
            fileOutput = nameO + ABC.charAt(number - 1);
        }
        return fileOutput;
    }

    public void numberLines(String nameI, String nameO, int number, boolean numbering) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(nameI))) {
            int numberlinesFile = 0;
            int numberFile = 1;
            String s = in.readLine();
            while (s != null) {
                StringBuilder str = new StringBuilder();
                while (numberlinesFile < number && s != null) {
                    str.append(s).append("\n");
                    s = in.readLine();
                    numberlinesFile++;
                }
                PrintWriter out = new PrintWriter(number(numbering, numberFile, nameO));
                out.print(str.toString());
                out.close();
                numberlinesFile = 0;
                numberFile++;
            }
        }
    }

    public void numberSymbol(String nameI, String nameO, int number, boolean numbering) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(nameI))) {
            if (number == 0) System.err.println("Impossible combination");
            int s = in.read();
            int numbersymbolFile = 0;
            int numberFile = 1;
            while (s != -1) {
                StringBuilder str = new StringBuilder();
                while (numbersymbolFile < number && s != -1) {
                    str.append((char) s);
                    s = in.read();
                    numbersymbolFile++;
                }
                PrintWriter out = new PrintWriter(number(numbering, numberFile, nameO));
                out.print(str.toString());
                out.close();
                numbersymbolFile = 0;
                numberFile++;
            }
        }
    }

    public void numberOutputFiles(String nameI, String nameO, int number, boolean numbering) throws IOException {
        if (number == 0) System.err.println("Impossible combination");
        LineNumberReader linenumber = new LineNumberReader(new FileReader(nameI));
        int numberlinesFile = 0;
        while (linenumber.readLine() != null) numberlinesFile++;
        numberlinesFile = (int) Math.ceil((double) numberlinesFile / number);
        numberLines(nameI, nameO, numberlinesFile, numbering);
    }
}
