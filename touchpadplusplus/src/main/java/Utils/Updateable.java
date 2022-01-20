package Utils;

public interface Updateable {
    default void updateInput() {}
    default void startInput() {}
    default void stopInput() {}
}
