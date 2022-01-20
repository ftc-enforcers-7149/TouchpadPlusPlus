package com.enforcers7149.touchpadplusplus.src.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class TouchpadHandler {

    private ArrayList<Updateable> objects;

    public TouchpadHandler(Updateable... updateables) {

        objects = new ArrayList<Updateable>();
        addInputs(updateables);
    }

    public void addInputs(Updateable... updateables) {
        objects.addAll(Arrays.asList(updateables));
    }

    public void addInputs(ArrayList<Updateable> objects) {
        this.objects = objects;
    }

    public void startInputs() {
        for (Updateable i : objects) i.startInput();
    }
    public void updateInputs() {
        for (Updateable i : objects) i.updateInput();
    }
    public void stopInputs() {
        for (Updateable i : objects) i.stopInput();
    }

}
