// Basic tests for Chapter 17 Exercises
// upgraded into generic Search Tree use
// CS211, March 2017, W.P. Iverson, instructor
public class Post {

    public static void main(String[] args) {

         SearchTree<Double> empty = new SearchTree<Double>();
         SearchTree<CalendarDate> test = new SearchTree<CalendarDate>();
         test.add(new CalendarDate(1, 1, 2017));
         test.add(new CalendarDate(12, 12, 2016));
        
         // A. Exercise #7 isFull();
         System.out.println("// A. Exercise #7 isFull();");
         System.out.println(test.isFull() + " = false"); // false
         System.out.println(empty.isFull() + " = true"); // true
        
        // // B. Exercise #9 equals(t2);
         System.out.println("// B. Exercise #9 equals(t2);");
         System.out.println(test.equals(test) + " = true"); // true
         System.out.println(test.equals(empty) + " = false"); // false
        
        // // D. Exercise #12 removeLeaves();
        System.out.println("//      D.  Exercise #12    removeLeaves();");
         empty.removeLeaves();
         empty.print(); // nothing to print
         test.removeLeaves();
         test.print();// [1/1/2017]

        SearchTree<Integer> a = new SearchTree<Integer>();
        a.add(1);
        a.add(2);
        a.add(5);
        a.add(6);
        a.add(9);
        a.add(0);
        a.add(11);
        a.print();
        a.removeLeaves();
        a.print();
        a.removeLeaves();
        a.print();
        a.removeLeaves();
        a.print();
        a.removeLeaves();
        a.print();
        a.removeLeaves();
        a.print();
        a.removeLeaves();
        a.print();
        a.removeLeaves();
        a.print();

        //
        // // C. remove process explained in detail via PowerPoint at BJP site
        System.out.println("//      C.  remove process explained in detail via PowerPoint at BJP site");
         empty.remove(42.);
         empty.print(); // nothing to print
         test.remove(new CalendarDate(1, 1, 2017));
         test.print(); // all gone

        a = new SearchTree<Integer>();
        a.add(1);
        a.add(2);
        a.add(5);
        a.add(6);
        a.add(9);
        a.add(0);
        a.add(11);
        a.print();
        a.remove(5);
        a.print();
        a.remove(2);
        a.print();
        a.remove(11);
        a.print();
        a.remove(6);
        a.print();

    }
}
