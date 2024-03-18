package pacman_v0;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio 
{
	private static Clip clip ;
	private static Clip sound ;

	public static void startClip(URL wav)
    {       
		
		try 
		{	
			
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(wav);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
    }
	public static void closeClip() 
    {       
		try 
		{
			clip.close();
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
    }
	public static void pauseClip() 
    {       
		try 
		{
			clip.stop();
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
    }
	public static void resumeClip() 
    {       
		try 
		{
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
    }
	public static void startSound(URL wav) 
    {       
		try 
		{
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(wav);
            sound = AudioSystem.getClip();
            sound.open(audioIn);
            sound.start();
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
    }
}
