public class test {
    public static void main(String[] args) {
        Hexagon a = new Hexagon();
        System.out.println(a.getSide());
        a.setSide(2);
        System.out.println(a.getSide());

        System.out.println(a.getArea());
        System.out.println(a.getPerimeter());

    }
}
