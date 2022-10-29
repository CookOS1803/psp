package com.cookos;


import java.util.*;
import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import org.hibernate.cfg.*;

public class App 
{
    private static SessionFactory factory = null;

    public static void main(String[] args)
    {
        try (var ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            var in = new Scanner(System.in)) {

            var meta = new MetadataSources(ssr).getMetadataBuilder().build();         
            factory = meta.getSessionFactoryBuilder().build();

            var configuration = new Configuration();
            configuration.addAnnotatedClass(Employee.class);

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
                    case 1 -> showAll();
                    case 2 -> showEmployee(in);
                    case 3 -> addEmployee(in);
                    case 4 -> removeEmployee(in);
                    case 5 -> updateEmployee(in);
                    case 6 -> calculateSalary(in);
                    
                    case 0 -> {
                        factory.close();
                        exit = true;
                    }
                }
            }            
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    private static void calculateSalary(Scanner in) {
        var employee = findById(in);

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

    private static void showEmployee(Scanner in) {
        var employee = findById(in);

        if (employee != null)
            System.out.println(employee);
        else
            System.out.println("Запись с таким ID не найдена");
    }

    private static void updateEmployee(Scanner in) {
        var employee = findById(in);

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

        try (var session = factory.openSession()) {
            var transaction = session.beginTransaction();

            session.merge(employee);
            
            transaction.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void removeEmployee(Scanner in) {
        
        int id = GetInt(in, "Введите ID: ");

        try (var session = factory.openSession()) {
            var transaction = session.beginTransaction();

            var employee = new Employee(id);
            session.remove(employee);
            
            transaction.commit();
            
        } catch (Exception e) {
            System.out.println("Запись с таким ID не найдена");
        }
    }

    private static void addEmployee(Scanner in) {
        
        var employee = new Employee();

        System.out.print("Введите имя: ");
        employee.setFirstName(in.nextLine());

        System.out.print("Введите фамилию: ");
        employee.setLastName(in.nextLine());

        System.out.print("Введите должность: ");
        employee.setPosition(in.nextLine());

        employee.setSalary(GetFloat(in, "Введите зарплату: "));
        employee.setExperience(GetInt(in, "Введите стаж в годах: "));

        try (var session = factory.openSession()) {
            var transaction = session.beginTransaction();

            session.persist(employee);
            var id = (Integer)session.getIdentifier(employee);
            
            transaction.commit();

            System.out.println("Добавлен новый сотрудник с ID " + id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Employee findById(Scanner in) {

        int id = GetInt(in, "Введите ID: ");

        try (var session = factory.openSession()) {
            
            var cb = session.getCriteriaBuilder();
            var query = cb.createQuery(Employee.class);
            var root = query.from(Employee.class);

            query.select(root).where(cb.equal(root.get("id"), id));

            try {
                var employee = session.createQuery(query).getSingleResult();
                return employee;
            } catch (Exception e) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void showAll() {
        
        try (var session = factory.openSession()) {
            
            var query = session.getCriteriaBuilder().createQuery(Employee.class);
            var root = query.from(Employee.class);

            query.select(root);

            var employees = session.createQuery(query).list();

            for (var e : employees) {
                System.out.println(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
