package com.l08gr02.zelda.viewers.elements;

import com.l08gr02.zelda.models.elements.Element;
import com.l08gr02.zelda.viewers.GameViewer;

public interface ElementViewer<T extends Element> {
    public void draw(T el, GameViewer gui);
}
