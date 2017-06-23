//import RandomizedQueue;
import edu.princeton.cs.algs4.StdIn.*;

public class Permutation {

	public static void main(String[] args)
	{
		RandomizedQueue<String> myQueue = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[0]);
		while(!edu.princeton.cs.algs4.StdIn.isEmpty())
		{
			myQueue.enqueue(edu.princeton.cs.algs4.StdIn.readString());
		}
		for (int j=0;j<k;j++){
			System.out.println(myQueue.dequeue());
		}
	}

}