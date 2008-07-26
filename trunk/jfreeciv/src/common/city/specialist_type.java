package common.city;
public enum specialist_type {
  SP_ELVIS, SP_SCIENTIST, SP_TAXMAN, SP_COUNT;
  public static int getSize(){
	  return values().length;
  }
};