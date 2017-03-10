
public class q16 {

    public static void main(String[] args) {
LinkedIntList a = new LinkedIntList();
        a.add(13);
        a.add(-88);
        System.out.println(a.isPerfectStutter()); // should be false
        System.out.println();



        a = new LinkedIntList();
        a.add(1);
        a.add(1);
        a.add(2);
        a.add(2);
        a.add(3);
        a.add(3);
        System.out.println(a.isPerfectStutter()); // should be true
        System.out.println();

        a = new LinkedIntList();
        a.add(1);
        a.add(1);
        a.add(2);
        a.add(2);
        a.add(3);
        a.add(3);
        a.add(4);
        System.out.println(a.isPerfectStutter()); // should be false
        System.out.println();

        a = new LinkedIntList();
        a.add(1);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(3);
        System.out.println(a.isPerfectStutter()); // should be false
        System.out.println();

        a = new LinkedIntList();
        a.add(1);
        a.add(1);
        a.add(2);
        a.add(2);
        a.add(3);
        a.add(3);
        a.add(4);
        a.add(4);
        a.add(5);
        a.add(5);
        a.add(6);
        a.add(6);
        System.out.println("pre:  " + a.toString()); // should be un-stuttered
        a.removeStutter();
        System.out.println("post: " + a.toString()); // should be un-stuttered
        System.out.println();

        a = new LinkedIntList();
        a.add(1);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(3);
        System.out.println("pre:  " + a.toString()); // should still be stuttered
        a.removeStutter();
        System.out.println("post: " + a.toString()); // should still be stuttered

    }
}
