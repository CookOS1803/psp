package clothingStore.AbstractClothing;

public class Shoes extends AbstractClothing {

    protected int size;

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize()
    {
        return size;
    }

    @Override
    String getStringSize() {
        return Integer.toString(size);
    }

    public Shoes()
    {
        super();
        size = 0;
    }

    public Shoes(String name, String brand, float price, int size)
    {
        super(name, brand, price);
        this.size = size;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Shoes)
        {
            var temp = (Shoes)obj;
            return getStringSize().equals(temp.getStringSize());
        }
        else
            return false;
    }
    
    @Override
    public String toString()
    {
        return "Категория: обувь; " + super.toString() + "; Размер: " + size; 
    }
}
