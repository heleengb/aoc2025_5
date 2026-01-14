package software.ulpgc.aoc.command;

import software.ulpgc.aoc.model.NumericInterval;
import java.util.ArrayList;
import java.util.List;

// Parte 2: Ordena, fusiona y suma la cobertura total
public class CalculateCoverageCommand implements AnalysisCommand {
    private final List<NumericInterval> rawIntervals;

    public CalculateCoverageCommand(List<NumericInterval> rawIntervals) {
        this.rawIntervals = rawIntervals;
    }

    @Override
    public long execute() {
        if (rawIntervals.isEmpty()) return 0;

        List<NumericInterval> merged = rawIntervals.stream()
                .sorted()
                .reduce(new ArrayList<>(), (acc, next) -> {
                    if (acc.isEmpty()) {
                        acc.add(next);
                    } else {
                        int lastIdx = acc.size() - 1;
                        NumericInterval last = acc.get(lastIdx);
                        if (last.canMergeWith(next)) {
                            acc.set(lastIdx, last.merge(next));
                        } else {
                            acc.add(next);
                        }
                    }
                    return acc;
                }, (a, b) -> a); // Combiner requerido por reduce; no se usa porque el stream es secuencial


        // Sumar longitudes
        return merged.stream().mapToLong(NumericInterval::size).sum();
    }
}