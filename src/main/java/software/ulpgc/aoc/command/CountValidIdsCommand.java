package software.ulpgc.aoc.command;

import software.ulpgc.aoc.model.NumericInterval;
import java.util.List;

// Parte 1: Cuenta cuántos IDs estan dentro de los rangos
public class CountValidIdsCommand implements AnalysisCommand {
    private final List<NumericInterval> intervals;
    private final List<Long> idsToValidate;

    public CountValidIdsCommand(List<NumericInterval> intervals, List<Long> idsToValidate) {
        this.intervals = intervals;
        this.idsToValidate = idsToValidate;
    }

    @Override
    public long execute() {
        return idsToValidate.parallelStream()
                .filter(id -> intervals.stream().anyMatch(range -> range.contains(id)))
                .count();
    }
}