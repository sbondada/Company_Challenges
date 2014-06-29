package storm8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main 
{
	// Given three colinear points p, q, r, the function checks if
	// point q lies on line segment 'pr'
	public boolean onSegment(Point p, Point q, Point r)
	{
	    if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
	        q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
	       return true;
	 
	    return false;
	}
	 
	// To find orientation of ordered triplet (p, q, r).
	// The function returns following values
	// 0 --> p, q and r are colinear
	// 1 --> Clockwise
	// 2 --> Counterclockwise
	int orientation(Point p, Point q, Point r)
	{
	    // See 10th slides from following link for derivation of the formula
	    // http://www.dcs.gla.ac.uk/~pat/52233/slides/Geometry1x1.pdf
	    Float val = (((q.y - p.y) * (r.x - q.x)) -((q.x - p.x) * (r.y - q.y)));
	 
	    if (val == 0) return 0;  // colinear
	 
	    return (val > 0)? 1: 2; // clock or counterclock wise
	}
	 
	// The main function that returns true if line segment 'p1q1'
	// and 'p2q2' intersect.
	public boolean doIntersect(Bridges b1,Bridges b2)
	{
        Point p1, q1, p2, q2;
        p1=b1.start;
        q1=b1.end;
        p2=b2.start;
        q2=b2.end;

	    // Find the four orientations needed for general and
	    // special cases
	    int o1 = orientation(p1, q1, p2);
	    int o2 = orientation(p1, q1, q2);
	    int o3 = orientation(p2, q2, p1);
	    int o4 = orientation(p2, q2, q1);
	 
	    // General case
	    if (o1 != o2 && o3 != o4)
	        return true;
	 
	    // Special Cases
	    // p1, q1 and p2 are colinear and p2 lies on segment p1q1
	    if (o1 == 0 && onSegment(p1, p2, q1)) return true;
	 
	    // p1, q1 and p2 are colinear and q2 lies on segment p1q1
	    if (o2 == 0 && onSegment(p1, q2, q1)) return true;
	 
	    // p2, q2 and p1 are colinear and p1 lies on segment p2q2
	    if (o3 == 0 && onSegment(p2, p1, q2)) return true;
	 
	     // p2, q2 and q1 are colinear and q1 lies on segment p2q2
	    if (o4 == 0 && onSegment(p2, q1, q2)) return true;
	 
	    return false; // Doesn't fall in any of the above cases
	}

	public class Bridges
	{
		Point start;
		Point end;
		int bridgenum;
		public Bridges(Point start,Point end,int bridgenum)
		{
			this.start=start;
			this.end=end;
			this.bridgenum=bridgenum;
		}
	}
	public class Point
	{
		float x;
		float y;
		public Point(float x,float y)
		{
			this.x=x;
			this.y=y;
		}
	}
	
	class ValueComparator implements Comparator<Bridges> {

	    Map<Bridges,Integer> base;
	    public ValueComparator(Map<Bridges, Integer> base) {
	        this.base = base;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(Bridges a, Bridges b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys
	    }
	}
	public LinkedHashMap<Bridges,ArrayList<Bridges>> findPossibleBridges(ArrayList<Bridges> bridgeList)
	{
		LinkedHashMap<Bridges,ArrayList<Bridges>> intersectionMap=new LinkedHashMap<>();
        HashMap<Bridges, Integer> intersectionCount= new  HashMap<Bridges,Integer>();
        for(int i =0;i<bridgeList.size();i++)
        {
        	Bridges firstbridge=bridgeList.get(i);
            intersectionMap.put(firstbridge,new ArrayList<Bridges>());
        	for(int j=0;j<bridgeList.size();j++)
        	{
                Bridges secondbridge = bridgeList.get(j);
        		if(i!=j && this.doIntersect(firstbridge,secondbridge))
        		{
                    intersectionMap.get(firstbridge).add(secondbridge);
        		}
            }
        	ArrayList<Bridges> templist= intersectionMap.get(firstbridge);
            intersectionCount.put(firstbridge,templist.size());
        }
        TreeMap<Bridges,Integer> sortedMap=new TreeMap<>(this.new ValueComparator(intersectionCount));
        sortedMap.putAll(intersectionCount);
        
        while(sortedMap.firstEntry().getValue()>0)
        {
        	Bridges first=sortedMap.firstKey();
        	ArrayList<Bridges> intersectlist=intersectionMap.get(first);
            for(Bridges temp:intersectlist)
            {
                ArrayList<Bridges> templist=intersectionMap.get(temp);
                templist.remove(first);
                intersectionCount.put(temp,templist.size());
            }
            intersectionMap.remove(first);
            intersectionCount.remove(first);
            sortedMap.clear();
            sortedMap.putAll(intersectionCount);
        }
        return intersectionMap;
	}
	public static void main(String args[])
	{
		String filepath=args[0];
        BufferedReader reader=null;
		try 
		{
			reader = new BufferedReader(new FileReader(filepath));
            String line = null;
            Main mnib= new Main();
            ArrayList<Bridges> bridgeList=new ArrayList<>();
            int count=0;
            while ((line = reader.readLine()) != null) 
            {
            	String temp[]=line.split(":");
            	String token[]=temp[1].split(",");
            	Point start=mnib.new Point(Float.valueOf(token[0].substring(3)),Float.valueOf(token[1].substring(0,token[1].length()-1)));
            	Point end=mnib.new Point(Float.valueOf(token[2].substring(2)),Float.valueOf(token[3].substring(0,token[3].length()-2)));
            	bridgeList.add(mnib.new Bridges(start,end,++count));
            }
            LinkedHashMap<Bridges,ArrayList<Bridges>>  intersectionMap=mnib.findPossibleBridges(bridgeList);
            for(Bridges  result:intersectionMap.keySet())
            {
            	System.out.println(result.bridgenum);
            }
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("file not found");
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			System.out.println("Reader not found");
			e.printStackTrace();
		}
	}
}
