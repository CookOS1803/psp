package clothingStore;

import java.util.*;
import clothingStore.AbstractClothing.*;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<AbstractClothing> clothings = new ArrayList<AbstractClothing>();
        clothings.add(new Shoes("Кроссовки", "Nike", 300, 42));
        clothings.add(new Shirt("Поло", "Ralph Loren", 400, Shirt.Size.L));
        
        Order order = new Order();
        int choice;

        try (Scanner in = new Scanner(System.in))
        {
            while (true)
            {
                choice = GetInt(in,
                "1. Показать каталог\n" +
                "2. Добавить товар в заказ\n" +
                "3. Удалить товар из заказа\n" +
                "4. Показать заказ\n" +
                "0. Выход\n");

                System.out.println();

                switch (choice)
                {
                    case 1:
                        ShowArray(clothings);
                        in.nextLine();
                        break;
                    case 2:
                        ShowArray(clothings);
                        do choice = GetInt(in, "Введите номер товара: ");
                        while (choice < 1 || choice > clothings.size());

                        order.addItem(clothings.get(choice - 1));

                        break;
                    case 3:
                        System.out.println(order);

                        do choice = GetInt(in, "Введите номер товара: ");
                        while (choice < 1 || choice > order.getSize());

                        order.removeItem(choice - 1);

                        break;
                    case 4:
                        System.out.println(order);
                        in.nextLine();
                        break;
                    case 0:
                        return;
                }
                
                System.out.println();
            }
        }
    }

    private static int GetInt(Scanner in, String output)
    {
        int result;

        while (true)
        {
            System.out.print(output);

            try
            {
                result = in.nextInt();
                in.nextLine();

                return result;
                
            }
            catch (Exception e)
            {
                in.nextLine();
            }
        }
    }

    private static void ShowArray(ArrayList<AbstractClothing> list)
    {
        int i = 1;
        for (var item : list) {
            System.out.println(i + ": " + item);
            i++;
        }
    }
}
