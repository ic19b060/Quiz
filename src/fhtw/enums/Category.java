package fhtw.enums;

import com.google.gson.annotations.SerializedName;

public enum Category {


     General_Knowledge("09"), Books("10"),Film("11"),Music("12"),Musical("13"),
        Television("14"),Video("15"),Games("16"),Science("17");


     public final String beschreibung;


    Category(String info){
            beschreibung=info;
        }



    public String getBeschreibung(){
            return beschreibung;
        }



}
