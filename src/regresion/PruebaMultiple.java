package regresion;

public class PruebaMultiple {

    public static void main(String[] args) {
        double[] tamano = {1, 2, 3, 4, 5, 6, 7, 8};
        double[] habitaciones = {2, 3, 4, 5, 5, 6, 7, 8};
        double[] precio = {5.2, 7.8, 10.5, 13.1, 14.9, 17.8, 20.3, 23.5};

        RegresionMultiple modelo = new RegresionMultiple();

        System.out.println("Regresion Lineal Multiple");
        System.out.println("Prediccion de precios de apartamentos\n");

        System.out.println("Datos de entrenamiento:");
        System.out.printf("%-18s %-18s %-18s\n", "Tamano (decenas)", "Habitaciones", "Precio (millones)");
        for (int i = 0; i < tamano.length; i++) {
            System.out.printf("%-18.0f %-18.0f %-18.1f\n", tamano[i], habitaciones[i], precio[i]);
        }

        modelo.calcularRegresion(tamano, habitaciones, precio);
        modelo.mostrarCalculosDetallados(tamano, habitaciones, precio);

        System.out.println("\n\nResultados:");
        modelo.mostrarResultados();

        System.out.println("\nInterpretacion:");
        System.out.println("Intercepto: Un apartamento base tiene valor de $" +
                String.format("%.2f", modelo.getB0()) + " millones");
        System.out.println("Coeficiente x1: Por cada 10 m2 adicionales, el precio aumenta $" +
                String.format("%.2f", modelo.getB1()) + " millones");
        System.out.println("Coeficiente x2: Por cada habitacion adicional, el precio aumenta $" +
                String.format("%.2f", modelo.getB2()) + " millones");
        System.out.println("R^2: El modelo explica el " + String.format("%.2f", modelo.getR2() * 100) +
                "% de la variabilidad");

        System.out.println("\nPrediccion para apartamento de 90 m2 (9 decenas) con 9 habitaciones:");
        double x1_nuevo = 9;
        double x2_nuevo = 9;
        double y_pred = modelo.predecir(x1_nuevo, x2_nuevo);
        System.out.println("Precio esperado: $" + String.format("%.2f", y_pred) + " millones");
        System.out.println("Calculo: y = " + String.format("%.2f", modelo.getB0()) + " + " +
                String.format("%.2f", modelo.getB1()) + "*" + (int)x1_nuevo + " + " +
                String.format("%.2f", modelo.getB2()) + "*" + (int)x2_nuevo);
        System.out.println("y = " + String.format("%.2f", modelo.getB0()) + " + " +
                String.format("%.2f", modelo.getB1() * x1_nuevo) + " + " +
                String.format("%.2f", modelo.getB2() * x2_nuevo) + " = " +
                String.format("%.2f", y_pred));
    }
}