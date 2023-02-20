package miracle3d.base.util.observer;

@FunctionalInterface
public interface IObserver<T> {
    void update(T var1);
}
