so if you have a class Human with two constructors
public Human ( int age, String name ) and
public Human ( int age )
then you have two options. You can either







public class Human{
    private int _age;
    private String _name;

    public Human (int age, String name){
        this._age = age;
        this._name = name;
    }

    public Human( int age ){
        // two options here!
        // either a call to this to take advantage of the constructor defined above
        this( age, "default name" );

        // OR set both values here
        this._age = age;
        this._name = "default name";
    }
}