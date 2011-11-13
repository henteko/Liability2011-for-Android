package jp.teres.numa08.chofufesdata;

import java.io.Serializable;



abstract public class Chofufes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2672252691101551188L;

	abstract public Key getKey();
	
	abstract public void setKey(Key key);
}
