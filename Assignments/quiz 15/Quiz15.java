

public class Quiz15{
    public static void main(String[] args){
        
        ArrayIntList quiz15 = new ArrayIntList(1,2,3); // add 1,2,3
        System.out.println(quiz15); // example: [1, 2, 3]
        quiz15.mirror();
        System.out.println(quiz15); // [1, 2, 3, 3, 2, 1]
        System.out.println(quiz15.isPerfectMirror()); // true
        quiz15.undoMirror();
        System.out.println(quiz15); // [1, 2, 3]
        
    }
}