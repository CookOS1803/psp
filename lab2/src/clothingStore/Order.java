package clothingStore;

import java.util.*;
import clothingStore.AbstractClothing.*;

public class Order {
    private ArrayList<AbstractClothing> items;

    public Order()
    {
        items = new ArrayList<AbstractClothing>();
    }

    public void addItem(AbstractClothing newItem)
    {
        items.add(newItem);
    }

    public float getTotalPrice()
    {
        float result = 0f;

        for (var i : items) {
            result += i.getPrice();
        }

        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((items == null) ? 0 : items.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Order)
        {
            var temp = (Order)obj;
            return items.equals(temp.items);
        }
        else
            return false;
    }

    @Override
    public String toString() {
        int i = 1;
        String result = "";

        for (var item : items) {
            result = result.concat(i + ": " + item + "\n");
            i++;
        }

        result = result.concat("Итого: " + getTotalPrice());

        return result;
    }
}
