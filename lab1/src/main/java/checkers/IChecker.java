package checkers;

@FunctionalInterface
public interface IChecker<T> {
    public boolean check(T p, T value);
}
