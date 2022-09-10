public class Icecream {
    private String _name;
    private boolean _hasChocolate;
    private float _fatPercentage;

    public Icecream()
    {
        _name = "";
        _hasChocolate = false;
        _fatPercentage = 0f;
    }

    public Icecream(String name, boolean hasChocolate, float fatPercentage)
    {
        _name = name;
        _hasChocolate = hasChocolate;
        _fatPercentage = fatPercentage;
    }

    public String name() {
        return _name;
    }

    public void set_name(String name) {
        _name = name;
    }

    public boolean hasChocolate() {
        return _hasChocolate;
    }

    public void set_hasChocolate(boolean hasChocolate) {
        _hasChocolate = hasChocolate;
    }

    public float fatPercentage() {
        return _fatPercentage;
    }

    public void set_fatPercentage(float fatPercentage) {
        _fatPercentage = fatPercentage;
    }
    
    public void print_name()
    {
        System.out.println("Название: " + _name);
    }

    public void print_hasChocolate()
    {
        System.out.println("Наличие шоколада: " + (_hasChocolate ? "есть" : "нет"));
    }

    public void print_fatPercentage()
    {
        System.out.println("Процент жирности: " +  _fatPercentage);
    }
}
