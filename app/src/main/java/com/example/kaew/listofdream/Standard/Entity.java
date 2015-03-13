package com.example.kaew.listofdream.Standard;

import java.io.Serializable;

/**
 * Created by Kaew on 3/13/2015.
 */
public class Entity implements Serializable {
    private Enum.FORM_MODE _formMode;

    public Entity() {
        this._formMode = Enum.FORM_MODE.ADD;
    }

    public Enum.FORM_MODE get_formMode() {
        return _formMode;
    }

    public void set_formMode(Enum.FORM_MODE _formMode) {
        this._formMode = _formMode;
    }
}
