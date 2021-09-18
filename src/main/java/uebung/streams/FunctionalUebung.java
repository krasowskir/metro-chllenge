package uebung.streams;

import uebung.Player;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalUebung {
    public Collection<Player> players = Arrays.asList(
            new Player("richard",30,new String[]{"Altenberg", "Bonn", "Berlin"}, "Daugavpils"),
            new Player("lidia",33, new String[]{"Altenberg", "Pirna", "Dublin", "Berlin"}, "Daugavpils"),
            new Player("waldemar",28, new String[]{"Altenberg", "Dresden"}, "Daugavpils"),
            new Player("Iwan",78, new String[]{"Daugavpils","Altenberg", "Pirna", "Berlin"},  "Daugavpils"));

    public Set<String> findLongNames(List<Player> players) {
        Set<String> playerNames = new HashSet<>();
        for (Player player : players) {
            if (player.getAlter() > 60) {
                String name = player.getName();
                playerNames.add(name);
            }
        }
        return playerNames;
    }

    public Set<String> findLongNamesRefactored(List<Player> players){
        return players.stream()
                .filter(player -> player.getAlter() > 60)
                .map(Player::getName)
                .collect(Collectors.toSet());
    }

    public int test1(){
        int count = 0;
        Iterator<Player> playerIterator = players.iterator();
        while(playerIterator.hasNext()){
            Player tmpPlayer = playerIterator.next();
            if (tmpPlayer.getAlter() > 29){
                count++;
            }
        }
        return count;
    }

    public int testFunc(){
        return (int) players.stream().filter(elem -> elem.getAlter() > 28).count();
    }

    public void findWho(int alter){
        players.stream()
                .filter(elem -> elem.getAlter() == alter)
                .findFirst()
                .ifPresentOrElse(
                        elem -> { System.out.println("player: " + elem.getName() + " alter: " + elem.getAlter());},
                        () -> {
                            Player tmp = new Player("not found",0,new String[]{}, "nowhere");
                            System.out.println("player: " + tmp.getName() + " alter: " + tmp.getAlter());
                        }
                );

    }

    public Collection<Player> findAllThat(Predicate<Player> playerCondition){
        return players.stream()
                .filter(playerCondition)
                .collect(Collectors.toList());
    }

    public Collection<Player> converNames(Function<Player, Player> convertPlayer){
        return players.stream()
                .map(convertPlayer)
                .collect(Collectors.toList());
    }

    public Collection<Player> concatenatePlayers(Function<Player, Stream<Player>> flatFunct){
        return players.stream()
                .flatMap(flatFunct)
                .collect(Collectors.toList());
    }

    public Player findMaxPlayer(Comparator<Player> comparePlayer){
        return players.stream()
                .max(comparePlayer).get();
    }
    public Player findMinPlayer(Comparator<Player> comparePlayer){
        return players.stream()
                .min(comparePlayer).get();
    }

    public int findGesamtAlter(BinaryOperator<Integer> fasseAlterZusammen){
        return players.stream()
                .map(Player::getAlter)
                .reduce(0, fasseAlterZusammen);
    }

    public Integer addUp(Stream<Integer> numbers){
        return numbers
                .reduce(0, Integer::sum);
    }


    public List<Player> findPlayersWithTwoLocations(Stream<Player> players){
        return players
                .filter(player -> player.getAlter() > 30)
                .filter(player -> player.getOrte().length > 2)
                .collect(Collectors.toList());
    }

    public List<Player> findPlayersInDresden(Stream<Player> players){
        return players
                .filter(player -> player.getOrte().length > 1)
                .filter(player -> Arrays.asList(player.getOrte()).contains("Dresden"))
                .collect(Collectors.toList());
    }

    public Stream<Integer> findPlayersStatistics(Stream<Player> players){
        ToIntFunction<Integer> statisticsFunction = new ToIntFunction<Integer>() {
            @Override
            public int applyAsInt(Integer value) {
                return value.intValue();
            }
        };

        IntUnaryOperator myZweimalOper = new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return operand + operand;
            }
        };

        IntFunction<Integer> myBoxingFunc = value -> (Integer) value;
        BinaryOperator<Integer> myBinOper = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return null;
            }
        };
        return players
                .map(Player::getAlter)
                .mapToInt(statisticsFunction)
                .map(myZweimalOper)
                .mapToObj(myBoxingFunc);
    }

    public List<Integer> findOrderAlterOfPlayers(Stream<Player> players){
        return players
                .map(Player::getAlter)
                .sorted()
                .collect(Collectors.toList());
    }

    public Double findAvgAge(Stream<Player> players){
        ToIntFunction<Player> avgFunc = player -> player.getAlter();
        return players.collect(Collectors.averagingInt(avgFunc));
    }

    public Integer findSumAge(Stream<Player> players){
        ToIntFunction<Player> alterOfPlayers = Player::getAlter;
        players.mapToInt(Player::getAlter).sum();

        return players.collect(Collectors.summingInt(alterOfPlayers));
    }

    public IntSummaryStatistics findAvgSumAndOthers(Stream<Player> players){
        return players.collect(Collectors.summarizingInt(Player::getAlter));
    }

    public Map<Boolean, List<Player>> determineYoungPlayers(Stream<Player> players){
        Predicate<Player> isYoung = player -> player.getAlter() < 30;
        return players.collect(Collectors.partitioningBy(isYoung));
    }

    public Map<String, List<Player>> groupByCity(Stream<Player> players){
        Function<Player, String> gruppiereNachCities = player -> player.getOrte()[0];
        return players.collect(Collectors.groupingBy(gruppiereNachCities));
    }

    public Map<String, Integer> findPlayerWithLongestCity(Stream<Player> players){
//        Function<Player, String[]> toCities = player -> player.getOrte();
        ToIntFunction<Player> toCities = player -> player.getOrte().length;
        return players
                .collect(Collectors.groupingBy(Player::getName,
                        Collectors.mapping(p -> p, Collectors.summingInt(toCities))));
    }
}
