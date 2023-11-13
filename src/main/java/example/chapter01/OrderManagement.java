package example.chapter01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class OrderManagement {

    private final Set<UserOrder> userOrders = new HashSet<>();

    /**
     * 주어진 사용자 주문 리스트에 대해 각 사용자 이름과 주문번호를 읽어온다.
     */
    private void loadOrders() {
        List<UserInfo> orders = getUserOrderFromAPI().getUserInfos();

        for (UserInfo order : orders) {
            userOrders.add(new UserOrder(order.getUsername(), order.getOrderNumber()));
        }
    }

    public Set<UserOrder> getUserOrders() {
        return userOrders;
    }

    public static void main(String[] args) {
        OrderManagement orderManagement = new OrderManagement();
        orderManagement.loadOrders();
        orderManagement.getUserOrders().forEach(System.out::println);
    }

    private static UserInfos getUserOrderFromAPI() {
        UserInfos userInfos = new UserInfos();
        userInfos.add(new UserInfo("A", "A001"));
        userInfos.add(new UserInfo("B", "B001"));
        userInfos.add(new UserInfo("C", "C001"));
        return userInfos;
    }
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class UserInfos {
    private List<UserInfo> userInfos = new LinkedList<>();

    public void add(UserInfo userinfo) {
        this.userInfos.add(userinfo);
    }
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class UserInfo {
    private String username;
    private String orderNumber;
}
