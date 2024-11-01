import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task_2 {

    static Scanner sc = new Scanner(System.in);

    static List<String> addString = new ArrayList<>();
    static List<Integer> addH = new ArrayList<>();
    static List<Integer> addV = new ArrayList<>();
    static List<Integer> finalOrder;

    static int minDist = Integer.MAX_VALUE;
    static int K;

    static public void main(String[] args) {

        final int N = sc.nextInt();
        final int M = sc.nextInt();


        K = sc.nextInt();

        List<Boolean> visited = new ArrayList<>(K + 1);
        visited.add(true);

        String firmaStreet = sc.next();
        Integer firmaHouse = sc.nextInt();

        addAddress(firmaStreet, firmaHouse);

        for (int i = 0; i < K; ++i) {
            visited.add(false);
            String clientStreet = sc.next();
            Integer clientHouse = sc.nextInt();
            addAddress(clientStreet, clientHouse);
        }


        findMinDist(0, visited, 0, new ArrayList<>());

        for (int i = 0; i < finalOrder.size(); ++i) {
            System.out.println(addString.get(finalOrder.get(i)));
        }

//        System.out.println(minDist);
    }

    private static Integer findMinDist(int cur, List<Boolean> visited, Integer curDist, List<Integer> order) {
       
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
        if (order.size() == K && curDist + findDist(cur, 0) < minDist) {
            minDist = curDist + findDist(cur, 0);
            finalOrder = new ArrayList<>(order);
            return curDist + findDist(cur, 0);
        }
        return dist;
    }

    private static Integer findDist(int first, int second) {
        return Math.abs(addH.get(first) - addH.get(second)) + Math.abs(addV.get(first) - addV.get(second));
    }

    private static void addAddress(String street, Integer house) {

        if (street.charAt(0) == 'H') {
            addH.add(Integer.parseInt(street.substring(1)) - 1);
            addV.add((house + 1) / 4);
        } else {
            addH.add((house + 1) / 4);
            addV.add(Integer.parseInt(street.substring(1)) - 1);
        }

        addString.add(street + " " + (house));
    }

}
