package regresion;

public class RegresionPolinomial {

    private double a;
    private double b;
    private double c;
    private double r2;

    public void calcularRegresion(double[] x, double[] y) {
        int n = x.length;

        double sx = 0, sy = 0, sx2 = 0, sx3 = 0, sx4 = 0;
        double sxy = 0, sx2y = 0;

        for (int i = 0; i < n; i++) {
            sx += x[i];
            sy += y[i];
            sx2 += x[i] * x[i];
            sx3 += x[i] * x[i] * x[i];
            sx4 += x[i] * x[i] * x[i] * x[i];
            sxy += x[i] * y[i];
            sx2y += x[i] * x[i] * y[i];
        }


        double[] solucion = resolverSistemaCramer(n, sx, sy, sx2, sx3, sx4, sxy, sx2y);
        a = solucion[0];
        b = solucion[1];
        c = solucion[2];

        double ssRes = 0;
        double ssTot = 0;
        double mediaY = sy / n;

        for (int i = 0; i < n; i++) {
            double yPred = a + b * x[i] + c * x[i] * x[i];
            ssRes += (y[i] - yPred) * (y[i] - yPred);
            ssTot += (y[i] - mediaY) * (y[i] - mediaY);
        }

        r2 = 1 - (ssRes / ssTot);
    }

    private double[] resolverSistemaCramer(double n, double sx, double sy, double sx2,
                                           double sx3, double sx4, double sxy, double sx2y) {

        double D = n * (sx2 * sx4 - sx3 * sx3)
                - sx * (sx * sx4 - sx2 * sx3)
                + sx2 * (sx * sx3 - sx2 * sx2);


        double Da = sy * (sx2 * sx4 - sx3 * sx3)
                - sx * (sxy * sx4 - sx2y * sx3)
                + sx2 * (sxy * sx3 - sx2y * sx2);


        double Db = n * (sxy * sx4 - sx2y * sx3)
                - sy * (sx * sx4 - sx2 * sx3)
                + sx2 * (sx * sx2y - sx2 * sxy);


        double Dc = n * (sx2 * sx2y - sx3 * sxy)
                - sx * (sx * sx2y - sx2 * sxy)
                + sy * (sx * sx3 - sx2 * sx2);

        double[] solucion = new double[3];
        solucion[0] = Da / D;  // a
        solucion[1] = Db / D;  // b
        solucion[2] = Dc / D;  // c

        return solucion;
    }

    public double predecir(double x) {
        return a + b * x + c * x * x;
    }

    public void mostrarResultados() {
        System.out.println("Modelo: y = " + String.format("%.2f", a) + " + " +
                String.format("%.2f", b) + "x + " + String.format("%.2f", c) + "x^2");
        System.out.println("R^2 = " + String.format("%.4f", r2));
    }

    public void mostrarCalculosDetallados(double[] x, double[] y) {
        int n = x.length;

        System.out.println("\nPaso 1: Calcular sumatorias");

        double sx = 0, sy = 0, sx2 = 0, sx3 = 0, sx4 = 0;
        double sxy = 0, sx2y = 0;

        System.out.printf("%-5s %-8s %-8s %-10s %-10s %-10s %-10s %-12s\n",
                "i", "x", "y", "x^2", "x^3", "x^4", "xy", "x^2*y");

        for (int i = 0; i < n; i++) {
            double x2 = x[i] * x[i];
            double x3 = x2 * x[i];
            double x4 = x3 * x[i];

            sx += x[i];
            sy += y[i];
            sx2 += x2;
            sx3 += x3;
            sx4 += x4;
            sxy += x[i] * y[i];
            sx2y += x2 * y[i];

            System.out.printf("%-5d %-8.1f %-8.1f %-10.1f %-10.1f %-10.1f %-10.2f %-12.2f\n",
                    i+1, x[i], y[i], x2, x3, x4, x[i]*y[i], x2*y[i]);
        }

        System.out.println("\nSumatorias:");
        System.out.println("n = " + n);
        System.out.println("Suma x = " + String.format("%.2f", sx));
        System.out.println("Suma y = " + String.format("%.2f", sy));
        System.out.println("Suma x^2 = " + String.format("%.2f", sx2));
        System.out.println("Suma x^3 = " + String.format("%.2f", sx3));
        System.out.println("Suma x^4 = " + String.format("%.2f", sx4));
        System.out.println("Suma xy = " + String.format("%.2f", sxy));
        System.out.println("Suma x^2*y = " + String.format("%.2f", sx2y));

        System.out.println("\nPaso 2: Sistema de ecuaciones 3x3");
        System.out.println("n*a + (Sx)*b + (Sx2)*c = Sy");
        System.out.println("(Sx)*a + (Sx2)*b + (Sx3)*c = Sxy");
        System.out.println("(Sx2)*a + (Sx3)*b + (Sx4)*c = Sx2y");

        System.out.println("\nSustituyendo valores:");
        System.out.println(String.format("%.0f*a + %.2f*b + %.2f*c = %.2f", (double)n, sx, sx2, sy));
        System.out.println(String.format("%.2f*a + %.2f*b + %.2f*c = %.2f", sx, sx2, sx3, sxy));
        System.out.println(String.format("%.2f*a + %.2f*b + %.2f*c = %.2f", sx2, sx3, sx4, sx2y));

        System.out.println("\nPaso 3: Resolver por Cramer");
        System.out.println("a = " + String.format("%.4f", a));
        System.out.println("b = " + String.format("%.4f", b));
        System.out.println("c = " + String.format("%.4f", c));

        System.out.println("\nPaso 4: Calcular R^2");
        System.out.printf("%-5s %-8s %-8s %-12s %-15s\n", "i", "x", "y", "y_pred", "(y-y_pred)^2");

        double ssRes = 0;
        double ssTot = 0;
        double mediaY = sy / n;

        for (int i = 0; i < n; i++) {
            double yPred = a + b * x[i] + c * x[i] * x[i];
            double resid = (y[i] - yPred) * (y[i] - yPred);
            double tot = (y[i] - mediaY) * (y[i] - mediaY);

            ssRes += resid;
            ssTot += tot;

            System.out.printf("%-5d %-8.1f %-8.1f %-12.4f %-15.6f\n",
                    i+1, x[i], y[i], yPred, resid);
        }

        System.out.println("\nMedia de y = " + String.format("%.4f", mediaY));
        System.out.println("SS_res = " + String.format("%.6f", ssRes));
        System.out.println("SS_tot = " + String.format("%.4f", ssTot));
        System.out.println("R^2 = 1 - (SS_res / SS_tot) = " + String.format("%.4f", r2));
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getR2() {
        return r2;
    }
}