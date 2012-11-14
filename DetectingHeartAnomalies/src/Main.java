
public class Main 
{
	public static void main(String args[])
	{
		MachineLearning machineLearning = new MachineLearning("data/spect-resplit.train.csv", "data/spect-resplit.test.csv");
		machineLearning.learn();
	}

}
