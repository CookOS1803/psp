package clothingStore;

import clothingStore.AbstractClothing.*;;

public class App {
    public static void main(String[] args) throws Exception {
        Shoes a = new Shoes();
        Shirt b = new Shirt();

        a.setPrice(4);
        b.setPrice(5);

        Order order = new Order();
        order.addItem(a);
        order.addItem(b);

        System.out.println(order);
    }
}
