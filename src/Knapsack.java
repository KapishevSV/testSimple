import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA Community
 * KapishevSV
 * 21.03.2021
 * 12:29
 */
public class Knapsack {
    private List<Item> bestItem = null;
    private int maxW = 5;
    private int maxPrice;
    private int n;

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        List<Item> items = new ArrayList<>();
        items.add(new Item("2-40", 2, 40));
        items.add(new Item("3-30", 3, 30));
        items.add(new Item("1-20", 1, 20));
        items.add(new Item("4-25", 4, 25));
        //items.add(new Item("Котелок", 1, 500));

        knapsack.makeAllSets(items);
        knapsack.getBestList();
    }

    //вычисляет вес комбинации предметов
    private int calcWeight(List<Item> items){
        int currentW = 0;
        for (Item entry: items) {
            currentW += entry.getWeight();
        }
        return currentW;
    }
    //вычисляет стоимость комбинации предметов
    private int calcPrice(List<Item> items){
        int currentPrice = 0;
        for (Item entry: items) {
            currentPrice += entry.getCost();
        }
        return currentPrice;
    }
    //сравнивает два набора предметов
    private void chekSet(List<Item> items){
        if(bestItem == null){
            if(calcWeight(items) <= maxW){
                bestItem = items;
                maxPrice = calcPrice(items);
            }
        } else {
            if(calcWeight(items) <= maxW && calcPrice(items) > maxPrice){
                bestItem = items;
                maxPrice = calcPrice(items);
            }
        }
    }
    //ищет лучший набор пердметов
    public void makeAllSets(List<Item> items){
        if(items.size() > 0) chekSet(items);

        for (int i = 0; i < items.size(); i++) {
            List<Item> newSet = new ArrayList<>(items);
            newSet.remove(i);
            makeAllSets(newSet);
        }
    }
    //возвращает лучший набор предметов
    public void getBestList(){
        for (Item entry: bestItem) {
            System.out.println(entry.getName());
        }
    }
}

class Item{
    private String name;
    private int weight;
    private int cost;

    public Item(String name, int weight, int cost) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" + name + '}';
    }
}