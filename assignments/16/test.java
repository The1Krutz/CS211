
public class test {
    public static void main(String[] args) {
        // test .min()
        LinkedList<Integer> a = new LinkedList<Integer>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(0);
        a.add(-1);
        System.out.println(a.min());

        // test .countDuplicates()
        a.clear();
        a.add(1);
        a.add(2);
        a.add(2);
        a.add(3);
        a.add(3);
        a.add(3);
        System.out.println(a.countDuplicates());

        // test .stutter()
        a.clear();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(0);
        System.out.println(a);
        a.stutter();
        System.out.println(a);

        // test .removeAll(E)
        a.clear();
        a.add(0);
        a.add(1);
        a.add(0);
        a.add(2);
        a.add(0);
        a.add(3);
        a.add(0);
        a.add(4);
        a.add(0);
        a.add(5);
        a.add(0);
        a.add(6);
        a.add(0);
        a.add(7);
        System.out.println(a);
        a.removeAll(0);
        System.out.println(a);
    }
}