import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class decoder 
{
	
	TreeNode Root = new TreeNode();
    TreeNode currptr = Root;
	
	public void decode(String encoded_bin, String code_table) 
	{
		
		 try {
	            BufferedReader in = new BufferedReader(new FileReader(code_table));
	            
	            String str;
	         
	            while ((str = in.readLine()) != null) {
	                String [] ar = str.split(" ");
	                for (int i = 0;i<ar[1].length();i++)
	                {
	                	if(ar[1].charAt(i)=='0')
	                	{
	                		if (currptr.left == null)
	                		{
	                			currptr.left = new TreeNode();
	                			currptr = currptr.left;
	                		}
	                		else 
	                		{
	                				currptr = currptr.left;
	                		}
	                	}
	                	else
	                	{
	                		if (currptr.right == null)
	                		{
	                			currptr.right = new TreeNode();
	                			currptr = currptr.right;
	                		}
	                		else
	                		{
	                			currptr = currptr.right;
	                		}
	                	}
	                }
	                currptr.value = Integer.parseInt(ar[0]);
	                currptr = Root;
	            }
	            in.close();
	        }
		 catch (IOException e) {
	            System.out.println("File Read Error");
	        }
		 
		 try
		 {
		 InputStream fis = new BufferedInputStream(new FileInputStream(encoded_bin));
		 Writer pw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("decoded.txt")));
		 byte[] allBytes = new byte[4096];
		 int length =0;
		 currptr = Root;
		 while ((length = fis.read(allBytes)) != -1)
		 {
			 for (int k =0;k<length;k++)
			 {	
				 String bitStr=String.format("%8s", Integer.toBinaryString(allBytes[k] & 0xFF)).replace(' ', '0');
				 for(char ch : bitStr.toCharArray()){
				 if (ch== '0')
					{
					 currptr = currptr.left;
					}
				 else if(ch== '1' && currptr.right != null)
				 {
					 currptr = currptr.right;
				 }
				
				 if (currptr.right == null && currptr.left == null)
				 {
					pw.write(currptr.value + "\n");
					 currptr= Root;
				 }}
			 }
		 }
		 pw.close();
		 fis.close();
		 }
		 catch(IOException e)
		 {
		 e.printStackTrace();
		
		 }
		 
	}

	public static void main(String[] args)
	{
		String encoded_bin = args[0];
		String code_table  = args[1];
		decoder d = new decoder();
		d.decode(encoded_bin,code_table);

	}

}
