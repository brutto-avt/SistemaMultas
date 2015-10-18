package util;

public interface Validable {
    
    boolean isValid();
    
    void validate() throws ValidationException;

}
