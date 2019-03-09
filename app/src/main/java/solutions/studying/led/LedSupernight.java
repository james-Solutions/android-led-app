package solutions.studying.led;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.Serializable;

public class LedSupernight implements Serializable {
    private int redBrightness;
    private int blueBrightness;
    private int greenBrightness;
    private int redPin=4;
    private int bluePin=27;
    private int greenPin=17;
    private String className;
    private String userName;
    private String password;
    private String host;
    private Integer port = 22;

    public LedSupernight(){
        this.blueBrightness = 0;
        this.redBrightness = 0;
        this.greenBrightness = 0;
    }

    public LedSupernight(int redBrightness, int greenBrightness, int blueBrightness) {
        this.redBrightness = redBrightness;
        this.greenBrightness = greenBrightness;
        this.blueBrightness = blueBrightness;
    }

    public LedSupernight(String name){
        className = name;
    }

    public void setName(String name){
        className = name;
    }

    public String getName(){
        return className;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setIp(String ipAddress){
        this.host = ipAddress;
    }

    public String getIp(){
        return host;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void testConnection(){

    }

    public int getBlueBrightness() {
        return blueBrightness;
    }

    public void setBlueBrightness(int blueBrightness) {
        if (blueBrightness >= 0 && blueBrightness <= 255) {
            this.blueBrightness = blueBrightness;
        }
    }

    public int getRedBrightness() {
        return redBrightness;
    }

    public void setRedBrightness(int redBrightness) {
        if (redBrightness >= 0 && redBrightness <= 255) {
            this.redBrightness = redBrightness;
        }
    }

    public int getGreenBrightness() {
        return greenBrightness;
    }

    public void setGreenBrightness(int greenBrightness) {
        if (greenBrightness >= 0 && greenBrightness <= 255) {
            this.greenBrightness = greenBrightness;
        }
    }

    //Red, Green, Blue values
    public void setRGB(int red, int green, int blue) {
        if (red >= 0 && red <= 255) this.redBrightness = red;
        if (green >= 0 && green <= 255) this.greenBrightness = green;
        if (blue >= 0 && blue <= 255) this.blueBrightness = blue;
        sendRGBToPi();

    }

    public String getHexColor() {
        return(String.format("#%02x%02x%02x", redBrightness, greenBrightness, blueBrightness));
    }

    public void setBluePin(int bluePin) {
        this.bluePin = bluePin;
    }

    public int getBluePin() {
        return bluePin;
    }

    public void setGreenPin(int greenPin) {
        this.greenPin = greenPin;
    }

    public int getGreenPin() {
        return greenPin;
    }

    public void setRedPin(int redPin) {
        this.redPin = redPin;
    }

    public int getRedPin() {
        return redPin;
    }

    public void sendRedToPi(){
        new redSSH().execute();
    }

    private class redSSH extends AsyncTask<Void, Void, String> implements Serializable {

        private  final String TAG = LedSupernight.redSSH.class.getName();
        @Override
        protected String doInBackground(Void... params){
            Log.d(TAG, "On doInBackground...");
            try{
                Log.d(TAG, "Entering try");
                JSch jsch = new JSch();
                Session session = jsch.getSession(userName, host, port);
                session.setPassword(password);
                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.setTimeout(10000);
                session.connect(10000);
                ChannelExec channel = (ChannelExec)session.openChannel("exec");
                channel.setCommand("pigs p " + redPin + " " + redBrightness);
                channel.connect(10000);
                channel.disconnect();
            }
            catch(Exception e){
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result){

            Log.d(TAG,result);
        }

        @Override
        protected void onPreExecute(){
            Log.d(TAG, "On preExceute...");
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    public void sendBlueToPi(){
        new blueSSH().execute();
    }

    private class blueSSH extends AsyncTask<Void, Void, String> implements Serializable {

        private  final String TAG = LedSupernight.blueSSH.class.getName();
        @Override
        protected String doInBackground(Void... params){
            Log.d(TAG, "On doInBackground...");
            try{
                Log.d(TAG, "Entering try");
                JSch jsch = new JSch();
                Session session = jsch.getSession(userName, host, port);
                session.setPassword(password);
                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.setTimeout(10000);
                session.connect(10000);
                ChannelExec channel = (ChannelExec)session.openChannel("exec");
                channel.setCommand("pigs p " + bluePin + " " + blueBrightness);
                channel.connect(10000);
                channel.disconnect();
            }
            catch(Exception e){
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result){

            Log.d(TAG,result);
        }

        @Override
        protected void onPreExecute(){
            Log.d(TAG, "On preExceute...");
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    public void sendGreenToPi(){
        new blueSSH().execute();
    }

    private class greenSSH extends AsyncTask<Void, Void, String> implements Serializable {

        private  final String TAG = LedSupernight.greenSSH.class.getName();
        @Override
        protected String doInBackground(Void... params){
            Log.d(TAG, "On doInBackground...");
            try{
                Log.d(TAG, "Entering try");
                JSch jsch = new JSch();
                Session session = jsch.getSession(userName, host, port);
                session.setPassword(password);
                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.setTimeout(10000);
                session.connect(10000);
                ChannelExec channel = (ChannelExec)session.openChannel("exec");
                channel.setCommand("pigs p " + greenPin + " " + greenBrightness);
                channel.connect(10000);
                channel.disconnect();
            }
            catch(Exception e){
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result){

            Log.d(TAG,result);
        }

        @Override
        protected void onPreExecute(){
            Log.d(TAG, "On preExceute...");
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    public void sendRGBToPi() {
        new rgbSSH().execute();
    }

    private class rgbSSH extends AsyncTask<Void, Void, String> implements Serializable {

        //private  final String TAG = LedSupernight.greenSSH.class.getName();
        private final String TAG = LedSupernight.rgbSSH.class.getName();
        @Override
        protected String doInBackground(Void... params){
            Log.d(TAG, "On doInBackground...");
            String script = "pigs p " + redPin + " " + redBrightness +
                    ";pigs p " + bluePin + " " + blueBrightness +
                    ";pigs p " + greenPin + " " + greenBrightness;
            try{
                Log.d(TAG, "Entering try");
                JSch jsch = new JSch();
                Session session = jsch.getSession(userName, host, port);
                session.setPassword(password);
                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.setTimeout(10000);
                session.connect(10000);
                ChannelExec channel = (ChannelExec)session.openChannel("exec");
                channel.setCommand(script);
                channel.connect(10000);
                channel.disconnect();
                Log.d(TAG, "Completed Try");
            }
            catch(Exception e){
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            }
            Log.d(TAG, "Completed");
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result){

            Log.d(TAG,result);
        }

        @Override
        protected void onPreExecute(){
            Log.d(TAG, "On preExceute...");
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
