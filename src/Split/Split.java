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
        String fileOutput = nameO;
        if (numbering) {
            fileOutput = fileOutput + number;
        } else {
            String ABC = "abcdefghijklmnopqrstuvwxyz";
            int i = 1;
            int Number = number;
            while (Number / 676.00 > 1) {
                Number = Number / 26;
                i++;
            }
            for (int j = i; j >= 0; j--) {
                fileOutput = fileOutput + ABC.charAt(((int) Math.ceil(number / Math.pow(26, j))) - 1);
                number = number - (int) Math.pow(26, j) * (int) Math.floor(number / Math.pow(26, j));
                if (number == 0) number = 26;
            }
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
        LineNumberReader linenumber = new LineNumberReader(new FileReader(nameI));
        int numberlinesFile = 0;
        while (linenumber.readLine() != null) numberlinesFile++;
        numberlinesFile = (int) Math.ceil((double) numberlinesFile / number);
        numberLines(nameI, nameO, numberlinesFile, numbering);
    }
}