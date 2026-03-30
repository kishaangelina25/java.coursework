public class Mobile extends Gadget {

    private int credit;

    public Mobile(String model, double price, int weight, String size, int credit) {
        super(model, price, weight, size);
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }

    public void addCredit(int amount) {
        if (amount > 0) {
            credit += amount;
            System.out.println("Credit added. New balance: " + credit + " minutes.");
        } else {
            System.out.println("Please enter a positive amount of credit.");
        }
    }

    public void makeCall(String phoneNumber, int duration) {
        if (duration <= credit) {
            System.out.println("Calling " + phoneNumber + " for " + duration + " minutes.");
            credit -= duration;
        } else {
            System.out.println("Insufficient credit to make this call.");
        }
    }

    public void display() {
        super.display();
        System.out.println("Remaining Credit (minutes): " + credit);
    }
}








