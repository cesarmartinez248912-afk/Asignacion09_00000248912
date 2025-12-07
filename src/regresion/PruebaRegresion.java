package regresion;

public class PruebaRegresion {

    public static void main(String[] args) {
        double[] publicidad = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] ventas = {2.3, 2.9, 3.8, 4.2, 5.1, 5.8, 6.5, 7.3, 7.9, 8.6};

        RegresionLineal modelo = new RegresionLineal();

        System.out.println("Regresion Lineal Simple");
        System.out.println("Prediccion de ventas basadas en publicidad\n");

        System.out.println("Datos de entrenamiento:");
        System.out.printf("%-20s %-20s\n", "Publicidad (miles $)", "Ventas (miles $)");
        for (int i = 0; i < publicidad.length; i++) {
            System.out.printf("%-20.1f %-20.1f\n", publicidad[i], ventas[i]);
        }

        modelo.calcularRegresion(publicidad, ventas);
        modelo.mostrarCalculosDetallados(publicidad, ventas);

        System.out.println("\n\nResultados:");
        modelo.mostrarResultados();

        System.out.println("\nInterpretacion:");
        System.out.println("Intercepto: Cuando no hay publicidad, ventas base de $" +
                String.format("%.2f", modelo.getB0() * 1000));
        System.out.println("Pendiente: Por cada $1,000 en publicidad, ventas aumentan $" +
                String.format("%.2f", modelo.getB1() * 1000));
        System.out.println("R^2: El modelo explica el " + String.format("%.2f", modelo.getR2() * 100) +
                "% de la variacion");

        System.out.println("\nPrediccion para $12,000 en publicidad:");
        double x_nuevo = 12;
        double y_pred = modelo.predecir(x_nuevo);
        System.out.println("Ventas esperadas: $" + String.format("%.2f", y_pred * 1000));
        System.out.println("Calculo: y = " + String.format("%.2f", modelo.getB0()) + " + " +
                String.format("%.2f", modelo.getB1()) + " * " + x_nuevo +
                " = " + String.format("%.2f", y_pred));
    }
}