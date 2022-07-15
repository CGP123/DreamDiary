package com.example.dreamdiary.cards;

public class MainWindowCard {
    private String cardText;
    private String cardImageName;

    public MainWindowCard(String cardText, String cardImageName) {
        this.cardText = cardText;
        this.cardImageName = cardImageName;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public String getCardImageName() {
        return cardImageName;
    }

    public void setCardImageName(String cardImageName) {
        this.cardImageName = cardImageName;
    }

}
