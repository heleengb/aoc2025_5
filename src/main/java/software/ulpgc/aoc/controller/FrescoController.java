package software.ulpgc.aoc.controller;

import software.ulpgc.aoc.command.*;
import software.ulpgc.aoc.model.NumericInterval;

import java.util.List;
import java.util.stream.Collectors;

public class FrescoController {

    // Lógica para Parte 1
    public long processValidIds(List<String> rawLines) {
        int splitIndex = rawLines.indexOf("");
        if (splitIndex == -1) throw new IllegalArgumentException("Formato inválido: falta línea vacía");

        // Parseamos Rangos
        List<NumericInterval> ranges = rawLines.subList(0, splitIndex).stream()
                .map(this::parseInterval)
                .collect(Collectors.toList());

        // Parseamos IDs
        List<Long> ids = rawLines.subList(splitIndex + 1, rawLines.size()).stream()
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .collect(Collectors.toList());

        return new CountValidIdsCommand(ranges, ids).execute();
    }

    // Lógica para Parte 2
    public long processTotalCoverage(List<String> rawLines) {
        List<NumericInterval> ranges = rawLines.stream()
                .filter(line -> line.contains("-")) // Filtramos solo líneas con formato de rango
                .map(this::parseInterval)
                .collect(Collectors.toList());

        return new CalculateCoverageCommand(ranges).execute();
    }

    // convertir String en objeto intevalo
    private NumericInterval parseInterval(String line) {
        String[] parts = line.split("-");
        return new NumericInterval(
                Long.parseLong(parts[0].trim()),
                Long.parseLong(parts[1].trim())
        );
    }
}