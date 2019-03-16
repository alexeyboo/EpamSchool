package hw1;

public class Equations {
    public static void main(String[] args) {
        quadratic(1,2,1);
        quadratic(1,0,-1);
        quadratic(0,1,1);
        quadratic(1,1,0);
    }
    static void quadratic(double a, double b, double c) {
        double D = b * b - 4 * a * c;
        double x1, x2;
        if (a == 0) {
            x1 = - (c / b);
            System.out.printf("Корень уравнения x=%f\n", x1);
            return;
        }
        if (D > 0) {
            x1 = (Math.sqrt(D) - b) / (2 * a);
            x2 = (- Math.sqrt(D) - b) / (2 * a);
            System.out.printf("Корни уравнения x1=%f, x2=%f\n", x1, x2);
        } else if (D == 0) {
            x1 = (- b) / (2 * a);
            System.out.printf("Корень уравнения x=%f\n", x1);
        } else System.out.println("Уравнение не имеет решения");
    }
}