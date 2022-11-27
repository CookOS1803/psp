package com.cookos;


import java.util.*;

public class App 
{
    public static void main(String[] args) throws Exception
    {
        try (var dao = new EmployeeDao();
            var in = new Scanner(System.in)) {

            boolean exit = false;

            while (!exit) {

                int choice = GetInt(in,
                        """
                        1. Показать всех
                        2. Найти по id
                        3. Добавить сотрудника
                        4. Удалить сотрудника
                        5. Изменить сотрудника
                        6. Рассчитать зарплату
                        0. Выход
                        """);

                
                switch (choice) {
                    case 1 -> showAll(dao);
                    case 2 -> showEmployee(in, dao);
                    case 3 -> addEmployee(in, dao);
                    case 4 -> removeEmployee(in, dao);
                    case 5 -> updateEmployee(in, dao);
                    case 6 -> calculateSalary(in, dao);
                    
                    case 0 -> exit = true;
                }
            }            
        }
    }

    private static void calculateSalary(Scanner in, EmployeeDao dao) {
        var employee = findById(in, dao);

        if (employee == null) {
            System.out.println("Запись с таким ID не найдена");
            return;
        }

        float modifier = getSalaryModifier(employee.getExperience());
        var modStr = Float.toString(modifier);
        modStr = modStr.substring(modStr.indexOf('.') + 1);
        if (!modStr.equals("0") && modStr.length() == 1)
            modStr += "0";

        System.out.println("Зарплата без надбавок: " + employee.getSalary());
        System.out.println("Надбавка за стаж: " + modStr + "%");
        System.out.println("Итоговая зарплата: " + employee.getSalary() * modifier);
    }

    private static float getSalaryModifier(int experience) {
        return switch (experience) {
            case 0 -> 1f;
            case 1 -> 1.05f;
            case 2 -> 1.1f;
            case 3 -> 1.15f;
            case 4 -> 1.2f;
        
            default -> experience > 4 ? 1.2f : 1f;
        };
    }

    private static void showEmployee(Scanner in, EmployeeDao dao) {
        var employee = findById(in, dao);

        if (employee != null)
            System.out.println(employee);
        else
            System.out.println("Запись с таким ID не найдена");
    }

    private static void updateEmployee(Scanner in, EmployeeDao dao) {
        var employee = findById(in, dao);

        if (employee == null) {
            System.out.println("Запись с таким ID не найдена");
            return;
        }

        boolean exit = false;

        while (!exit) {

            System.out.println(employee);

            int choice = GetInt(in,
                    """
                    1. Изменить имя
                    2. Изменить фамилию
                    3. Изменить должность
                    4. Изменить зарплату
                    5. Изменить стаж
                    0. Применить
                    """);
            

            switch (choice) {
                case 1 -> {System.out.print("> "); employee.setFirstName(in.nextLine());}
                case 2 -> {System.out.print("> "); employee.setLastName(in.nextLine());}
                case 3 -> {System.out.print("> "); employee.setPosition(in.nextLine());}
                case 4 -> employee.setSalary(GetFloat(in, "> "));
                case 5 -> employee.setExperience(GetInt(in, "> "));

                case 0 -> exit = true;

            }
        }

        dao.updateEmployee(employee);
    }

    private static void removeEmployee(Scanner in, EmployeeDao dao) {
        
        int id = GetInt(in, "Введите ID: ");

        try {
            dao.removeEmployee(id);
        } catch (Exception e) {
            System.out.println("Запись с таким ID не найдена");
        }        
    }

    private static void addEmployee(Scanner in, EmployeeDao dao) {
        
        var employee = new Employee();

        System.out.print("Введите имя: ");
        employee.setFirstName(in.nextLine());

        System.out.print("Введите фамилию: ");
        employee.setLastName(in.nextLine());

        System.out.print("Введите должность: ");
        employee.setPosition(in.nextLine());

        employee.setSalary(GetFloat(in, "Введите зарплату: "));
        employee.setExperience(GetInt(in, "Введите стаж в годах: "));

        int id = dao.addEmployee(employee);
        System.out.println("Добавлен новый сотрудник с ID " + id);

    }

    private static Employee findById(Scanner in, EmployeeDao dao) {

        int id = GetInt(in, "Введите ID: ");        

        return dao.findById(id);
    }

    private static void showAll(EmployeeDao dao) {
        
        var employees = dao.selectAll();

        for (var employee : employees) {
            System.out.println(employee);
        }
    }

    public static int GetInt(Scanner in, String output)
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

    public static float GetFloat(Scanner in, String output)
    {
        float result;

        while (true)
        {
            System.out.print(output);

            try
            {
                result = in.nextFloat();
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
