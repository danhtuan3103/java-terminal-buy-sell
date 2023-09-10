public class Customer {
    private String name;
    private int age;
    private float budget;
    private Item[] bag;
    private int maxItem;
    private int indexOfBag = 0;


    public Customer(String name, int age, float budget) {
        this.name = name;
        this.age = age;
        this.budget = budget;
        this.maxItem = 10;
        this.bag = new Item[this.maxItem];


        for(int i = 0; i < this.maxItem; i++) {
            bag[i] = new Item("", 0, 0);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getBudget() {
        return this.budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public void getItems() {
        System.out.println("================ Bag Of " + this.name + " ==================");
        if(indexOfBag == 0) {
            System.out.println("No items in bag .");
        }
        for(int i = 0; i < indexOfBag; i++) {
            Item temp = bag[i];
            temp.printItem();
        }
    }

    public void setItem(Item item) {
        this.bag[indexOfBag++]= item;
    }
    public void sleep(int mil) {
        try {

            Thread.sleep(mil); //1초 대기

        } catch (InterruptedException e) {

            e.printStackTrace();

        }
    }
    public void setItemToBag(Item item) {

         System.out.println("Checking ...............");


         if(item.getPrice() * item.getQuantity() > this.budget) {
             this.sleep(2000);
            System.out.println("Not enough money !!! ");
            return;
         }else {
             this.bag[indexOfBag++] = item;
             this.budget -= item.getPrice() * item.getQuantity();
             System.out.println("- " + item.getPrice() * item.getQuantity() );
             this.sleep(2000);
             System.out.println("Order Successfully ! Congratulation <3 ");
         }




    }

    public void countItem() {
        System.out.println("You have " + this.indexOfBag + " items in the bag.");
    }
    public void printInfo() {
        System.out.println("Name : " + this.name + ", age : " + this.age + ", budget : " + this.budget + " .");
    }





}
