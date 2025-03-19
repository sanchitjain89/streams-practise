package lambda;

public class lambda1 {

    public static void main(String[] args) {
        // Lambda Expression
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;

        // Create instance of MathOperation old way
        MultiplyOperation multiply = new MultiplyOperation();

        System.out.println(multiply.operate(5, 3));
        System.out.println(addition.operate(5, 3)); // Output: 8
        System.out.println(subtraction.operate(5, 3)); // Output: 2
    }
}

class MultiplyOperation implements MathOperation{

    @Override
    public int operate(int a, int b) {
        return a*b;
    }
}
