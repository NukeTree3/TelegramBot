package com.nuketree3.example.telegrambot;

import org.springframework.stereotype.Component;


@Component
public class BotStat {
    private int startClickCount;
    private int vKClickCount;
    private int telegramClickCount;
    private int instaClickCount;
    private int sendAdminMessageClickCount;
    private int reviewClickCount;
    private int rentClickCount;

    public int getStartClickCount() { return startClickCount; }
    public int getVKClickCount() { return vKClickCount; }
    public int getTelegramClickCount() { return telegramClickCount; }
    public int getInstaClickCount() { return instaClickCount; }
    public int getSendAdminMessageClickCount() { return sendAdminMessageClickCount; }
    public int getReviewClickCount() { return reviewClickCount; }
    public int getRentClickCount() { return rentClickCount; }

    public void setStartClickCount(int startClickCount) { this.startClickCount = startClickCount; }
    public void setVKClickCount(int vKClickCount) { this.vKClickCount = vKClickCount; }
    public void setTelegramClickCount(int telegramClickCount) { this.telegramClickCount = telegramClickCount; }
    public void setInstaClickCount(int instaClickCount) { this.instaClickCount = instaClickCount; }
    public void setSendAdminMessageClickCount(int sendAdminMessageClickCount) { this.sendAdminMessageClickCount = sendAdminMessageClickCount; }
    public void setReviewClickCount(int reviewClickCount) { this.reviewClickCount = reviewClickCount; }
    public void setRentClickCount(int rentClickCount) { this.rentClickCount = rentClickCount; }
}
