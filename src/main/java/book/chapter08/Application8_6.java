package book.chapter08;

public class Application8_6 {
    public static void main(String[] args) {
        PricingPlan pricingPlan = retrievePricingPlan();
        int baseCharge = pricingPlan.base;
        int chargePerUnit = pricingPlan.unit;

        Order order = retrieveOrder();
        int units = order.units;
        int discountableUnits = Math.max(units - pricingPlan.discountThreshold, 0);

        int discount;
        discount = (int) (discountableUnits * pricingPlan.discountFactor);
        if (order.isRepeat) discount += 20;

        int charge;
        charge = baseCharge + units * chargePerUnit;
        charge = charge - discount;
        chargeOrder(charge);
    }

    private static void chargeOrder(int charge) {
        System.out.println("Application8_6.chargeOrder");
    }

    private static Order retrieveOrder() {
        System.out.println("Application8_6.retrieveOrder");
        return new Order();
    }

    private static PricingPlan retrievePricingPlan() {
        System.out.println("Application8_6.retrievePricingPlan");
        return new PricingPlan();
    }
}

class Order {
    int units;
    boolean isRepeat;
}

class PricingPlan {
    int unit;
    double discountFactor;
    int base;
    int discountThreshold;
}
