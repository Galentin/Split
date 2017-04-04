package Split;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;

public class SplitLauncher {
    @Option(name = "-d", metaVar = "Numbering", required = false)
    private boolean numbering;

    @Option(name = "-l", required = false)
    private boolean numberLines;

    @Option(name = "-c", required = false)
    private boolean numberSymbol;

    @Option(name = "-n", required = false)
    private boolean numberOutputFiles;

    @Argument(metaVar = "Number", required = true)
    private int number;

    @Option(name = "-o", metaVar = "NameO", required = true)
    private String outputName;

    @Argument(metaVar = "NameI", index = 1, required = true)
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
            if (numberSymbol && numberOutputFiles || numberOutputFiles && numberLines || numberLines && numberSymbol)
                System.err.println("Impossible combination");
            else {
                if (outputName.isEmpty()) outputName = "x";
                if (outputName.contentEquals("-")) outputName = inputName;
                if (numberLines) Split.numberLines(inputName, outputName, number, numbering);
                if (numberSymbol) Split.numberSymbol(inputName, outputName, number, numbering);
                if (numberOutputFiles) Split.numberOutputFiles(inputName, outputName, number, numbering);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
