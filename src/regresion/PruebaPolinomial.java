package regresion;

public class PruebaPolinomial {

    public static void main(String[] args) {
        double[] tiempo = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        double[] altura = {1.0, 2.2, 5.8, 11.5, 19.1, 28.9, 40.8, 54.6, 70.5};

        RegresionPolinomial modelo = new RegresionPolinomial();

        System.out.println("Regresion Polinomial de Grado 2");
        System.out.println("Crecimiento de planta en funcion del tiempo\n");

        System.out.println("Datos de entrenamiento:");
        System.out.printf("%-15s %-15s\n", "Tiempo (sem)", "Altura (cm)");
        for (int i = 0; i < tiempo.length; i++) {
            System.out.printf("%-15.0f %-15.1f\n", tiempo[i], altura[i]);
        }

        modelo.calcularRegresion(tiempo, altura);
        modelo.mostrarCalculosDetallados(tiempo, altura);

        System.out.println("\n\nResultados finales:");
        modelo.mostrarResultados();

        System.out.println("\nInterpretacion:");
        System.out.println("a = " + String.format("%.2f", modelo.getA()) +
                ": Altura inicial (semana 0) es " + String.format("%.2f", modelo.getA()) + " cm");
        System.out.println("b = " + String.format("%.2f", modelo.getB()) +
                ": Crecimiento lineal de " + String.format("%.2f", modelo.getB()) + " cm/semana");
        System.out.println("c = " + String.format("%.2f", modelo.getC()) +
                ": Aceleracion cuadratica del crecimiento");
        System.out.println("\nR^2 = " + String.format("%.2f", modelo.getR2() * 100) +
                "%: El modelo explica casi toda la variabilidad");

        System.out.println("\nPrediccion para semana 9:");
        double x_nuevo = 9;
        double y_pred = modelo.predecir(x_nuevo);
        System.out.println("Altura esperada: " + String.format("%.2f", y_pred) + " cm");
        System.out.println("Calculo: y = " + String.format("%.2f", modelo.getA()) + " + " +
                String.format("%.2f", modelo.getB()) + "*" + (int)x_nuevo + " + " +
                String.format("%.2f", modelo.getC()) + "*" + (int)x_nuevo + "^2");
        System.out.println("y = " + String.format("%.2f", modelo.getA()) + " + " +
                String.format("%.2f", modelo.getB() * x_nuevo) + " + " +
                String.format("%.2f", modelo.getC() * x_nuevo * x_nuevo) + " = " +
                String.format("%.2f", y_pred));
    }
}