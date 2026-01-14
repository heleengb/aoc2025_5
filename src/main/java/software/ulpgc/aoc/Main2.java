package software.ulpgc.aoc;

import software.ulpgc.aoc.controller.FrescoController;
import software.ulpgc.aoc.io.TextFileReader;
import software.ulpgc.aoc.view.ConsoleResultPrinter;

import java.io.IOException;
import java.nio.file.Path;

public class Main2 {
    // Archivo con formato: Lista de rangos
    private static final Path INPUT_PATH = Path.of("src", "main", "resources", "frescos.txt");

    public static void main(String[] args) {
        try {
            // IO
            var rawData = new TextFileReader(INPUT_PATH).readContent();

            // CONTROLLER
            long result = new FrescoController().processTotalCoverage(rawData);

            // VIEW
            new ConsoleResultPrinter().display("Cobertura Total", result);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}