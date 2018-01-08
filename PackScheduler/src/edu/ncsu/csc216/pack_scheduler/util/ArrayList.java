package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;
/**
 * Constructor for the ArrayList utility
 * 
 * @author James Ritchey 
 * @author Alex Johnson
 * @author Connor McCarthy 
 *
 * @param <E> The generic item to use
 */
public class ArrayList<E> extends AbstractList<E> {
	/**The initial size of the array list*/
	private static final int INIT_SIZE = 10;
	/**The array of generic objects*/
	private E[] list;
	/**The number of items in the ArrayList*/
	private int size;
	/**
	 * Constructor for the ArrayList
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(){
		list = (E[])(new Object[INIT_SIZE]);
		size = 0;
	}
	/**
	 * Adds the specified item to the ArrayList
	 * and shifts all other obejcts to the right
	 * @param idx The index to add the object
	 * @param item The item to add
	 */
	@Override
	public void add(int idx, E item){
		
		if(idx < 0 || idx > size){
			throw new IndexOutOfBoundsException();
		}
		if(item == null){
			throw new NullPointerException();
		}
		for(int i = 0; i < list.length; i++){
			if(item.equals(list[i])){
				throw new IllegalArgumentException();
			}
		}
		if(size >= list.length - 1) {
			this.growArray();
		}
		for(int i = size; i >= idx; i--){
			list[i + 1] = list[i];
		}
		list[idx] = item;
		size++;
	}
	/**
	 * Removes the item from the ArrayList at the 
	 * specified index
	 * @param idx The index to remove at
	 */
	@Override
	public E remove(int idx){
		if(idx < 0 || idx >= size){
			throw new IndexOutOfBoundsException();
		}
		E item = list[idx];
		for(int i = idx; i < list.length - 1; i++){
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return item;
	}
	/**
	 * Overwrites the index with the specified item
	 * @return item The item that was set
	 */
	@Override
	public E set(int idx, E item){
		E prevItem = list[idx];
		if(idx < 0 || idx >= size){
			throw new IndexOutOfBoundsException();
		}
		if(item == null){
			throw new NullPointerException();
		}
		for(int i = 0; i < list.length; i++){
			if(item.equals(list[i])){
				throw new IllegalArgumentException();
			}
		}
		list[idx] = item;
		return prevItem;
	}
	/**
	 * Gets the item from the specified index
	 * @return The item at the index
	 */
	@Override
	public E get(int idx) {
		if(idx < 0 || idx >= size){
			throw new IndexOutOfBoundsException();
		}
		return list[idx];
	}
	/**
	 * Returns the size of the ArrayList
	 * @return size The size of the ArrayList
	 */
	public int size() {
		return size;
	}
	/**
	 * Doubles the capacity of the ArrayList
	 */
	@SuppressWarnings("unchecked")
	private void growArray(){
		E[]	newList = (E[])(new Object[list.length * 2]);
		for(int i = 0; i < list.length; i++){
			newList[i] = list[i];
		}
		list = newList;
	}
}
