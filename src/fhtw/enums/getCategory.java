package fhtw.enums;

import com.google.gson.annotations.SerializedName;

public class getCategory {

    public enum Category {

        @SerializedName("Books")
        Books(10),
        @SerializedName("Film")
        Film(11),
        @SerializedName("Music")
        Music(12),
        @SerializedName("Musical & Theatres")
        MUSICAL_AND_THEATRES(13),
        @SerializedName("Television")
        Television(14),
        @SerializedName("Video Games")
        VIDEO_GAMES(15),
        @SerializedName("Board Games")
        BOARD_GAMES(16),
        @SerializedName("Science & Nature")
        SCIENCE_AND_NATURE(17),
        @SerializedName("Computers")
        COMPUTERS(18),
        @SerializedName("Mathematics")
        MATHEMATICS(19),
        @SerializedName("Mythology")
        MYTHOLOGY(20),
        @SerializedName("Sports")
        SPORTS(21),
        @SerializedName("Geography")
        GEOGRAPHY(22),
        @SerializedName("History")
        HISTORY(23),
        @SerializedName("Politics")
        POLITICS(24),
        @SerializedName("Art")
        ART(25),
        @SerializedName("Celebreties")
        CELEBRETIES(26),
        @SerializedName("Animals")
        ANIMALS(27),
        @SerializedName("Comics")
        COMICS(28),
        @SerializedName("Vehicles")
        VEHICLES(29),
        @SerializedName("Gadgets")
        GADGETS(30),
        @SerializedName("Japanese anime & mangas")
        JAPANESE(31),
        @SerializedName("Cartoon & Animation")
        CARTOON(32);


        private int value;


        Category(int value) {
            this.value = value;
        }

        public int value(){
            return value;
            }


    }
}


