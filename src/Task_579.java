import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Task_579 {
	public static void main(String[] args) {
		Sequence test = new Sequence();
		try(Formatter output = new Formatter("OUTPUT.TXT")) {
			output.format("%s", test.toString());
		}catch (FileNotFoundException | FormatterClosedException e) {
			e.printStackTrace();
		}
	}

	//-----------------------------------------------------------------------------
	/*public*/static class Sequence {
		//-----------------------------------------------------------------------------fields
		private HashMap<Integer, Integer> negativeSequence;
		private HashMap<Integer, Integer> positiveSequence;
		private int lengthSequence;
		//-----------------------------------------------------------------------------constructors
		/*public*/ Sequence(String path)
		{
			this.negativeSequence = new HashMap<>();
			this.positiveSequence = new HashMap<>();
			try(Scanner input = new Scanner(Paths.get(path))) {
				//-----------------------------------------------------------------------------
				if(input.hasNext()) {
					//-----------------------------------------------------------------------------
					this.lengthSequence = input.nextInt();
					for(int i = 0; i < this.lengthSequence; i++) {
						int temp = input.nextInt();
						if(temp >= 0) {
							this.positiveSequence.put(i + 1, temp);
						}
						else {
							this.negativeSequence.put(i + 1, temp);
						}
					}
					//-----------------------------------------------------------------------------
				}
				//-----------------------------------------------------------------------------
				else {
					throw new IOException("File is empty!");
				}
				//-----------------------------------------------------------------------------
			}catch (IOException | NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		/*public*/ Sequence()
		{
			this("INPUT.TXT");
		}
		//-----------------------------------------------------------------------------
		//-----------------------------------------------------------------------------methods
		private int sumSequence(HashMap<Integer, Integer> current)
		{
			int sum = 0;
			for(Integer i : current.values()) {
				sum+= Math.abs(i);
			}
			return sum;
		}

		private String answer()
		{
			return (sumSequence(this.negativeSequence) >= sumSequence(this.positiveSequence)) ?
					createNegativeSequenceString() : createPositiveSequenceString();
		}

		private String createNegativeSequenceString()
		{
			String newString = String.format(("%d%n"), this.negativeSequence.size());
			for(Integer i : this.negativeSequence.keySet()) {
				newString = String.format(("%s%d "), newString, i);
			}
			return newString;
		}

		private String createPositiveSequenceString()
		{
			String newString = String.format(("%d%n"), this.positiveSequence.size());
			for(Integer i : this.positiveSequence.keySet()) {
				newString = String.format(("%s%d "), newString, i);
			}
			return newString;
		}

		@Override
		public String toString()
		{
			return String.format(("%s"), this.answer());
		}
	}
}