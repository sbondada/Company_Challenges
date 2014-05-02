package Spotify_Challenge;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

class ZipfSongInfo1 implements Comparable<ZipfSongInfo1>
{
	String Name;
	Float Quality;
	public ZipfSongInfo1(String name,Float Quality )
	{
		this.Name=name;
		this.Quality=Quality;
	}
	public int compareTo(ZipfSongInfo1 S2){
		if (this.Quality.equals(S2.Quality))
		{
			return 1;
		}
		return (-(this.Quality).compareTo(S2.Quality));
	}
}

class SongInfo1 
{
	String Name;
	Long Frequency;
	public SongInfo1(String name,Long frequency )
	{
		this.Name=name;
		this.Frequency=frequency;
	}
}

public class ZipfTest 
{
	Long maxFreq=0l;
	public static void main(String args[]) throws IOException
	{
		try
		{	
			String[] sizeAndPickArray;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			do
			{
				sizeAndPickArray = br.readLine().split(" ");
			}while(Integer.parseInt(sizeAndPickArray[0])<Integer.parseInt(sizeAndPickArray[1]));
			int inc=0;
			ArrayList<SongInfo1> songRankSet =new ArrayList<SongInfo1>();		
			ZipfTest zipf=new ZipfTest();
			while(inc!=Integer.parseInt(sizeAndPickArray[0]))
			{
				String nameCountPair = br.readLine();
				songRankSet=zipf.addValuesToList(nameCountPair,songRankSet);
				System.out.println("size"+songRankSet.size());
				inc++;
			} 
			SortedSet<ZipfSongInfo1> songZipfFreqSet=zipf.getZipfFreqSet(songRankSet);
			Iterator<ZipfSongInfo1> getNext = songZipfFreqSet.iterator();
			while(getNext.hasNext())
			{
				ZipfSongInfo1 a=getNext.next();
				System.out.println("quality list :"+a.Quality+a.Name);
			}
		
//			inc=0;
//			while(inc<Integer.parseInt(sizeAndPickArray[1]))
//			{
//				System.out.println(getNext.next().Name);
//				inc++;
//			}
		}
		catch(IOException e)
		{
			System.out.println("exception in the try block"+e);
		}
	}
	public ArrayList<SongInfo1> addValuesToList(String nameCountPair,ArrayList<SongInfo1> songRankSet)
	{
		String[] countPairArray=nameCountPair.split(" ");
		Long songCount=Long.parseLong(countPairArray[0]);
		if(this.maxFreq<songCount)
		{
			this.maxFreq=songCount;
		}
		String songName=countPairArray[1];
		songRankSet.add(new SongInfo1(songName,songCount));
		return songRankSet;	
	}

	public SortedSet<ZipfSongInfo1> getZipfFreqSet(ArrayList<SongInfo1> songRankSet)
	{
		SortedSet<ZipfSongInfo1> songZipfFreqSet=new TreeSet<ZipfSongInfo1>();
		Iterator<SongInfo1> getNext = songRankSet.iterator();
		int inc=1;
		while(getNext.hasNext())
		{
			SongInfo1 nextSong=getNext.next();
			System.out.println("rank list :"+nextSong.Frequency+nextSong.Name);
			songZipfFreqSet.add(new ZipfSongInfo1(nextSong.Name,((float)(nextSong.Frequency)/((float)(maxFreq)/inc))));
			inc++;
		}
		return songZipfFreqSet;
	}
}

