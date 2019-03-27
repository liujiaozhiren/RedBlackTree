package rbtree;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.Test;

class RBTreeTest {

	@Test
	void testInsert() {
		Comparator<Integer> com = new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if(o1.equals(o2))
				return 0;
				else return o1<o2?-1:1;
			}
			
		};
		Random rand = new Random(47);
		RBTree<Integer> rbt = new RBTree<Integer>(com);
		for(int i=0;i<2000;i++) {
			int v = rand.nextInt(99999);
			rbt.insert(v);
			System.out.println();
		}
		rbt.bianli(null);
		
		rbt.deep(rbt.root);
		rbt.deepp(rbt.root);
	}

}
