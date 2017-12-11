package Search;

public interface Online {
	static boolean checkName(String product, String search) {
		String a[] = product.split(" ");
		String b[] = search.split(" ");
		if(a.length*b.length<=0)
			return false;
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (a[j].length() > 0) {
					if (b[i].indexOf(a[j]) > -1)
						return true;
				}
			}
		}
		return false;
	}
}
