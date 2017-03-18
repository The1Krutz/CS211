public class test {
    public static void main(String[] args) {

        HashSet<Character> test1 = new HashSet<Character>();
        test1.add((char) 97);
        test1.add('b');
        test1.add('c');
//        test1.add('e');
        System.out.println(test1); // [a,b,c]

        HashSet<Character> test2 = new HashSet<Character>();
        test2.add((char) 97);
        test2.add('b');
        test2.add('c');
//        test2.add('d');
        System.out.println(test2); // [a,b,c]

        System.out.println(test1.equals(test2)); // true

        // char 97, 107, and 117 all three hash to 7
        test1.add((char) 107);
        test1.add((char) 117);
        System.out.println(test1); // [u,k,a,b,c]
        System.out.println(test1.longestChain()); // 3

        /*
         * 
         * 
         * 
         * 
         * HashSet<Integer> a = new HashSet<Integer>();
         * a.add(91);
         * a.add(1);
         * a.add(71);
         * a.add(5);
         * a.add(45);
         * a.add(7);
         * a.add(9);
         * // a.add(97);
         * // a.add(27);
         * // a.add(29);
         * // a.add(297);
         * 
         * // System.out.println(a.toString());
         * // System.out.println("----- ----- -----");
         * System.out.println(a.toString2());
         * 
         * // System.out.println();
         * // System.out.println("----- ----- -----");
         * // System.out.println();
         * 
         * HashSet<CalendarDate> b = new HashSet<CalendarDate>();
         * for (int i = 1; i < 9; i++)
         * b.add(new CalendarDate(1, i, 17));
         * 
         * // System.out.println(b.toString());
         * // System.out.println("----- ----- -----");
         * System.out.println(b.toString2());
         * 
         * a = new HashSet<Integer>();
         * a.add(91);
         * a.add(1);
         * a.add(71);
         * a.add(5);
         * a.add(45);
         * a.add(7);
         * a.add(9);
         * a.add(97);
         * a.add(27);
         * a.add(29);
         * a.add(297);
         * 
         * // System.out.println(a.toString());
         * // System.out.println("----- ----- -----");
         * System.out.println(a.toString2());
         * 
         * // System.out.println();
         * // System.out.println("----- ----- -----");
         * // System.out.println();
         * 
         * b = new HashSet<CalendarDate>();
         * for (int i = 1; i < 15; i++)
         * b.add(new CalendarDate(1, i, 17));
         * 
         * // System.out.println(b.toString());
         * // System.out.println("----- ----- -----");
         * System.out.println(b.toString2());
         * 
         * 
         * 
         * 
         */

    }
}
