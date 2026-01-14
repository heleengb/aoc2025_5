package software.ulpgc.aoc.view;

public class ConsoleResultPrinter implements SolutionPrinter {
    @Override
    public void display(String title, long value) {
        System.out.println(title);
        System.out.println("Resultado: " + value);
    }
}