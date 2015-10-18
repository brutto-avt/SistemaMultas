package util;

public class CpfCnpj implements Validable{
    public static final int CNPJ_DIGITS = 14;
    public static final String CNPJ_MASK = "##.###.###/####-##";
    public static final int CPF_DIGITS = 11;
    public static final String CPF_MASK = "###.###.###-##";
    
    public static boolean isValid(String cpfOrCnpj){
        if (cpfOrCnpj == null) return false;
		String n = cpfOrCnpj.replaceAll("[^0-9]","");
		boolean isCnpj = n.length() == CNPJ_DIGITS;
		boolean isCpf = n.length() == CPF_DIGITS;
		if (!isCpf && !isCnpj) return false;
        int i; int j;  
        int digit;      
        int coeficient; 
        int sum;       
		int[] foundDv = {0,0};
        int dv1 = Integer.parseInt(String.valueOf(n.charAt(n.length()-2)));
        int dv2 = Integer.parseInt(String.valueOf(n.charAt(n.length()-1)));       
        for (j = 0; j < 2; j++) {
            sum = 0;
            coeficient = 2;
            for (i = n.length() - 3 + j; i >= 0 ; i--){
                digit = Integer.parseInt(String.valueOf(n.charAt(i)));               
                sum += digit * coeficient;
                coeficient ++;
                if (coeficient > 9 && isCnpj) coeficient = 2;                
            }                
            foundDv[j] = 11 - sum % 11;
            if (foundDv[j] >= 10) foundDv[j] = 0;
        }
        return dv1 == foundDv[0] && dv2 == foundDv[1];
	}
    
    public static char getModule11Dv(String number, boolean isCpf){
        int sum;       
        int digit;     
        int coeficient;  
        int dv;         
        //
        String n = number.replaceAll("[^0-9]","");
        sum = 0;
        coeficient = 2;
        for (int i = n.length() - 1; i >= 0 ; i--){
            digit = Integer.parseInt(String.valueOf(n.charAt(i)));               
            sum += digit * coeficient;
            coeficient ++;
            if (coeficient > 9 && !isCpf) coeficient = 2;                
        }                
        //
        dv = 11 - sum % 11;
        if (dv >= 10) dv = 0;          
        return Integer.toString(dv).charAt(0);
    }

    public static String completeDv(String number){
        if (number != null) {
            String n = number.replaceAll("[^0-9]","");
            boolean isCpf = n.length() == 9; 
            n = n + getModule11Dv(n, isCpf);
            n = n + getModule11Dv(n, isCpf);
            return n;
        } else {
            return null;
        }
    }
    public static String extractDv(String completeNumber){
        if (completeNumber != null){
            String n = completeNumber.replaceAll("[^0-9]","");
            boolean isCpf = n.length() == 9;
            return "" + getModule11Dv(completeNumber, isCpf);
        } else {
            return null;
        }
        
    }
    
    private String mask = null;
    private String  number = null;
    private boolean autoCorrection = false;

	public CpfCnpj(){
		super();
	}

	public CpfCnpj(String cpfCnpj){
		super();
		setCpfCnpj(cpfCnpj);
	}

    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString()); 
    }

    public String getMask() {
        return mask;
    }

    public String getNumber() {
        return number;
    }

    public String getCpfCnpj() {
        if (number != null){
        	if(this.isCpf())
        		return number.replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})","$1\\.$2\\.$3-$4");
        	else
        		return number.replaceAll("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})","$1\\.$2\\.$3/$4-$5");
        } else return null;
    }

    public boolean isCnpj() {
        return number != null && number.length() == CNPJ_DIGITS;    
    }
 
    public boolean isCpf() {
		return number != null && number.length() == CPF_DIGITS;    
    }

    public boolean isFormatValid() {
		return (isCpf() || isCnpj());
    }

    public boolean isValid() {
        return isValid(getNumber());
    }
	
    public void setCpfCnpj(String cpfCnpj) {
        if (cpfCnpj != null){
            number = cpfCnpj.replaceAll("[^0-9]*", "");
            if (isCpf()) {
                mask = CPF_MASK;
            } else if (isCnpj()){
                mask = CNPJ_MASK;
            }
        } else number = null;
    }

    public long toLong() {
		if (number != null && number.length() > 0)
			return Long.parseLong(number);
		else
			return 0;
    }

    public String toString() {
        return getCpfCnpj();
    }

    public void validate() throws ValidationException {
        if (!isValid()) throw new ValidationException();        
    }

    public boolean isAutoCorrection() {
        return autoCorrection;
    }

    public void setAutoCorrection(boolean autoCorrection) {
        this.autoCorrection = autoCorrection;
    }
}
