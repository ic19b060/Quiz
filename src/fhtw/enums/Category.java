package fhtw.enums;

import com.google.gson.annotations.SerializedName;



    public enum Category {

        @SerializedName("General Knowledge")
        General_Knowledge(9),
        @SerializedName("Books")
        Books(10),
        @SerializedName("Film")
        Film(11),
        @SerializedName("Music")
        Music(12),
        @SerializedName("Musical & Theatres")
        Musical_and_Theatre(13),
        @SerializedName("Television")
        Television(14),
        @SerializedName("Video Games")
        VideoGames(15),
        @SerializedName("Board Games")
        BoardGames(16),
        @SerializedName("Science & Nature")
        Science_and_Nature(17),
        @SerializedName("Computers")
        Computers(18),
        @SerializedName("Mathematics")
        Mathematics(19),
        @SerializedName("Mythology")
        Mythology(20),
        @SerializedName("Sports")
        Sports(21),
        @SerializedName("Geography")
        Geography(22),
        @SerializedName("History")
        History(23),
        @SerializedName("Politics")
        Politics(24),
        @SerializedName("Art")
        Art(25),
        @SerializedName("Celebreties")
        Celebreties(26),
        @SerializedName("Animals")
        Animals(27),
        @SerializedName("Comics")
        Comics(28),
        @SerializedName("Vehicles")
        Vehicles(29),
        @SerializedName("Gadgets")
        Gadgets(30),
        @SerializedName("Japanese anime & mangas")
        Japanese_Anime_Manga(31),
        @SerializedName("Cartoon & Animation")
        Cartoon_and_Animation(32);

        private int value;

        Category(int value) {
            this.value = value;
        }

        public int getValue(){
            return value;
        }

    }



