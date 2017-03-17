public class test {
    public static void main(String[] args) {

        HashSet<Integer> a = new HashSet<Integer>();
        a.add(91);
        a.add(1);
        a.add(71);
        a.add(5);
        a.add(45);
        a.add(7);
        a.add(9);
        // a.add(97);
        // a.add(27);
        // a.add(29);
        // a.add(297);

        // System.out.println(a.toString());
        // System.out.println("----- ----- -----");
        System.out.println(a.toString2());

        // System.out.println();
        // System.out.println("----- ----- -----");
        // System.out.println();

        HashSet<CalendarDate> b = new HashSet<CalendarDate>();
        for (int i = 1; i < 9; i++)
            b.add(new CalendarDate(1, i, 17));

        // System.out.println(b.toString());
        // System.out.println("----- ----- -----");
        System.out.println(b.toString2());

        a = new HashSet<Integer>();
        a.add(91);
        a.add(1);
        a.add(71);
        a.add(5);
        a.add(45);
        a.add(7);
        a.add(9);
        a.add(97);
        a.add(27);
        a.add(29);
        a.add(297);

        // System.out.println(a.toString());
        // System.out.println("----- ----- -----");
        System.out.println(a.toString2());

        // System.out.println();
        // System.out.println("----- ----- -----");
        // System.out.println();

        b = new HashSet<CalendarDate>();
        for (int i = 1; i < 15; i++)
            b.add(new CalendarDate(1, i, 17));

        // System.out.println(b.toString());
        // System.out.println("----- ----- -----");
        System.out.println(b.toString2());

    }
}
