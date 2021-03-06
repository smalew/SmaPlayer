/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smaplayer;

import fileClasses.Mp3;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javax.swing.ImageIcon;
import javazoom.jlgui.basicplayer.BasicPlayerListener;


public class SmaPlayer extends BasicPlayer{
    
    //Переменные статуса песни.
    private String playedSong;
    private double volumeValue;
    
    //Переменные, которые зависят от статуса песни.
    private BasicPlayer player = new BasicPlayer();
    private JLabel songName;
    private JLabel songTime;
    private JButton btnPlayPause;
    
    
    
    public void setBasicPlayerListener(BasicPlayerListener event){
        player.addBasicPlayerListener(event);
    }
    
    public String getPlayedSongName(){
        return this.playedSong;
    }
    
    //Передаем переменные для изменения UI
    public void setUIElements(JLabel label, JButton pl_pa){
        this.songName = label;
        this.btnPlayPause = pl_pa;
    }
    
   
    public void play(String patch){
        try {
            //Если музыка была поставлена на паузу, то продолжаем воспроизведение
            if (playedSong != null && playedSong.equals(patch) && player.getStatus() == BasicPlayer.PAUSED){
                player.resume();
                btnPlayPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/play.png")));
                return;
            }
            
            //Если играла музыка, то ставим на паузу
            if (playedSong != null && playedSong.equals(patch) && player.getStatus() == BasicPlayer.PLAYING){
                player.pause();
                btnPlayPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/pause.png")));
                return;
            }                
            
            //Иначе включаем новую мелодию.
            btnPlayPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smaplayer/images/play.png")));
            
            playedSong = patch;
            File mp3 = new File(playedSong);
            
            Mp3 song = new Mp3(mp3.getName(), mp3.getPath());
            songName.setText(song.toString());
            
            player.open(mp3);            
            player.play();
            this.setVolume((int)volumeValue, 200);
            
        } catch (BasicPlayerException ex) {
            Logger.getLogger(SmaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void pause(){
        try {
            if (player.getStatus() == BasicPlayer.PLAYING)
                player.pause();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(SmaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void stop(){
        try {
            player.stop();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(SmaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setVolume(int currentVolume, int maxVolume){
        this.volumeValue = (double) currentVolume;
        
        try {
            if (currentVolume == 0)
                player.setGain((double) currentVolume);
            else
                player.setGain(calcVolume(currentVolume, maxVolume));
        } catch (BasicPlayerException ex) {
            Logger.getLogger(SmaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getVolume(){
        return (int)this.volumeValue;
    }  
    private double calcVolume(int currentVolume, int maxVolume){
        double result = (double) currentVolume/maxVolume;
        return result;
    }    
    public void changePan(int panValue){
        double result = (double) panValue;
        try {
            if (panValue >= 100){
                result = (result - 100)/100;
            }
            else{
                result = -(100 - result)/100;
            }
            
            player.setPan(result);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(SmaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void jump(long bytes) {
        try {
            player.seek(bytes);
            this.setVolume((int)volumeValue, 200);// устанавливаем уровень звука
        } catch (BasicPlayerException ex) {
            Logger.getLogger(SmaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getTime(long time){
        String result = "";
        int hour, minute, second;
        
        hour = (int) (time / 60 / 60);
        minute = (int)((time - hour * 60 * 60) / 60);
        second = (int) time - (minute * 60);
        
        if (hour <= 9) result = "0"+ hour;
        else result = ""+ hour;
        
        if (minute <= 9) result += ":0"+ minute;
        else result += ":"+ minute;
        
        if (second <= 9) result += ":0"+ second;
        else result += ":"+ second;
        
        return result;
    }
}
