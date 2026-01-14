# Reto de Análisis de Intervalos (Frescos) - 1.0

**Arquitectura, estilo MVC (Model-View-Controller) con Patrón Command:**
El proyecto está organizado para separar la lógica matemática de la gestión de flujo y la presentación. En el paquete **model**, `NumericInterval` (implementado como un Record) encapsula la lógica de conjuntos (pertenencia, fusión y tamaño). En el paquete **view**, `ConsoleResultPrinter` se encarga exclusivamente de mostrar los resultados. En **controller**, `FrescoController` actúa como orquestador: procesa el texto crudo, identifica qué partes son rangos y qué partes son IDs, y delega el análisis al comando correspondiente.

**Principios aplicados:**
* **Responsabilidad Única (SRP):** Cada componente tiene un propósito único. `TextFileReader` solo lee archivos, `NumericInterval` solo gestiona lógica de intervalos, y `CalculateCoverageCommand` solo sabe fusionar y sumar rangos.
* **Inversión de Dependencias (DIP):** El código depende de abstracciones como `InputReader`, `SolutionPrinter` y la interfaz `AnalysisCommand`. Esto permite, por ejemplo, cambiar la lógica de análisis sin tocar el controlador, o cambiar la fuente de datos sin tocar la lógica de negocio.
* **Abierto-Cerrado (OCP):** El sistema es extensible. Si se requiere un nuevo tipo de análisis (ej. "Encontrar huecos vacíos entre rangos"), se puede implementar una nueva clase `FindGapsCommand` que cumpla con `AnalysisCommand` sin modificar el código existente en `NumericInterval` o en otros comandos.

**Extras:**
* **Patrón Command:** Se utiliza para encapsular las lógicas complejas de "Contar IDs válidos" y "Calcular Cobertura", permitiendo que el controlador sea ligero.
* **Java Records:** Uso de `NumericInterval` para definir objetos de valor inmutables y comparables (`Comparable<NumericInterval>`).
* **Algoritmo de Fusión de Intervalos:** Implementación eficiente en `CalculateCoverageCommand` utilizando Streams y `reduce` para ordenar y combinar rangos solapados en una sola pasada.
* **Streams y Programación Funcional:** Uso intensivo de `stream()`, `filter`, `map`, `reduce` y `parallelStream` para procesar listas de datos de manera declarativa y eficiente.
