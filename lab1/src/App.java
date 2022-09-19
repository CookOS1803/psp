import java.util.*;;

public class App {
    public static void main(String[] args) throws Exception
    {
        Icecream icecream = new Icecream("a", true, 20);
        int choice;

        try (Scanner in = new Scanner(System.in))
        {
            while (true)
            {
                choice = GetInt(in,
                "1. Показать мороженое\n" +
                "2. Изменить название\n" +
                "3. Изменить наличие шоколада\n" +
                "4. Изменить содержание жира\n" + 
                "0. Выход\n");

                System.out.println();

                switch (choice)
                {
                    case 1:
                        WriterInfo.PrintIcecream(icecream);
                        in.nextLine();

                        break;
                    case 2:
                        System.out.println("Текущее название: " + icecream.name());
                        System.out.print("Введите новое название: ");

                        icecream.set_name(in.nextLine());

                        break;
                    case 3:
                        icecream.print_hasChocolate();
                        
                        do choice = GetInt(in, "Введите новое состояние:\n1. есть\n2. нет\n");
                        while (choice != 1 && choice != 2);

                        icecream.set_hasChocolate(choice == 1 ? true : false);

                        break;
                    case 4:
                        System.out.println("Текущий процент жирности: " + icecream.fatPercentage());

                        do choice = GetInt(in, "Введите новый процент жирности: ");
                        while (choice < 0 || choice > 100);

                        icecream.set_fatPercentage(choice);

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
}
