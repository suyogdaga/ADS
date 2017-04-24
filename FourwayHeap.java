import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class FourwayHeap {
	private Node[] heap;
	private int size;
	private int d;

	
	public FourwayHeap() {
		this.d = 4;
		this.heap = new Node[1000000];
		this.size = 0;
	}
	
	
	public boolean Empty() {
		return size == 0;
	}

	public int size()
    {
        return size;
    }
	public int findMin() {
		if (size == 0) { 
			System.out.println("Heap is empty");
			}
		
		return heap[0].frequency;
	}
	
	 public HashMap<Integer,Integer> frequencyTable(String filepath)
	    {
	    	HashMap<Integer, Integer> hmap = new HashMap<Integer,Integer>();
			 int temp;

			Scanner scanner;
			try {
				scanner = new Scanner(new File(filepath));
				while(scanner.hasNextInt())
				{
					temp = scanner.nextInt();
					 if(hmap.containsKey(temp))
					 {
						 hmap.put(temp, hmap.get(temp)+1);
					 }
					 else
					 { 
					 	hmap.put(temp, 1);
					 }
					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return hmap;
	    }
	
	public void insert(Node a) {
		if (size==0) {
			heap[0] = a;
			size++;
			return;
		}
		
		
		if (size == heap.length) {
			Node[] newArray = new Node[size*2];
			for(int i=0; i<size; i++) {
				newArray[i] = heap[i];
			}
			
			heap = newArray;
		}
		
		
		int i = size;
		
		for(; heap[(i-1)/d].frequency > a.frequency; i=(i-1)/d) {
			
			if (i==0) break;
			
			
			heap[i] = heap[(i-1)/d];
		}
		
		heap[i] = a;
		size++;
		
	}
	
	public Node deleteMin() {
		if (size == 0) { throw new java.lang.IllegalStateException("Empty Heap"); }
		
		Node ret = heap[0];
		
		Node last_ele = heap[size-1];
		
		int child_min;
		
		int i=0;
		
		for(; (i*d)+1 < size; i=child_min) {
			
			child_min = (i*d)+1;
			
			
			if (child_min > size) { break; }
			
			int j=1, currentSmallest = child_min;
			for(; j<d; j++) {
				if (child_min+j == size) break;
				if(heap[currentSmallest].frequency > heap[child_min+j].frequency)
					currentSmallest = child_min+j;
			}
			
			child_min = currentSmallest;
			
			if (last_ele.frequency > heap[child_min].frequency) {
				heap[i] = heap[child_min];
			} else {
				break;
			}
		}
		
		heap[i] = last_ele;
		size--;
		return ret;
	}
	
	

}