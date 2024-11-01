import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task_2 {

    static Scanner sc = new Scanner(System.in);

    static List<Integer> adrH = new ArrayList<>();
    static List<Integer> adrV = new ArrayList<>();
    static List<Integer> finalOrder;

    static int minDist = Integer.MAX_VALUE;
    static int K;

    static public void main(String[] args) {

        final int N = sc.nextInt();
        final int M = sc.nextInt();

        String firmaStreet = sc.next();
        Integer firmaHouse = sc.nextInt();

        addAddress(firmaStreet, firmaHouse);

        K = sc.nextInt();

        List<Boolean> visited = new ArrayList<>(K + 1);
        visited.add(true);

        for (int i = 0; i < K; ++i) {
            visited.add(false);
            String clientStreet = sc.next();
            Integer clientHouse = sc.nextInt();
            addAddress(clientStreet, clientHouse);
        }


        findMinDist(0, visited, 0, new ArrayList<>());

        for (int i = 0; i < finalOrder.size(); ++i) {
            System.out.println(finalOrder.get(i));
        }

//        System.out.println(minDist);
    }

    private static Integer findDist(int first, int second) {
        return Math.abs(adrH.get(first) - adrH.get(second)) + Math.abs(adrV.get(first) - adrV.get(second));
    }

    private static Integer findMinDist(int cur, List<Boolean> visited, Integer curDist, List<Integer> order) {
        Integer dist = Integer.MAX_VALUE;
        for (int i = 1; i < adrH.size(); i++) {
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


    private static void addAddress(String street, Integer house) {

        if (street.charAt(0) == 'H') {
            adrH.add(Integer.parseInt(street.substring(1)) - 1);
            adrV.add((house + 1) / 4);
        } else {
            adrH.add((house + 1) / 4);
            adrV.add(Integer.parseInt(street.substring(1)) - 1);
        }
    }

}
