public class Item {
    private String name;
    private float price;
    private int quantity;

    public Item(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void printItem() {
        System.out.println("Name : " + this.name + ", Price : " + this.price + ", Quantity : " + this.quantity);
    }


}
