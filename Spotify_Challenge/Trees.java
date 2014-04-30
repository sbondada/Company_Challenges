package Spotify_Challenge;
import java.util.ArrayList;
import java.util.Currency;

public class Trees 
{

	Node prev ;
	Node head;
	Node parent;
	Node maxleftNode;
	int max;
	int min;
	{
		min=Integer.MAX_VALUE;
		max=Integer.MIN_VALUE;
		prev =null;
		head=null;
		parent = null;
		maxleftNode = null;
	}
	public class BinaryTree
	{
		public Node root;
		public BinaryTree(int val) 
		{
			this.root=new Node(val);
		}
		public void addNode(int val)
		{
			Node temp=this.root;
			Node tempparent=this.root;
			while(temp!=null)
			{
				tempparent=temp;
				if(val<=temp.val)
				{
					temp=temp.left;
				}
				else
				{
					temp=temp.right;
				}
			}
			if(val<=tempparent.val)
			{
				tempparent.left=new Node(val);
			}
			else
			{
				tempparent.right=new Node(val);
			}
		}
	}
	public class Node
	{
		public int val;
		public Node left;
		public Node right;
		public Node(int val)
		{
			this.val=val;
			this.left=null;
			this.right=null;
		}
	}
	public Node findnextrightnode(Trees.BinaryTree tree,int val)
	{
		ArrayList<Node> queue = new ArrayList<Node>();
		Node temp=tree.root;
		queue.add(temp);
		int levelcounter=queue.size();
		while(!queue.isEmpty())
		{
			Node pop=queue.get(0);
			queue.remove(0);
			levelcounter--;
			if(pop.left!=null)
			{
				queue.add(pop.left);
			}
			if(pop.right!=null)
			{
			queue.add(pop.right);
			}
			if(val==pop.val)
			{
				if(levelcounter!=0)
				{
					return queue.get(0);
				}
			}
			if(levelcounter==0)
			{
				levelcounter=queue.size();
			}
		}
		return null;
	}
	public Node LowestPossibleAncestors(Node curr, int first,int second)
	{
		if(curr!=null)
		{
			if (curr.val==first || curr.val==second)
			{
				return curr;
			}
			Node left=LowestPossibleAncestors(curr.left,first,second);
			Node right=LowestPossibleAncestors(curr.right, first, second);
			if(left!=null && right!=null && this.parent==null)
			{
				this.parent=curr;
			}
			else if(left!=null)
			{
				return left;
			}
			else if(right!=null)
			{
				return right;
			}
			return null;
		}
		else
		{
			return null;
		}
	}
	public void ConverttoDoublyList(Node curr)
	{
		if(curr!=null)
		{
			ConverttoDoublyList(curr.left);
			curr.left=this.prev;
			if(this.prev!=null)
			{
				this.prev.right=curr;
			}
			else
			{
				this.head=curr;
			}
			this.prev=curr;
			ConverttoDoublyList(curr.right);
		}
		else
		{
			return;
		}
	}
	public void PrintNodewithNoSiblings(Node curr)
	{
		if(curr.left==null && curr.right==null)
		{
			System.out.print(curr.val+" ");
			return;
		}
		if(curr.left!=null)
		{
			PrintNodewithNoSiblings(curr.left);
		}
		if(curr.right!=null)
		{
			PrintNodewithNoSiblings(curr.right);
		}
	}
	public void SumOfbranches(Node curr,int sum)
	{
		if(curr!=null)
		{
			sum+=curr.val;
			if(curr.left==null && curr.right==null)
			{
				System.out.println(sum);
			}
			SumOfbranches(curr.left, sum);
			SumOfbranches(curr.right, sum);
		}
	}
	public int findMaxLeftDeepNode(Node curr,int depth,int max,int flag)
	{
		if(curr!=null)
		{
			depth++;
			int max1=findMaxLeftDeepNode(curr.left,depth,max,1);
			if(max1>max)
			{
				max=max1;
				System.out.println(curr.val);
				this.maxleftNode=curr;
			}
			int max2=findMaxLeftDeepNode(curr.right,depth,max,0);
			return max2;
		}
		else
		{
			if(flag==1)
			{
			return depth;
			}
			else
			{
				return max;
			}
		}
	}
	public void getMaxandMinDepth(Node curr,int depth)
	{
		if(curr!=null)
		{
			depth++;
			getMaxandMinDepth(curr.left, depth);
			getMaxandMinDepth(curr.right, depth);
		}
		else
		{
			if(depth>this.max)
			{
				this.max=depth;
			}
			if(depth<this.min)
			{
				this.min=depth;
			}
		}
	}
	public void printList(Node head)
	{
		Node temp=head;
		while(temp!=null)
		{
			System.out.print(temp.val+" ");
			temp=temp.right;
		}
		System.out.println();
	}
	public static void main(String args[])
	{
		Trees tr= new Trees();
		Trees.BinaryTree tree= tr.new BinaryTree(19);
		tree.addNode(6);
		tree.addNode(8);
		tree.addNode(29);
		tree.addNode(11);
		tree.addNode(18);
		tree.addNode(10);
		tree.addNode(3);
		tree.addNode(5);
		tree.addNode(1);
//		System.out.println(tr.findnextrightnode(tree, 29).val);
//		tr.ConverttoDoublyList(tree.root);
//		tr.prev.right=null;
//		tr.printList(tr.head);
//		tr.LowestPossibleAncestors(tree.root,5,29);
//		System.out.println(tr.parent.val);
//		tr.PrintNodewithNoSiblings(tree.root);
//		tr.SumOfbranches(tree.root,0);
//		tr.findMaxLeftDeepNode(tree.root,0,0,0);
//		System.out.println(tr.maxleftNode.val);
		tr.getMaxandMinDepth(tree.root,0);
		System.out.println(tr.max);
		System.out.println(tr.min);

	}
}
