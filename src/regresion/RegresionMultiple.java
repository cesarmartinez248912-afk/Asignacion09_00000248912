package regresion;

public class RegresionMultiple {

    private double b0;
    private double b1;
    private double b2;
    private double r2;

    public void calcularRegresion(double[] x1, double[] x2, double[] y) {
        int n = x1.length;

        double sumaX1 = 0;
        double sumaX2 = 0;
        double sumaY = 0;
        for (int i = 0; i < n; i++) {
            sumaX1 += x1[i];
            sumaX2 += x2[i];
            sumaY += y[i];
        }
        double mediaX1 = sumaX1 / n;
        double mediaX2 = sumaX2 / n;
        double mediaY = sumaY / n;

        double sx1x1 = 0;
        double sx2x2 = 0;
        double sx1x2 = 0;
        double sx1y = 0;
        double sx2y = 0;

        for (int i = 0; i < n; i++) {
            double difX1 = x1[i] - mediaX1;
            double difX2 = x2[i] - mediaX2;
            double difY = y[i] - mediaY;

            sx1x1 += difX1 * difX1;
            sx2x2 += difX2 * difX2;
            sx1x2 += difX1 * difX2;
            sx1y += difX1 * difY;
            sx2y += difX2 * difY;
        }

        double D = sx1x1 * sx2x2 - sx1x2 * sx1x2;

        b1 = (sx2x2 * sx1y - sx1x2 * sx2y) / D;
        b2 = (sx1x1 * sx2y - sx1x2 * sx1y) / D;
        b0 = mediaY - b1 * mediaX1 - b2 * mediaX2;

        double ssRes = 0;
        double ssTot = 0;
        for (int i = 0; i < n; i++) {
            double yPred = b0 + b1 * x1[i] + b2 * x2[i];
            ssRes += (y[i] - yPred) * (y[i] - yPred);
            ssTot += (y[i] - mediaY) * (y[i] - mediaY);
        }

        r2 = 1 - (ssRes / ssTot);
    }

    public double predecir(double x1, double x2) {
        return b0 + b1 * x1 + b2 * x2;
    }

    public void mostrarResultados() {
        System.out.println("Ecuacion: y = " + String.format("%.2f", b0) + " + " +
                String.format("%.2f", b1) + "x1 + " + String.format("%.2f", b2) + "x2");
        System.out.println("R^2 = " + String.format("%.4f", r2));
    }

    public void mostrarCalculosDetallados(double[] x1, double[] x2, double[] y) {
        int n = x1.length;

        System.out.println("\nPaso 1: Calcular las medias");

        double sumaX1 = 0;
        double sumaX2 = 0;
        double sumaY = 0;
        for (int i = 0; i < n; i++) {
            sumaX1 += x1[i];
            sumaX2 += x2[i];
            sumaY += y[i];
        }
        double mediaX1 = sumaX1 / n;
        double mediaX2 = sumaX2 / n;
        double mediaY = sumaY / n;

        System.out.println("Media de x1: " + String.format("%.4f", mediaX1));
        System.out.println("Media de x2: " + String.format("%.4f", mediaX2));
        System.out.println("Media de y: " + String.format("%.4f", mediaY));

        System.out.println("\nPaso 2: Calcular desviaciones y productos");
        System.out.printf("%-5s %-8s %-8s %-8s %-12s %-12s %-12s %-15s %-15s\n",
                "i", "x1", "x2", "y", "(x1-mx1)", "(x2-mx2)", "(y-my)",
                "(x1-mx1)^2", "(x2-mx2)^2");

        double sx1x1 = 0;
        double sx2x2 = 0;
        double sx1x2 = 0;
        double sx1y = 0;
        double sx2y = 0;

        for (int i = 0; i < n; i++) {
            double difX1 = x1[i] - mediaX1;
            double difX2 = x2[i] - mediaX2;
            double difY = y[i] - mediaY;

            sx1x1 += difX1 * difX1;
            sx2x2 += difX2 * difX2;
            sx1x2 += difX1 * difX2;
            sx1y += difX1 * difY;
            sx2y += difX2 * difY;

            System.out.printf("%-5d %-8.1f %-8.1f %-8.1f %-12.4f %-12.4f %-12.4f %-15.4f %-15.4f\n",
                    i+1, x1[i], x2[i], y[i], difX1, difX2, difY, difX1*difX1, difX2*difX2);
        }

        System.out.println("\nContinuacion de la tabla:");
        System.out.printf("%-5s %-15s %-15s %-15s\n", "i", "(x1-mx1)(x2-mx2)", "(x1-mx1)(y-my)", "(x2-mx2)(y-my)");

        for (int i = 0; i < n; i++) {
            double difX1 = x1[i] - mediaX1;
            double difX2 = x2[i] - mediaX2;
            double difY = y[i] - mediaY;

            System.out.printf("%-5d %-15.4f %-15.4f %-15.4f\n",
                    i+1, difX1*difX2, difX1*difY, difX2*difY);
        }

        System.out.println("\nSumatorias:");
        System.out.println("Sx1x1 = " + String.format("%.4f", sx1x1));
        System.out.println("Sx2x2 = " + String.format("%.4f", sx2x2));
        System.out.println("Sx1x2 = " + String.format("%.4f", sx1x2));
        System.out.println("Sx1y = " + String.format("%.4f", sx1y));
        System.out.println("Sx2y = " + String.format("%.4f", sx2y));

        System.out.println("\nPaso 3: Calcular denominador D");
        double D = sx1x1 * sx2x2 - sx1x2 * sx1x2;
        System.out.println("D = Sx1x1 * Sx2x2 - Sx1x2^2");
        System.out.println("D = " + String.format("%.4f", sx1x1) + " * " +
                String.format("%.4f", sx2x2) + " - " + String.format("%.4f", sx1x2) + "^2");
        System.out.println("D = " + String.format("%.4f", D));

        System.out.println("\nPaso 4: Calcular coeficientes");
        System.out.println("b1 = (Sx2x2 * Sx1y - Sx1x2 * Sx2y) / D");
        System.out.println("b1 = (" + String.format("%.4f", sx2x2) + " * " +
                String.format("%.4f", sx1y) + " - " + String.format("%.4f", sx1x2) +
                " * " + String.format("%.4f", sx2y) + ") / " + String.format("%.4f", D));
        System.out.println("b1 = " + String.format("%.4f", b1));

        System.out.println("\nb2 = (Sx1x1 * Sx2y - Sx1x2 * Sx1y) / D");
        System.out.println("b2 = (" + String.format("%.4f", sx1x1) + " * " +
                String.format("%.4f", sx2y) + " - " + String.format("%.4f", sx1x2) +
                " * " + String.format("%.4f", sx1y) + ") / " + String.format("%.4f", D));
        System.out.println("b2 = " + String.format("%.4f", b2));

        System.out.println("\nb0 = media_y - b1 * media_x1 - b2 * media_x2");
        System.out.println("b0 = " + String.format("%.4f", mediaY) + " - " +
                String.format("%.4f", b1) + " * " + String.format("%.4f", mediaX1) +
                " - " + String.format("%.4f", b2) + " * " + String.format("%.4f", mediaX2));
        System.out.println("b0 = " + String.format("%.4f", b0));

        System.out.println("\nPaso 5: Calcular predicciones y R^2");
        System.out.printf("%-5s %-8s %-8s %-8s %-12s %-15s %-15s\n",
                "i", "x1", "x2", "y", "y_pred", "(y-y_pred)^2", "(y-my)^2");

        double ssRes = 0;
        double ssTot = 0;
        for (int i = 0; i < n; i++) {
            double yPred = b0 + b1 * x1[i] + b2 * x2[i];
            double resid = (y[i] - yPred) * (y[i] - yPred);
            double total = (y[i] - mediaY) * (y[i] - mediaY);

            ssRes += resid;
            ssTot += total;

            System.out.printf("%-5d %-8.1f %-8.1f %-8.1f %-12.4f %-15.6f %-15.4f\n",
                    i+1, x1[i], x2[i], y[i], yPred, resid, total);
        }

        System.out.println("\nSS_res = " + String.format("%.6f", ssRes));
        System.out.println("SS_tot = " + String.format("%.4f", ssTot));
        System.out.println("R^2 = 1 - (SS_res / SS_tot)");
        System.out.println("R^2 = " + String.format("%.4f", r2));
    }

    public double getB0() {
        return b0;
    }

    public double getB1() {
        return b1;
    }

    public double getB2() {
        return b2;
    }

    public double getR2() {
        return r2;
    }
}