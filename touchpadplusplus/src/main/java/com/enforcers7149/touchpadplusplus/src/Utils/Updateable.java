package com.enforcers7149.touchpadplusplus.src.Utils;

public interface Updateable {
    default void updateInput() {}
    default void startInput() {}
    default void stopInput() {}
}
