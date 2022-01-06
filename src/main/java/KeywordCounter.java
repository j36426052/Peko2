import java.util.ArrayList;

public class KeywordCounter {
	private String[] dataListG, dataListK;
	private int count, lengthG, lengthK;

	public KeywordCounter(String dataG, String dataK) {
		this.dataListG = dataG.split(" ");
		this.dataListK = dataK.split(" ");
		count = 0;

		lengthG = dataListG.length;
		lengthK = dataListK.length;
		this.count(0, 0);
	}

	public void count(int indexG, int indexK) {
		if (lengthG - indexG > lengthK - indexK) {
			if (!dataListG[indexG].equals(dataListK[indexK])) {
				count(indexG + 1, indexK);
			} else if (indexK == lengthK - 1) {
				count++;
				count(indexG + 1, 0);
			} else {
				count(indexG + 1, indexK + 1);
			}

		}
	}

	public int getCount() {
		return count;
	}

}
