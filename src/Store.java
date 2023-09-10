
public class Store {
    private String name;
    private Item[] items;
    private float budget;
    private int indexOfItems = 0;
    private int maxItem;
    private String type;

    public Store(String name, String type, float budget) {
        this.name = name;
        this.type = type;
        this.budget = budget;
        this.maxItem = 10;
        this.items = new Item[this.maxItem];

        for(int i = 0; i < this.maxItem; i++) {
            items[i] = new Item("", 0, 0);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBudget() {
        return this.budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getType() {
        return this.type;
    }

    public void sleep(int mil) {
        try {

            Thread.sleep(mil); //1초 대기

        } catch (InterruptedException e) {

            e.printStackTrace();

        }
    }
    public void getMenu() {
        System.out.println("=================== Menu of "+ this.name + " ==================");
        if(indexOfItems == 0) {
            System.out.println("Empty menu .");
            return;
        }
        else {
            System.out.println("Name          price           quantity");

            for(int i = 0; i < indexOfItems; i++) {
                Item temp = items[i];
                System.out.println(temp.getName() + "         " + temp.getPrice() + "             " + temp.getQuantity());
            }
        }
        System.out.println("================================================");
    }

    public void setMenu(Item newItem) {
        this.items[this.indexOfItems++] = newItem;
    }

    public int findIndexOfItem(String name) {
        int index = -1;
        boolean isFind = false;
        for(int i = 0; i < this.indexOfItems; i++){
            if(this.items[i].getName().equals(name)) {
                index = i;
                isFind = true;
            }
        }

        if(isFind == false) {
            System.out.println(name + " is not in our menu !! ");
        }

        return index;
    }
    public void deleteItemFromMenu(String name) {
       System.out.println("Deleting ...... " + name);
       int index = this.findIndexOfItem(name);
       int newIndex = 0;
       if(index >= 0) {
           Item[] newItem = new Item[this.maxItem];
           for (int i = 0; i < this.maxItem; i++) {
               if(i != index) {
                   newItem[newIndex] = this.items[i];
                   newIndex++;
               }
           }

           for (int i = newItem.length; i < this.maxItem; i++) {
                newItem[newIndex] = new Item("", 0,0);
           }

           this.items = newItem;
           this.indexOfItems--;
       }
        this.sleep(2000);
       System.out.println("Deleted " + name + " from menu !!!" );
    }

    public void printInfo() {
        System.out.println("Name : " + this.name  + " , type : " + this.type + " , budget : " + this.budget);
    }

    public Item findItem(String name) {
        boolean isFind = false;
        Item temp = null;
        for(int i = 0; i < this.indexOfItems; i++){
            if(this.items[i].getName().equals(name)) {
                temp = this.items[i];
                isFind = true;
            }
        }
        if(isFind == false) {
            System.out.println(name + " is not in our menu !! ");
        }
        return temp;

    }

    public void sayThanks() {
        System.out.println("Thank you for your order");
    }

    public void recvOrder(Item item) {
        Item temp = this.findItem(item.getName());
        temp.setQuantity(temp.getQuantity() - item.getQuantity());
        this.budget += item.getPrice() * item.getQuantity();
        this.sayThanks();
    }
}
