package Spotify_Challenge;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

class ZipfSongInfo implements Comparable<ZipfSongInfo>
{
	String Name;
	Float Quality;
	public ZipfSongInfo(String name,Float Quality )
	{
		this.Name=name;
		this.Quality=Quality;
	}
//  over-riding the campareTo method to get a sorted list in decreasing order
	public int compareTo(ZipfSongInfo S2){ 
		if (this.Quality.equals(S2.Quality))
		{
			return 1;
		}
		return (-(this.Quality).compareTo(S2.Quality));
	}
}

class SongInfo 
{
	String Name;
	Long Frequency;
	public SongInfo(String name,Long frequency )
	{
		this.Name=name;
		this.Frequency=frequency;
	}
}

public class ZipfSong 
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
			ArrayList<SongInfo> songRankSet =new ArrayList<SongInfo>();		
			ZipfSong zipf=new ZipfSong();
			while(inc!=Integer.parseInt(sizeAndPickArray[0]))
			{
				String nameCountPair = br.readLine();
				songRankSet=zipf.addValuesToList(nameCountPair,songRankSet);
//				System.out.println("size"+songRankSet.size());                   //test output
				inc++;
			} 
			SortedSet<ZipfSongInfo> songZipfFreqSet=zipf.getZipfFreqSet(songRankSet);
			Iterator<ZipfSongInfo> getNext = songZipfFreqSet.iterator();
//			while(getNext.hasNext())
//			{
//				ZipfSongInfo a=getNext.next();
//				System.out.println("quality list :"+a.Quality+a.Name);           //test output
//			}
		
			inc=0;
//			displaying the top good songs based on their quality 
			while(inc<Integer.parseInt(sizeAndPickArray[1]))
			{
				System.out.println(getNext.next().Name);
				inc++;
			}
		}
		catch(IOException e)
		{
			System.out.println("IO exception occured enter the values properly"+e);
			System.exit(0);
		}
		catch(NumberFormatException e)
		{
			System.out.println("enter the values in proper format and do not enter empty values "+e);
			System.exit(0);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("enter the  both the values frequency and the name of the song "+e);
			System.exit(0);
		}
		catch(Exception e)
		{
			System.out.println("Unknown Exception occured "+e);
			System.exit(0);

		}
	}
	public ArrayList<SongInfo> addValuesToList(String nameCountPair,ArrayList<SongInfo> songRankSet) throws NumberFormatException
	{
		try
		{
		String[] countPairArray=nameCountPair.split(" ");
		Long songCount=Long.parseLong(countPairArray[0]);
//      finding out the maximum frequency of a song in the given list (to calculate the Zipf frequency)
		if(this.maxFreq<songCount)
		{
			this.maxFreq=songCount;
		}
		String songName=countPairArray[1];
		songRankSet.add(new SongInfo(songName,songCount));
		return songRankSet;	
		}
		catch(NumberFormatException e)
		{
			System.out.println("enter the values in proper format and do not enter empty values "+e);
			System.exit(0);
			return null;
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("enter the  both the values frequency and the name of the song "+e);
			System.exit(0);
			return null;
		}
		catch(Exception e)
		{
			System.out.println("Unknown Exception occured "+e);
			System.exit(0);
			return null;
		}
	}

	public SortedSet<ZipfSongInfo> getZipfFreqSet(ArrayList<SongInfo> songRankSet)
	{
		SortedSet<ZipfSongInfo> songZipfFreqSet=new TreeSet<ZipfSongInfo>();
		Iterator<SongInfo> getNext = songRankSet.iterator();
		int inc=1;
		while(getNext.hasNext())
		{
			SongInfo nextSong=getNext.next();
//			System.out.println("rank list :"+nextSong.Frequency+nextSong.Name);  //test output
//			calculating the quality of each song and adding into the set to sort them in the decreasing order of max quality
			songZipfFreqSet.add(new ZipfSongInfo(nextSong.Name,((float)(nextSong.Frequency)/((float)(maxFreq)/inc))));
			inc++;
		}
		return songZipfFreqSet;
	}
}

