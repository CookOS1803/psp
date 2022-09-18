package clothingStore.AbstractClothing;

import clothingStore.*;

public abstract class AbstractClothing implements IIdentifiable {
    private static int nextId = 0;

    protected String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    protected String brand;
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    protected float price;
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    protected int id;
    @Override
    public int getId() {
        return id;
    }

    public AbstractClothing()
    {
        name = "unknown clothing";
        brand = "unknown brand";
        price = 0f;

        calculateId();
    }

    abstract String getSize();

    @Override
    public void calculateId()
    {
        id = nextId;
        nextId++;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((brand == null) ? 0 : brand.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + Float.floatToIntBits(price);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof AbstractClothing)
        {
            var temp = (AbstractClothing)obj;
            return name.equals(temp.getName()) &&
                brand.equals(temp.getBrand()) &&
                price == temp.getPrice();
        }
        else
            return false;
    }

    @Override
    public String toString()
    {
        return "Название: " + name + "; Бренд: " + brand + "; Цена: " + price; 
    }

}
