package regresion;

public class RegresionLineal {

    private double b0;
    private double b1;
    private double r2;

    public void calcularRegresion(double[] x, double[] y) {
        int n = x.length;

        double sumaX = 0;
        double sumaY = 0;
        for (int i = 0; i < n; i++) {
            sumaX += x[i];
            sumaY += y[i];
        }
        double mediaX = sumaX / n;
        double mediaY = sumaY / n;

        double numerador = 0;
        double denominador = 0;
        for (int i = 0; i < n; i++) {
            numerador += (x[i] - mediaX) * (y[i] - mediaY);
            denominador += (x[i] - mediaX) * (x[i] - mediaX);
        }
        b1 = numerador / denominador;

        b0 = mediaY - b1 * mediaX;

        double ssRes = 0;
        double ssTot = 0;
        for (int i = 0; i < n; i++) {
            double yPred = b0 + b1 * x[i];
            ssRes += (y[i] - yPred) * (y[i] - yPred);
            ssTot += (y[i] - mediaY) * (y[i] - mediaY);
        }
        r2 = 1 - (ssRes / ssTot);
    }

    public double predecir(double x) {
        return b0 + b1 * x;
    }

    public void mostrarResultados() {
        System.out.println("Ecuacion: y = " + String.format("%.2f", b0) + " + " + String.format("%.2f", b1) + "x");
        System.out.println("R^2 = " + String.format("%.4f", r2));
    }

    public void mostrarCalculosDetallados(double[] x, double[] y) {
        int n = x.length;

        System.out.println("\nPaso 1: Calcular las medias");

        double sumaX = 0;
        double sumaY = 0;
        for (int i = 0; i < n; i++) {
            sumaX += x[i];
            sumaY += y[i];
        }
        double mediaX = sumaX / n;
        double mediaY = sumaY / n;

        System.out.println("Suma de x: " + sumaX);
        System.out.println("Suma de y: " + sumaY);
        System.out.println("Media de x: " + String.format("%.4f", mediaX));
        System.out.println("Media de y: " + String.format("%.4f", mediaY));

        System.out.println("\nPaso 2: Calcular la pendiente (b1)");
        System.out.println("Tabla:");
        System.out.printf("%-5s %-8s %-8s %-12s %-12s %-15s %-15s\n",
                "i", "x", "y", "(x-media_x)", "(y-media_y)", "(x-mx)*(y-my)", "(x-mx)^2");

        double numerador = 0;
        double denominador = 0;
        for (int i = 0; i < n; i++) {
            double difX = x[i] - mediaX;
            double difY = y[i] - mediaY;
            double prod = difX * difY;
            double cuadX = difX * difX;

            numerador += prod;
            denominador += cuadX;

            System.out.printf("%-5d %-8.1f %-8.1f %-12.4f %-12.4f %-15.4f %-15.4f\n",
                    i+1, x[i], y[i], difX, difY, prod, cuadX);
        }

        System.out.println("\nNumerador: " + String.format("%.4f", numerador));
        System.out.println("Denominador: " + String.format("%.4f", denominador));
        System.out.println("b1 = " + String.format("%.4f", b1));

        System.out.println("\nPaso 3: Calcular el intercepto (b0)");
        System.out.println("b0 = media_y - b1 * media_x");
        System.out.println("b0 = " + String.format("%.4f", mediaY) + " - " +
                String.format("%.4f", b1) + " * " + String.format("%.4f", mediaX));
        System.out.println("b0 = " + String.format("%.4f", b0));

        System.out.println("\nPaso 4: Calcular R^2");
        System.out.println("Tabla:");
        System.out.printf("%-5s %-8s %-8s %-12s %-15s %-15s\n",
                "i", "x", "y", "y_pred", "(y-y_pred)^2", "(y-media_y)^2");

        double ssRes = 0;
        double ssTot = 0;
        for (int i = 0; i < n; i++) {
            double yPred = b0 + b1 * x[i];
            double resid = (y[i] - yPred) * (y[i] - yPred);
            double total = (y[i] - mediaY) * (y[i] - mediaY);

            ssRes += resid;
            ssTot += total;

            System.out.printf("%-5d %-8.1f %-8.1f %-12.4f %-15.4f %-15.4f\n",
                    i+1, x[i], y[i], yPred, resid, total);
        }

        System.out.println("\nSS_res: " + String.format("%.4f", ssRes));
        System.out.println("SS_tot: " + String.format("%.4f", ssTot));
        System.out.println("R^2 = 1 - (SS_res / SS_tot)");
        System.out.println("R^2 = " + String.format("%.4f", r2));
    }

    public double getB0() {
        return b0;
    }

    public double getB1() {
        return b1;
    }

    public double getR2() {
        return r2;
    }
}