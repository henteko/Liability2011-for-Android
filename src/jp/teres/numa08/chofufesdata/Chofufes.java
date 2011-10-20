package jp.teres.numa08.chofufesdata;

import java.io.Serializable;



abstract public class Chofufes implements Serializable{
	abstract public Key getKey();
	
	abstract public void setKey(Key key);
}
