package software.ulpgc.aoc.model;

public record NumericInterval(long start, long end) implements Comparable<NumericInterval> {

    // Lógica Parte 1: Verificar contenido
    public boolean contains(long number) {
        return number >= start && number <= end;
    }

    // Lógica Parte 2: Longitud del rango
    public long size() {
        return (end - start) + 1;
    }

    // Lógica Parte 2: Verificar si es fusionable
    public boolean canMergeWith(NumericInterval other) {
        return other.start <= this.end + 1;
    }

    // Lógica Parte 2: Crear nuevo intervalo fusionado
    public NumericInterval merge(NumericInterval other) {
        return new NumericInterval(this.start, Math.max(this.end, other.end));
    }

    @Override
    public int compareTo(NumericInterval other) {
        return Long.compare(this.start, other.start);
    }
}