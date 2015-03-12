package com.example.kaew.listofdream;

/**
 * Created by Kaew on 3/12/2015.
 */
public class DreamEntity {
    private int _id;
    private boolean _achieve;
    private String _dream, _comment;

    public DreamEntity(int id, boolean achieve, String dream, String comment)
    {
        this._id = id;
        this._achieve = achieve;
        this._dream = dream;
        this._comment = comment;
    }

    public DreamEntity(){}

    public int getID(){return _id;}
    public boolean getAchieve(){return _achieve;}
    public String getDream(){return _dream;}
    public String getComment(){return _comment;}

    public void setID(int id){ _id = id;}
    public void setAchieve(boolean achieve){_achieve = achieve;}
    public void setDream(String dream){_dream = dream;}
    public void setComment(String comment){_comment = comment;}
}
