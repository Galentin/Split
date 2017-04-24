package Split;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;

public class SplitLauncher {
    @Option(name = "-d", metaVar = "Numbering", required = false)
    private boolean numbering = false;

    @Option(name = "-l", required = false)
    private int numberLines = -1;

    @Option(name = "-c", required = false)
    private int numberSymbol = -1;

    @Option(name = "-n", required = false)
    private int numberOutputFiles = -1;

    @Option(name = "-o", metaVar = "NameO", required = false)
    private String outputName = "x";

    @Argument(metaVar = "NameI", required = true)
    private String inputName;

    public static void main(String[] args) {
        new SplitLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("split [-d] [- l num | - n num | - c num] [-o NameOutputFiles] FileName");
            parser.printUsage(System.err);
            return;
        }

        try {
            if ((numberSymbol == -1 && numberOutputFiles == -1 && numberLines != -1) || (numberOutputFiles == -1 && numberLines == -1 && numberSymbol != -1) || (numberLines == -1 && numberSymbol == -1 && numberOutputFiles != -1)) {
                if (outputName.contentEquals("-")) outputName = inputName;
                Split split = new Split(inputName, outputName, numberLines, numbering);
                if (numberLines != -1) {
                    if (numberLines == 0) numberLines = 100;
                    split.numberLines(inputName, outputName, numberLines, numbering);
                }
                if (numberSymbol != -1) {
                    if (numberSymbol == 0) throw new IllegalArgumentException();
                    split.numberSymbol(inputName, outputName, numberSymbol, numbering);
                }
                if (numberOutputFiles != -1) {
                    if (numberOutputFiles == 0) throw new IllegalArgumentException();
                    split.numberOutputFiles(inputName, outputName, numberOutputFiles, numbering);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}