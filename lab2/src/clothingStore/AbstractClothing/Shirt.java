package clothingStore.AbstractClothing;

public class Shirt extends AbstractClothing {

    public enum Size {
        S, M, L, XL, XXL, XXXL
    }

    protected Size size;
    public void setSize(Size size) {
        this.size = size;
    }
    @Override
    String getSize() {
        return size.toString();
    }

    public Shirt()
    {
        super();
        size = Size.S;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Shirt)
        {
            var temp = (Shirt)obj;
            return size == temp.size;
        }
        else
            return false;
    }
    
    @Override
    public String toString()
    {
        return "Категория: рубашки; " + super.toString() + "; Размер: " + size; 
    }
}
