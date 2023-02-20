package miracle3d.base.util.observer;

public interface IPublishSubscribe<T> {
    void attach(IObserver<T> var1);

    boolean detach(IObserver<T> var1);

    void detachAll();

    void onStateChange(T var1);
}
