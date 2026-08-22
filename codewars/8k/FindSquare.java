public static int FindSquare(int n) {
    int lower = (int) Math.sqrt(n);
    int r1 = lower * lower;
    int r2 = lower + 1;
    int r3 = r2 * r2;
    int r4 = n - r1;
    int r5 = r3 - n;

    if (r4 > r5) {
        return r3;
    } else {
        return r1;
    }
}


void main() {
    System.out.println(FindSquare(111));
}
