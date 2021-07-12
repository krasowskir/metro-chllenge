package uebung;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

public class GroupByAccumulator<T, K> implements Collector<Player, Map<String, List<Player>>, Map<String, List<Player>>> {

    private final static Set<Characteristics> characteristics = new HashSet<>();
    static {
        characteristics.add(Characteristics.IDENTITY_FINISH);
    }

    public GroupByAccumulator() {
    }

    @Override
    public Supplier<Map<String, List<Player>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, List<Player>>, Player> accumulator() {
        return (a, b) -> {
            a.putIfAbsent(b.getBirthPlace(), new ArrayList<>());
            a.computeIfPresent(b.getBirthPlace(), (key , val) -> {
                val.add(b);
                return val;
            });
        };
    }

    @Override
    public BinaryOperator<Map<String, List<Player>>> combiner() {
        return (a, b) -> {
            b.forEach((elem, listPlayer) -> {
                a.putIfAbsent(elem, listPlayer);
                a.computeIfPresent(elem, (key, val) -> {
                    val.addAll(listPlayer);
                    return val;
                });
            });
            return b;
        };
    }

    @Override
    public Function<Map<String, List<Player>>, Map<String, List<Player>>> finisher() {
        return a -> a;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }
}
