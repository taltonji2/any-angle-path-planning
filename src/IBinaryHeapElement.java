package src;

public interface IBinaryHeapElement{
    boolean LessThan(IBinaryHeapElement element);
    boolean GreaterThan(IBinaryHeapElement element);
    boolean Equals(IBinaryHeapElement element);
}