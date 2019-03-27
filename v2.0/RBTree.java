package rbtree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.GrayFilter;

public class RBTree<T> {
	static int red=0;
	static int black=0;
	Comparator<T> com;
	Tree<T> root;
	public RBTree(Comparator<T> com) {
		this.com = com;
	}
	
	public void insert(T value) {
		if(root == null) {root = new Tree<T>(value,false);return;}
		Tree<T> p = root;
		LinkedList<Tree<T>> nodeStack = new LinkedList<Tree<T>>(); 
		LinkedList<Boolean> boolStack = new LinkedList<Boolean>(); 
		while(p!=null) {
			if(com.compare(value,p.getValue())==0){									
				return;
			}
			else if(com.compare(value,p.getValue())<0) {												
				nodeStack.addFirst(p);
				boolStack.addFirst(true);
				p=p.getLeft();
			}else {																						
				nodeStack.addFirst(p);
				boolStack.addFirst(false);
				p=p.getRight();
			}
		}
		Tree<T> gFather,father = nodeStack.poll();														
		boolean grandLeft,faLeft = boolStack.poll();
		Tree<T> child = new Tree<T>(value);
		father.setChild(faLeft,child);
		p=father.getChild(faLeft);																	
		
		while(true){
			if(!father.isRed()) return;													
			gFather = nodeStack.poll();											//	System.out.println(gFather.getValue().toString()+" "+father.getValue().toString());
			grandLeft = boolStack.poll();										//	System.out.println("grandleft= "+grandLeft+" fatherleft="+faLeft);
			if(gFather.getChild(!grandLeft)!=null&&gFather.getChild(!grandLeft).isRed()) {			
				father.setRed(false);
				gFather.getChild(!grandLeft).setRed(false);
				if(gFather==root)return;
				gFather.setRed(true);
				p = gFather;
				father = nodeStack.poll();
				faLeft = boolStack.poll();
			}else break;
		}
		if(faLeft!=grandLeft) {																
			T tempValue = p.getValue();
			p.setValue(father.getValue());
			father.setValue(tempValue);

			Tree<T> tempNode = father.getChild(!faLeft);
			father.setChild(!faLeft, p);
			father.setChild(faLeft,p.getChild(faLeft));
			p.setChild(faLeft, p.getChild(!faLeft));
			p.setChild(!faLeft, tempNode);
			faLeft = !faLeft;
		}
														
		T tempValue = gFather.getValue();			//T = 16
		gFather.setValue(father.getValue());
		father.setValue(tempValue);
		
		gFather.setChild(grandLeft, p);
		father.setChild(faLeft, father.getChild(!faLeft));
		father.setChild(!faLeft, gFather.getChild(!grandLeft));
		gFather.setChild(!grandLeft, father);
		
		
	  /*while(gFather.getChild(grandLeft)!=null&&gFather.getChild(grandLeft).isRed()) {
			
			gFather = nodeStack.poll();
			grandLeft = boolStack.poll();
		}*/
		
	}

	public static void main(String[] args) {
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
	public void bianli(Tree t) {if(t==null)t=root;
		System.out.print(t.getLeft()!=null?"↓ ":"x ");
		System.out.print(t.getValue().toString()+" "+(t.isRed()?"red":"black"));
		System.out.println(t.getRight()!=null?" ↓ ":" x ");
		if(t.isRed()&&((t.getChild(true)!=null&&t.getChild(true).isRed())||(t.getChild(false)!=null&&t.getChild(false).isRed()))) throw new RuntimeException();
		if(t.getLeft()!=null)bianli(t.getChild(true));
		if(t.getRight()!=null)bianli(t.getChild(false));
	}
	
	public void deep(Tree t) {
		if(!t.isRed())red++;
		if(t.getLeft()==null||t.getRight()==null) {System.out.println("red="+red);return;}
		
		if(t.getLeft()!=null)deep(t.getLeft());
		else if(t.getRight()!=null)deep(t.getRight());
		
		
		
	}
	public void deepp(Tree t) {
		if(!t.isRed())black++;
		if(t.getRight()==null||t.getLeft()==null) {
			if(black!=red)System.out.println("not equals"+red+" "+black);
		}
		if(t.getLeft()!=null)deepp(t.getLeft());
		if(t.getRight()!=null)deepp(t.getRight());
		if(!t.isRed())black--;
	}
	
	
}
