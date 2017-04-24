    import java.io.BufferedReader;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.HashMap;
	import java.util.Set;

	public class encoder {

		public Node build_tree_using_fourway_heap(String filepath)
		{   
			FourwayHeap fh = new FourwayHeap();
			HashMap<Integer,Integer> k = fh.frequencyTable(filepath);
			Set<Integer> keySet = k.keySet();
			
			for (int i : keySet)
			{
			 Node x = new Node(i,k.get(i));
			 x.isLeaf=true;
			 fh.insert(x);
			}
			
		while (fh.size()>1)
		{
			Node A = fh.deleteMin();
			Node B = fh.deleteMin();
			Node C = new Node(A,B);
			C.left = A;
			C.right = B;
			C.isLeaf=false;
			fh.insert(C);
		}
		return fh.deleteMin();
		
	}
		
		public static void main(String[] args) throws FileNotFoundException, IOException 
		{   
			String filepath="";
		
			if(args.length>0)
			{
				filepath = args[0];
				
			}
			else
			{
				System.exit(0);
			}
			
			encoder a = new encoder();
			Node x =a.build_tree_using_fourway_heap(filepath);
			HuffmanTree h = new HuffmanTree(x);
			h.printPreorder(h.root,new String());
			
			HashMap<Integer, String> k = h.hashy;
			
			PrintWriter out = null;
			try
			{
				out=new PrintWriter("code_table.txt");
				for ( int i : k.keySet())
				{
					out.println(i+" "+ String.valueOf(k.get(i)));
				}
			}
			catch(Exception e)
			{}
			finally
			{
				out.close();
			}
			try {
				
	            String outfilepath = "encoded.bin";
	            BufferedReader encode = new BufferedReader(new FileReader(filepath));
	            FileOutputStream encodingwrite = new FileOutputStream(outfilepath);
	            String draw;
	            int j;
	            String s=new String();
	            while ((draw = encode.readLine()) != null && !draw.isEmpty())   {
	                      draw = draw.trim();
	                      j=Integer.valueOf(draw);
	                      s=s+k.get(j);
	                      if(s.length() % 8 == 0){
	                      for (int x1 = 0; x1 < s.length(); x1 += 8) {
	                          String bS = s.substring(x1, x1 + 8); 
	                          int pByte = Integer.parseInt(bS, 2);
	                          encodingwrite.write(pByte); 
	                        }
	                      s = "";
	                      }
	                      
	            }
	            
	            while (s.length() % 8 != 0)
	                s += "0"; 
	            for (int x1 = 0; x1 < s.length(); x1 += 8) {
	              String bS = s.substring(x1, x1 + 8); 
	              int pbyte = Integer.parseInt(bS, 2);
	              encodingwrite.write(pbyte); 
	            }
	            
	            encode.close();
	            encodingwrite.close();
	
			}
			catch(Exception E)
			{
				System.out.println("Error");
			}

		}
	}
		
