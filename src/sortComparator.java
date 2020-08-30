import java.util.Comparator;

public class sortComparator implements Comparator{

	@Override
	 public int compare(Object a, Object b) {
	    if (a instanceof String && ((String) a).length() == 0) {
	      a = null;
	    }
	    if (b instanceof String && ((String) b).length() == 0) {
	      b = null;
	    }
	    if (a == null && b == null) {
	      return 0;
	    } else if (a == null) {
	      return 1;
	    } else if (b == null) {
	      return -1;
	    } else if (a instanceof Comparable) {
	      return ((Comparable) a).compareTo(b);
	    } else {
	      return a.toString().compareTo(b.toString());
	    }
	  }

	

	
	

}
