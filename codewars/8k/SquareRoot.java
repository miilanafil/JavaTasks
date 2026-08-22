public class SquareRoot {
    public static int[] squareOrSquareRoot(int[] array) {
        int[] result = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            int number = array[i];
            int root = (int) Math.sqrt(number);

            if (root * root == number) {
                result[i] = root;
            } else {
                result[i] = number * number;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] input = {4, 3, 9, 7, 2, 1};
        int[] output = squareOrSquareRoot(input);

        System.out.print("Результат: ");
        for (int i = 0; i < output.length; i++) {
            System.out.print(output[i]);
            if (i < output.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}