package tomiks.socketiotest.http.algorithms;

import org.springframework.beans.factory.annotation.Value;
import tomiks.socketiotest.http.model.Order;

import java.util.ArrayList;
import java.util.List;


public class Algorithms {

    private List<Integer> addH;
    private List<Integer> addV;
    private List<Integer> finalOrder;

    private int minDist = Integer.MAX_VALUE;
    private int orderListSize;

    @Value("${constants.office.street}")
    private String firmaStreet;

    @Value("${constants.office.house_number}")
    private Integer firmaHouse;

    public Integer getMileage(List<Order> orderList, int K) {

        addH = new ArrayList<>();
        addV = new ArrayList<>();

        addAddress(firmaStreet, firmaHouse);

        int mileage = 0;

        for (int i = 0; i < K; ++i) {
            Order order = orderList.get(i);
            String clientStreet = order.getStreet();
            Integer clientHouse = order.getHouse_number();

            addAddress(clientStreet, clientHouse);

            mileage += findDist(i, i + 1);
        }

        mileage += findDist(K, 0);

        return mileage;
    }

    public void sortOrderList(List<Order> orderList) {

        addH = new ArrayList<>();
        addV = new ArrayList<>();

        long minPriority = Long.MAX_VALUE;

        orderListSize = orderList.size();

        addAddress(firmaStreet, firmaHouse);

        List<Boolean> visited = new ArrayList<>(orderListSize + 1);
        visited.add(true);

        for (int i = 0; i < orderListSize; ++i) {
            visited.add(false);
            Order order = orderList.get(i);
            String clientStreet = order.getStreet();
            Integer clientHouse = order.getHouse_number();
            addAddress(clientStreet, clientHouse);

            minPriority = Math.min(minPriority, order.getPriority());
        }

        findMinDist(0, visited, 0, new ArrayList<>());

        for (long i = 0; i < finalOrder.size(); ++i) {
            Order order = orderList.get(finalOrder.get((int)i));
            order.setPriority(minPriority + i);
            orderList.set(finalOrder.get((int)i), order);
        }
    }

    private Integer findMinDist(int cur, List<Boolean> visited, Integer curDist, List<Integer> order) {

        Integer dist = Integer.MAX_VALUE;
        for (int i = 1; i < addH.size(); i++) {
            if (visited.get(i)) {
                continue;
            }
            List<Boolean> newVisited = new ArrayList<>(visited);
            newVisited.set(i, true);
            List<Integer> newOrder = new ArrayList<>(order);
            newOrder.add(i);
            int x = findMinDist(i, newVisited, curDist + findDist(cur, i), newOrder);
            if (x < dist) {
                dist = x;
            }
        }

        if (order.size() == orderListSize && curDist + findDist(cur, 0) < minDist) {
            minDist = curDist + findDist(cur, 0);
            finalOrder = new ArrayList<>(order);
            return curDist + findDist(cur, 0);
        }

        return dist;
    }

    private Integer findDist(int first, int second) {
        return Math.abs(addH.get(first) - addH.get(second)) + Math.abs(addV.get(first) - addV.get(second));
    }

    private void addAddress(String street, Integer house) {

        if (street.charAt(0) == 'H') {
            addH.add(Integer.parseInt(street.substring(1)) - 1);
            addV.add((house + 1) / 4);
        } else {
            addH.add((house + 1) / 4);
            addV.add(Integer.parseInt(street.substring(1)) - 1);
        }

    }


}
