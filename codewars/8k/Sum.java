

public static int exepta(int a, int b, int c) {
    int r1 = a + b + c;
    int r2 = a * b * c;
    int r3 = a + b * c;
    int r4 = (a + b) * c;
    int r5 = a * (b + c);

    int max = r1;
    if (r2 > max) max = r2;
    if (r3 > max) max = r3;
    if (r4 > max) max = r4;
    if (r5 > max) max = r5;
    System.out.println(max);
    return max;
}

void main() {


    exepta(6,4,5);

}
