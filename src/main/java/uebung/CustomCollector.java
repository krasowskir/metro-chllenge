//package uebung;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.function.BiConsumer;
//import java.util.function.BinaryOperator;
//import java.util.function.Function;
//import java.util.function.Supplier;
//import java.util.stream.Collector;
//
//public class MyCustomCollector<T, K> implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<String>>> {
//
//    private Map<T, List<T>> values;
//
//    @Override
//    public Supplier<Map<K, List<T>>> supplier() {
//        return HashMap::new;
//    }
//
//    @Override
//    public BiConsumer<Map<K, List<T>>, T> accumulator() {
//        (a, b) ->{
//            Set<T> keys = ;
//            keys.stream()
//                    .filter(e -> )
//        }
//        return null;
//    }
//
//    @Override
//    public BinaryOperator<Map<K, List<T>>> combiner() {
//        return null;
//    }
//
//    @Override
//    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
//        return null;
//    }
//
//    @Override
//    public Set<Characteristics> characteristics() {
//        return null;
//    }
//}
