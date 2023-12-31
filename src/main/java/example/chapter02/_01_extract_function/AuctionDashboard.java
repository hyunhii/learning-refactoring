package example.chapter02._01_extract_function;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class AuctionDashboard {
    private void printOngoingItems() {
        print(getItems(getAuction("Seoul")));
    }

    private void printInProgressItems(String region) {
        print(getItems(getAuction(region)));
    }

    private static void print(List<String> items) {
        items.forEach(System.out::println);
    }

    private static List<String> getItems(Auction auction) {
        List<String> items = new ArrayList<>();
        auction.getItems().forEach(item -> items.add(item.getName()));
        return items;
    }

    private static Auction getAuction(String region) {
        AuctionHub auctionHub = AuctionHub.connect(region);
        return auctionHub.getAuction();
    }

    public static void main(String[] args) {
        AuctionDashboard auctionDashboard = new AuctionDashboard();
        auctionDashboard.printOngoingItems();
        auctionDashboard.printInProgressItems("Seoul");
    }
}

@Getter
@AllArgsConstructor
class AuctionHub {

    private String region;
    private List<Auction> auctions;

    public AuctionHub(String region) {
        this.region = region;
        this.auctions =
                List.of(
                        new Auction(List.of(new Item("A", 3000), new Item("B", 4000), new Item("C", 5000))));
    }

    public Auction getAuction() {
        return auctions.get(0);
    }

    public static AuctionHub connect(String region) {
        return new AuctionHub(region);
    }
}

@Getter
@AllArgsConstructor
class Auction {
    private List<Item> items;
}

@Getter
@AllArgsConstructor
class Item {
    private String name;
    private int price;
}
