import java.util.Scanner;
public class Main {
    private Customer mainCustomer;
    private Store mainStore;

    private int maxCustomer;
    private int maxStore;
    private int countOfCustomer;
    private int countOfStore;
    private Customer[] customers;
    private Store[] stores;
    public Main(int maxCustomer, int maxStore) {
        this.maxStore = maxStore;
        this.maxCustomer = maxCustomer;
        this.countOfCustomer = 0;
        this.countOfStore = 0;

        this.customers = new Customer[this.maxCustomer];
        this.stores = new Store[this.maxStore];

        for(int i = 0; i < maxCustomer; i++) {
            customers[i] = new Customer("", 0,0);
        }
        for(int i = 0; i < maxStore; i++) {
            stores[i] = new Store("", "",0);
        }

    }

    public int getMaxCustomer() {
        return this.maxCustomer;
    }
    public void setMaxCustomer(int maxCustomer) {
        this.maxCustomer = maxCustomer;
    }
    public int getMaxStore() {
        return this.maxStore;
    }
    public void setMaxStore(int maxStore) {
        this.maxStore = maxStore;
    }
    public void getMainCustomer() {
        this.mainCustomer.printInfo();
    }
    public void setMainCustomer(Customer mainCustomer) {
        this.mainCustomer = mainCustomer;
    }
    public void getMainStore() {
        this.mainStore.printInfo();
    }
    public void setMainStore(Store mainStore) {
        this.mainCustomer = mainCustomer;
    }


    public void sleep(int mil) {
        try {

            Thread.sleep(mil);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }
    }

    public void printAllCustomer() {
        System.out.println("============== All Customer =============== ");
        if(this.countOfCustomer == 0) {
            System.out.println("No have any customer... ");
        }else {
            for(int i = 0; i < this.countOfCustomer; i++) {
                customers[i].printInfo();
            }
        }

        System.out.println("============================================");
    }

    public void printAllStore() {
        System.out.println("================ All Store ================ ");
        if(this.countOfStore == 0) {
            System.out.println("No have any store... ");
        }else {
            for(int i = 0; i < this.countOfStore; i++) {
                stores[i].printInfo();
            }
        }
        System.out.println("===========================================");

    }

    public void addCustomerInterface() {
        if(countOfCustomer < maxCustomer) {
            System.out.println("================ Add Customer UI ================ ");
            Scanner inputCus = new Scanner(System.in);
            System.out.println("Enter customer name : ");
            String name = inputCus.nextLine();
            System.out.println("Enter customer age : ");
            int age = inputCus.nextInt();
            System.out.println("Enter customer budget : ");
            float budget = inputCus.nextFloat();

            Customer cus = new Customer(name,age,budget);

            this.customers[countOfCustomer++] = cus;
            System.out.println("Adding ........");
            this.sleep(2000);
            System.out.println("Added one customer.");
        }else {
            System.out.println("Error: Range out of customer");
        }



    }

    public void addStoreInterface() {
        if(countOfStore < maxStore) {
            System.out.println("================ Add Store UI ================= ");
            Scanner inputSto = new Scanner(System.in);
            System.out.println("Enter store name : ");
            String name = inputSto.nextLine();
            System.out.println("Enter store type : ");
            String type = inputSto.nextLine();
            System.out.println("Enter store budget : ");
            float budget = inputSto.nextFloat();

            Store store = new Store(name, type, budget);

            this.stores[countOfStore++] = store;
            System.out.println("Adding.........");
            this.sleep(2000);
            System.out.println("Added one store.");

        }else {
            System.out.println("Error: Range out of store .");
        }

    }

    public boolean chooseMainCustomerByName(String name) {
        Customer cus = findTheCustomer(name);
        if(cus == null){
            return false;
        }else {
            this.mainCustomer = cus;
            return true;
        }
    }

    public boolean chooseMainStoreByName(String name) {
       Store store = findTheStore(name);
       if(store == null){
           return false;
       }else {
           this.mainStore = store;
           return true;
       }
    }

    public Customer findTheCustomer(String name) {
        Customer cus = null;
        boolean isFinded = false;
        for(int i = 0; i < this.countOfCustomer; i++) {
            if(customers[i].getName().equals(name)) {
                cus = customers[i];
                isFinded = true;
            }
        }

        if(isFinded == false) {
            System.out.println( name + " is not in customers");
        }

        return cus;
    }

    public Store findTheStore(String name) {
        Store store = null;
        boolean isFinded = false;
        for(int i = 0; i < this.countOfStore; i++) {
            if(stores[i].getName().equals(name)) {
                store = stores[i];
                isFinded = true;
            }
        }

        if(isFinded == false) {
            System.out.println( name + " is not in stores");
        }

        return store;
    }


    public void orderFromStore(Store store, String itemName) {
        Item temp = store.findItem(itemName);
        if(temp == null) {
            return;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("quantity of "+ temp.getName() +" you want to buy : ( < "+ temp.getQuantity()+" ) ." ) ;
        int count = input.nextInt();
        if(count > temp.getQuantity()) {
            System.out.println("Sorry. Not enough quantity of "+ temp.getName());
            return;
        }
        Item orderItem = new Item(temp.getName(), temp.getPrice(), count);
        this.mainCustomer.setItemToBag(orderItem);
        this.mainStore.recvOrder(orderItem);
        System.out.println("===================================================");
        System.out.println("\n\n");


    }
    public void goToStores() {
        String chooser = "";
        do {
            this.printAllStore();
            System.out.println("Choose one by name store to shopping (\'exit\' to go out)");
            Scanner input = new Scanner(System.in);
            chooser = input.nextLine();

            if(chooser.equals("exit")) {
                break;
            }

            Store store = this.findTheStore(chooser);

            // print menu of store
            if(store != null) {
                int isOrder = 0;
                do {
                    store.getMenu();
                    System.out.println("Do you want order some item : ( 1. Yes, 2. No )");
                    isOrder = input.nextInt();
                    input.nextLine();

                    if(isOrder == 1) {
                        System.out.println("Enter the name item you want to order :");
                        String itemName = input.nextLine();
                        this.orderFromStore(store, itemName);

                    }else if(isOrder == 2) {
                        break;
                    }else {
                        System.out.println("Wrong input number .... ");
                    }
                }while(isOrder != 2);


            }




        }while(true);




    }

    public void printCustomerUI() {
        System.out.println("================= Customer UI ======================");
        System.out.println("1. Show my profile");
        System.out.println("2. Go to stores");
        System.out.println("3. Show my bag");
        System.out.println("4. Exit");

    }


    public void customerIU() {
        int chooser = 0;

        do {
            this.printCustomerUI();
            Scanner input = new Scanner(System.in);
            chooser = input.nextInt();
            switch (chooser) {
                case 1:
                    System.out.println("================== My Profile ====================");
                    this.getMainCustomer();
                    break;
                case 2:
                    this.goToStores();
                    break;
                case 3:
                    this.mainCustomer.getItems();
                    break;
                case 4:
                    System.out.println("Go Out .....");
                    return;

                default:
                    System.out.println("Wrong number .");
                    return;


            }

        }while(chooser != 5);
    }

    public void useCustomer() {
        boolean isCustomer = true;
        do {
            this.printAllCustomer();
            Scanner input = new Scanner(System.in);
            System.out.println("Choose one customer by name: (\'exit\' to fo go out)");
            String name = input.nextLine();
            if(name.equals("exit")) {
                return;
            }
            isCustomer = this.chooseMainCustomerByName(name);

            if(isCustomer == true) {
                this.customerIU();
            }


        }while(isCustomer == false);



    }

    public void printStoreUI() {
        System.out.println("================== Store UI ===================== ");
        System.out.println("1. Show menu of store");
        System.out.println("2. Add item to menu of store");
        System.out.println("3. Delete item to menu of store");
        System.out.println("4. Show information of store");
        System.out.println("5. Exit");
    }

    public void addItemtoMenu() {
        System.out.println("==================== Add Item UI ====================");
        Scanner input = new Scanner(System.in);
        System.out.println("How many items do you want add to store's menu ? ( < 10 )");
        int count = input.nextInt();


        for (int i = 1; i <= count; i++) {
            input.nextLine();
            System.out.println("Name of item " + i + " : ");
            String nameOfItem = input.nextLine();
            System.out.println("Price of item " + i + ": ");
            Float price = input.nextFloat();
            System.out.println("quantity of item " + i + ": ");
            int quantity = input.nextInt();
            Item temp = new Item(nameOfItem,price,quantity);
            this.mainStore.setMenu(temp);
        }
        System.out.println("Adding ...... ");
        this.sleep(2000);
        System.out.println("Added " + count + " item into menu !");
        System.out.println("=====================================================");


    }

    public void storeUI() {
        int chooser = 0;

        do {
            this.printStoreUI();
            System.out.println("choose one number above :");
            Scanner input = new Scanner(System.in);
            chooser = input.nextInt();
            switch (chooser) {
                case 1:
                    this.mainStore.getMenu();
                    break;
                case 2:
                    this.addItemtoMenu();
                    break;
                case 3:
                    System.out.println("==================== Delete UI ===================");
                    System.out.println("Enter name of item you want delete:  (\'exit\' to go out)");
                    input.nextLine();
                    String deleteItem = input.nextLine();
                    if(deleteItem.equals("exit")) {
                        break;
                    }
                    this.mainStore.deleteItemFromMenu(deleteItem);
                    break;
                case 4:
                    System.out.println("============= Information of store ============");
                    this.mainStore.printInfo();
                    break;
                case 5:
                    System.out.println("Go Out .....");
                    return;

                default:
                    System.out.println("Wrong number .");
                    break;


            }

        }while(chooser != 5);
    }

    public void useStore() {
        boolean isStore = true;
        do {
            this.printAllStore();
            Scanner input = new Scanner(System.in);
            System.out.println("Choose one store by name: (\'exit\' to go out)");
            String name = input.nextLine();
            if(name.equals("exit")) {

                return;
            }
            isStore = this.chooseMainStoreByName(name);

            if(isStore == true) {
                this.storeUI();
            }


        }while(isStore == false);

    }





    public void printMainMenu() {
        System.out.println("==================== Main UI ====================== ");
        System.out.println("1. Add Customer");
        System.out.println("2. Add Store");
        System.out.println("3. Use customer ");
        System.out.println("4. Use store ");
        System.out.println("5. Show all customers");
        System.out.println("6. Show all store");
        System.out.println("7. Exit");

        System.out.println("Choose one : ");

    }



    public static void main(String[] args) {


        System.out.println("Hello !! Please setup for your program ");
        Scanner mySetup = new Scanner(System.in);
        System.out.println("Enter the number of customer you want in this program :");
        int maxCus = mySetup.nextInt();
        System.out.println("Enter the number of store you want in this program : ");
        int maxStore = mySetup.nextInt();
        Main program = new Main(maxCus,maxStore);
        program.sleep(2000);
        System.out.println("You have successfully first setup. ");
        System.out.println("Max Customer = " + program.getMaxStore() + ", Max Store = " + program.getMaxCustomer());
        int setup = 0;

        do{
            program.printMainMenu();
            setup = mySetup.nextInt();

            switch (setup) {
                case 1:
                    program.addCustomerInterface();
                    break;
                case 2:
                    program.addStoreInterface();
                    break;
                case 3:
                    program.useCustomer();
                    break;
                case 4:
                    program.useStore();
                    break;
                case 5:
                    program.printAllCustomer();
                    break;
                case 6:
                    program.printAllStore();
                    break;
                case 7:
                    program.sleep(1000);
                    System.out.println("Bye . ");
                    break;

                default:
                    System.out.println("Wrong number .");
                    break;

            }
        }while(setup != 7);



    }
}

