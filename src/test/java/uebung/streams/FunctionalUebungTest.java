package uebung.streams;

import org.junit.jupiter.api.Test;
import uebung.Player;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class FunctionalUebungTest {

    @Test
    void findLongNames() {
        FunctionalUebung uebung = new FunctionalUebung();
        Set<String> longNamesOld = uebung.findLongNames(List.copyOf(uebung.players));
        Set<String> longNamesNew = uebung.findLongNamesRefactored(List.copyOf(uebung.players));
        assert longNamesOld.containsAll(longNamesNew);
        assert longNamesNew.size() == longNamesOld.size();
    }

    @Test
    void testCountOlder29() {
        FunctionalUebung uebung = new FunctionalUebung();
        assert uebung.countOlder29Old() == uebung.countOlder29OldNew();
    }

    @Test
    void findWho() {
        FunctionalUebung uebung = new FunctionalUebung();
        Optional<Player> foundPlayer = uebung.findWho(28);
        Optional<Player> notFoundPlayer = uebung.findWho(100);
        assert foundPlayer.isPresent();
        assert !notFoundPlayer.isPresent();
    }

    @Test
    void findAllThat() {
        FunctionalUebung uebung = new FunctionalUebung();
        Collection<Player> players = uebung.findAllThat(player -> player.getAlter() > 27);
        assert players.size() == 4;
        System.out.println(players);
    }

    @Test
    void converNames() {
        FunctionalUebung uebung = new FunctionalUebung();
        Collection<Player> players = uebung.converNames(player -> { player.setName(player.getName().toUpperCase()); return player; });
        players.forEach(p -> {
            System.out.println(p.getName());
            assert p.getName().matches("[A-Z]*");
        });
        System.out.println(players);
    }

    @Test
    void concatenatePlayers() {
        FunctionalUebung uebung = new FunctionalUebung();
        Collection<Player> players = uebung.concatenatePlayers(player -> {
            player.setAlter(player.getAlter() * 2);
            return Stream.of(player);
        });
        assert players.size() == 4;
        players.stream().map(Player::getName).collect(toList()).containsAll(List.of("richard", "lidia", "waldemar", "Iwan"));
        System.out.println(players);
    }

    @Test
    void findMaxPlayer() {
        FunctionalUebung uebung = new FunctionalUebung();
        Player iwan = uebung.findMaxPlayer(Comparator.comparing(Player::getAlter));
        Player waldemar = uebung.findMinPlayer(Comparator.comparing(Player::getAlter));
        assert iwan.getName().equals("Iwan");
        assert waldemar.getName().equals("waldemar");
        System.out.println(iwan);
        System.out.println(waldemar);
    }

    @Test
    void findGesamtAlter() {
        FunctionalUebung uebung = new FunctionalUebung();
        System.out.println("Gesamtalter: " + uebung.findGesamtAlter(Integer::sum));
        assert  uebung.findGesamtAlter((a,b) -> a+b) == uebung.addUp(uebung.players.stream().map(Player::getAlter));
    }

    @Test
    void findPlayersWithTwoLocations() {
        FunctionalUebung uebung = new FunctionalUebung();
        List<Player> playersWithTwoLocations = uebung.findPlayersWithTwoLocations(uebung.players.stream());
        assert playersWithTwoLocations.size() == 2;
        System.out.println(playersWithTwoLocations);
    }

    @Test
    void findPlayersInDresden() {
        FunctionalUebung uebung = new FunctionalUebung();
        List<Player> playersInDD = uebung.findPlayersInDresden(uebung.players.stream());
        assert playersInDD.size() == 1;
        assert playersInDD.get(0).getName().equals("waldemar");
//        System.out.println(playersInDD);
    }

    @Test
    void findPlayersStatistics() {
        FunctionalUebung uebung = new FunctionalUebung();
        List<Integer> alterBoxed = uebung.findPlayersStatistics(uebung.players.stream()).collect(toList());
        assert Arrays.equals(alterBoxed.toArray(new Integer[4]), List.of(60,66,56,156).toArray(Integer[]::new));
//        System.out.println(alterBoxed);
    }

    @Test
    void findOrderAlterOfPlayers() {
        FunctionalUebung uebung = new FunctionalUebung();
        List<Integer> sortedAges = uebung.findOrderAlterOfPlayers(uebung.players.stream());
        assert Arrays.equals(sortedAges.toArray(new Integer[4]), List.of(28,30,33,78).toArray(Integer[]::new));
        System.out.println(Arrays.toString(sortedAges.toArray(new Integer[4])));
    }

    @Test
    void findAvgAge() {
        FunctionalUebung uebung = new FunctionalUebung();
        Double value = uebung.findAvgAge(uebung.players.stream());
        assert value == 42.25;
    }

    @Test
    void findSumAge() {
        FunctionalUebung uebung = new FunctionalUebung();
        Integer value = uebung.findSumAge(uebung.players.stream());
        assert value == 169;
    }

    @Test
    void findAvgSumAndOthers() {
        FunctionalUebung uebung = new FunctionalUebung();
        IntSummaryStatistics statistics = uebung.findAvgSumAndOthers(uebung.players.stream());
        System.out.println(statistics);
    }

    /*false=[
        Player{name='richard', alter=30, orte=[Altenberg, Bonn, Berlin], birthPlace='Daugavpils'},
        Player{name='lidia', alter=33, orte=[Altenberg, Pirna, Dublin, Berlin], birthPlace='Daugavpils'},
        Player{name='Iwan', alter=78, orte=[Daugavpils, Altenberg, Pirna, Berlin], birthPlace='Daugavpils'}]
    true=[Player{name='waldemar', alter=28, orte=[Altenberg, Dresden], birthPlace='Daugavpils'}]*/
    @Test
    void testDetermineYoungPlayers_partitioningBy() {
        FunctionalUebung uebung = new FunctionalUebung();
        Map<Boolean, List<Player>> playersByCity = uebung.partitionByYoungPlayers(uebung.players.stream());
        playersByCity.entrySet().forEach(System.out::println);
    }

    /*Daugavpils=[Player{name='Iwan', alter=78, orte=[Daugavpils, Altenberg, Pirna, Berlin], birthPlace='Daugavpils'}]
    Altenberg=[
        Player{name='richard', alter=30, orte=[Altenberg, Bonn, Berlin], birthPlace='Daugavpils'},
        Player{name='lidia', alter=33, orte=[Altenberg, Pirna, Dublin, Berlin], birthPlace='Daugavpils'},
        Player{name='waldemar', alter=28, orte=[Altenberg, Dresden], birthPlace='Daugavpils'}
    ]*/
    @Test
    void testGroupByCity_groupBy() {
        FunctionalUebung uebung = new FunctionalUebung();
        Map<String, List<Player>> playersByCity = uebung.groupByCity(uebung.players.stream());
        playersByCity.entrySet().forEach(System.out::println);
    }

    /*richard=3
    Iwan=4
    lidia=4
    waldemar=2*/
    @Test
    void testFindPlayerWithMostCity() {
        FunctionalUebung uebung = new FunctionalUebung();
        Map<String, Integer> playersByCity = uebung.findPlayerWithMostCity(uebung.players.stream());
        playersByCity.entrySet().forEach(System.out::println);
    }

    /*
   false=[
       Player{name='richard', alter=30, orte=[Altenberg, Bonn, Berlin], birthPlace='Daugavpils'},
       Player{name='lidia', alter=33, orte=[Altenberg, Pirna, Dublin, Berlin], birthPlace='Daugavpils'},
       Player{name='Iwan', alter=78, orte=[Daugavpils, Altenberg, Pirna, Berlin], birthPlace='Daugavpils'}
   ]
   true=[Player{name='waldemar', alter=28, orte=[Altenberg, Dresden], birthPlace='Daugavpils'}]
    */
    @Test
    void testPlayersWithTwoCities_PartitionBy() {
        FunctionalUebung uebung = new FunctionalUebung();
        Map<Boolean, List<Player>> playersWithTwoCities = uebung.partitionPLayersWithTwoCities(uebung.players.stream());
        playersWithTwoCities.entrySet().forEach(System.out::println);
    }

    /*
    richard=3
    Iwan=4
    lidia=4
    waldemar=2
     */
    @Test
    void testGroupPlayersWithAmountCities_GroupByCollector() {
        FunctionalUebung uebung = new FunctionalUebung();
        HashMap<String, Integer> playersWithTwoCities = uebung.groupPLayersWithAmountOfCities(uebung.players.stream());
        playersWithTwoCities.entrySet().forEach(System.out::println);
    }

    /*
    richard=3
    Iwan=4
    lidia=4
    waldemar=2
     */
    @Test
    void testGroupPlayersNameAndByCity_toMap() {
        FunctionalUebung uebung = new FunctionalUebung();
        Map<String, Integer> playersWithTwoCities = uebung.groupPlayersNameAndByCity(uebung.players.stream());
        playersWithTwoCities.entrySet().forEach(System.out::println);
    }
}