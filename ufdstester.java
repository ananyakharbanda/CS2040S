class UFDStester {
    public static void main(String[] args) {
        int[] elements1 = {7, 9, 2, 4, 1, 6};
        UFDS ufds1 = new UFDS(elements1);
        ufds1.print();
        ufds1.union(7, 9);
        ufds1.print();
        ufds1.union(7, 1);
        ufds1.print();
        ufds1.union(2, 4);
        ufds1.print();
        ufds1.union(4, 6);
        ufds1.print();
        ufds1.union(2, 6);
        ufds1.print();
    }
}
