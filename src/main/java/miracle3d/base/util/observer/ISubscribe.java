package miracle3d.base.util.observer;

@FunctionalInterface
public interface ISubscribe<T> {
    void attach(IObserver<T> var1);
}
