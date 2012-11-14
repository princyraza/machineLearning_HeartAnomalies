import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class MachineLearning 
{
	private int F[][];
	private int N[];
	private String train;
	private String test;
	
	public MachineLearning(String trainFileName, String testFileName)
	{
		train=trainFileName;
		test=testFileName;
	}
	
	public void learn()
	{
		F = new int[2][22];
		N = new int[2];
		String readLine="";
		ArrayList<Integer> arrayFields = new ArrayList<Integer>();
		
		for(int i=0;i<F.length;i++)
		{
			for(int j=0;j<F[0].length;j++)
			{
				F[i][j]=0;
			}
		}
		
		for(int i=0;i<N.length;i++)
		{
			N[i]=0;
		}
		
		try {
			FileReader fr = new FileReader(train);
			BufferedReader in = new BufferedReader(fr);
			int c; //class
			while((readLine=in.readLine()) != null)
			{
				arrayFields = StringToArrayList(readLine);
				//increment N[t.c]
				c=arrayFields.get(0);
				N[c]++;
				for(int j=1;j<arrayFields.size()-1;j++)
				{
					if(arrayFields.get(j)==1)
						F[c][j]++;
				}
				
			}
			
			//test purpose
//			for(int i=0;i<F.length;i++)
//			{
//				for(int j =0;j<F[0].length;j++)
//				{
//					System.out.print(F[i][j]+",");
//				}
//				
//			}
//			System.out.println(F[0].length);
			
//			for(int i=0;i<N.length;i++)
//			{
//				System.out.println(N[i]);
//			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void classifier()
	{
		FileReader fr;
		int abnormalCount = 0;
		int normalCount = 0;
		int totalInstance = 0;
		String readLine="";
		ArrayList<Integer> arrayFields = new ArrayList<Integer>();
		try {
			fr = new FileReader(test);
			BufferedReader in = new BufferedReader(fr);
			int c; //class
//			int instance[];
			while((readLine=in.readLine()) != null)
			{
				arrayFields = StringToArrayList(readLine);
				c=arrayFields.get(0);
//				for(int i = 0;i<arrayFields.size()-1;i++)
//				{
//					instance[i]=arrayFields.get(i);
//				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private double likeliHood(int[] c, int i)
	{
		double L = 0.0;
		L=Math.log(N[i]+0.5)-Math.log(N[0]+N[1]+0.5);
		int s = 0;
		for (int j=1;j<c.length;j++)
		{
			s = F[i][j];
			if(c[j]==0)
				s=N[i]-s;
			L=L+Math.log(s+0.5)-Math.log(N[i]+0.5);
		}
		return L;
	}
	
	private int classify(int[] c)
	{
		if (likeliHood(c, 1)>likeliHood(c, 0))
			return 1;
		return 0;
	}
	
	/**
	 * Fonction interne de decomposition d'une ligne de texte en ArrayList A
	 * chaque espace, un nouvel element est ajoute a l'ArrayList
	 *
	 * @param s
	 *            chaine de caracteres
	 * @return La liste des mots de la chaine de caracteres dans une ArrayList
	 * @author Stephane Bonnevay - Professor at Polytech Lyon, Universite Lyon 1, Lyon, France
	 */
	private static ArrayList<Integer> StringToArrayList(String s) {
		ArrayList<Integer> v = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(s,",");
		while (st.hasMoreTokens())
			v.add(Integer.valueOf(st.nextToken()));
		return v;
	}

}
