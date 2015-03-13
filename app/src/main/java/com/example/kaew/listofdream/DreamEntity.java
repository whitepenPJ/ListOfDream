package com.example.kaew.listofdream;

import com.example.kaew.listofdream.Standard.Entity;
import com.example.kaew.listofdream.Standard.AnnotationEntity;

import java.io.Serializable;

/**
 * Created by Kaew on 3/12/2015.
*/
public class DreamEntity extends Entity {
    @AnnotationEntity(PrimaryKey = true)
    private int _id;
    private boolean _achieve;
    private String _dream, _comment;

    public DreamEntity(int _id, boolean _achieve, String _dream, String _comment) {
        super();
        this._id = _id;
        this._achieve = _achieve;
        this._dream = _dream;
        this._comment = _comment;
    }

    public DreamEntity() {
        super();
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public boolean is_achieve() {
        return _achieve;
    }

    public void set_achieve(boolean _achieve) {
        this._achieve = _achieve;
    }

    public String get_dream() {
        return _dream;
    }

    public void set_dream(String _dream) {
        this._dream = _dream;
    }

    public String get_comment() {
        return _comment;
    }

    public void set_comment(String _comment) {
        this._comment = _comment;
    }

}
