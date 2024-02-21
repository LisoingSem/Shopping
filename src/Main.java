interface DiscountRate {
    double getServiceDiscount();
    double getProductDiscount();
}

class Customer implements DiscountRate {
    private String customerName;
    private String customerType;

    public Customer(String customerName, String customerType) {
        this.customerName = customerName;
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    @Override
    public double getServiceDiscount() {
        switch (customerType) {
            case "Premium":
                return 0.2;
            case "Gold":
                return 0.15;
            case "Silver":
                return 0.1;
            default:
                return 0;
        }
    }

    @Override
    public double getProductDiscount() {
        switch (customerType) {
            case "Premium":
            case "Gold":
            case "Silver":
                return 0.1;
            default:
                return 0;
        }
    }
}

class Sale {
    private Customer customer;
    private String date;
    private double serviceExpense;
    private double productExpense;

    public Sale(Customer customer, String date) {
        this.customer = customer;
        this.date = date;
    }

    public void setServiceExpense(double serviceExpense) {
        this.serviceExpense = serviceExpense;
    }

    public void setProductExpense(double productExpense) {
        this.productExpense = productExpense;
    }

    public double getTotalExpense() {
        double serviceDiscount = serviceExpense * customer.getServiceDiscount();
        double productDiscount = productExpense * customer.getProductDiscount();
        return serviceExpense - serviceDiscount + productExpense - productDiscount;
    }

    public void displayInfo() {
        System.out.println("Customer Name: " + customer.getCustomerName());
        System.out.println("Customer Type: " + customer.getCustomerType());
        System.out.println("Date: " + date);
        System.out.println("Service Expense: $" + serviceExpense);
        System.out.println("Product Expense: $" + productExpense);
        System.out.println("Total Expense: $" + getTotalExpense());
    }
}

public class Main {
    public static void main(String[] args) {
        Customer premiumCustomer = new Customer("Lisoing Sem Premium", "Premium");
        Customer goldCustomer = new Customer("Lisoing Sem Gold", "Gold");
        Customer silverCustomer = new Customer("Lisoing Sem Silver", "Silver");
        Customer normalCustomer = new Customer("Lisoing Sem Nomal", "Normal");

        Sale premiumSale = new Sale(premiumCustomer, "2023-11-20");
        premiumSale.setServiceExpense(100.0);
        premiumSale.setProductExpense(200.0);

        Sale goldSale = new Sale(goldCustomer, "2023-11-21");
        goldSale.setServiceExpense(50.0);
        goldSale.setProductExpense(150.0);

        Sale silverSale = new Sale(silverCustomer, "2023-11-22");
        silverSale.setServiceExpense(25.0);
        silverSale.setProductExpense(75.0);

        Sale normalSale = new Sale(normalCustomer, "2023-11-23");
        normalSale.setServiceExpense(10.0);
        normalSale.setProductExpense(30.0);

        premiumSale.displayInfo();
        System.out.println("======================================");
        goldSale.displayInfo();
        System.out.println("======================================");
        silverSale.displayInfo();
        System.out.println("======================================");
        normalSale.displayInfo();
        System.out.println("======================================");
    }
}