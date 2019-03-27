package rbtree;

public class Tree<T> {
	private Tree<T> left;
	private Tree<T> right;
	private T value;
	private boolean red = true;
	public Tree(T value,boolean red) {
		this.value = value;
		this.red = red;
	}
	public Tree(T value) {
		this.value = value;
	}
	public Tree<T> getChild(boolean t){
		return t?left:right;
	}
	public Tree<T> getChild(int i){
		return i==0?left:right;
	}
	
	
	public Tree<T> getLeft() {
		return left;
	}
	public void setLeft(Tree<T> left) {
		this.left = left;
	}
	public Tree<T> getRight() {
		return right;
	}
	public void setRight(Tree<T> right) {
		this.right = right;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public boolean isRed() {
		return red;
	}
	public void setRed(boolean red) {
		this.red = red;
	}
	public void setChild(boolean b,Tree<T> v){
		if(b)setLeft(v);
		else setRight(v);
	}
	public void setChild(int b,Tree<T> v){
		if(b==0)setLeft(v);
		else setRight(v);
	}

}
