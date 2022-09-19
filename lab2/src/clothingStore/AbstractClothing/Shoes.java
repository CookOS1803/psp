package clothingStore.AbstractClothing;

public class Shoes extends AbstractClothing {

    protected int size;

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    String getSize() {
        return Integer.toString(size);
    }

    public Shoes()
    {
        super();
        size = 0;
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
            return getSize().equals(temp.getSize());
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
