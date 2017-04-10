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
    private boolean numberLines;

    @Option(name = "-c", required = false)
    private boolean numberSymbol;

    @Option(name = "-n", required = false)
    private boolean numberOutputFiles;

    @Argument(metaVar = "Number", required = true)
    private int number;

    @Option(name = "-o", metaVar = "NameO", required = false)
    private String outputName = "x";

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
                if (outputName.contentEquals("-")) outputName = inputName;
                Split split = new Split(inputName, outputName, number, numbering);

                if (numberLines) {
                    if (number == 0) number = 100;
                    split.numberLines(inputName, outputName, number, numbering);
                }
                if (numberSymbol) split.numberSymbol(inputName, outputName, number, numbering);
                if (numberOutputFiles) split.numberOutputFiles(inputName, outputName, number, numbering);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
