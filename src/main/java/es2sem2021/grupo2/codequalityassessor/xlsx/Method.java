package es2sem2021.grupo2.codequalityassessor.xlsx;

public class Method {

	public int methodID;
	public String m_package;
	public String m_class;
	public String m_method;
	public int nom_class;
	public int loc_class;
	public int wmc_class;
	public boolean is_God_Class;
	public int loc_method;
	public int cyclo_method;
	public boolean is_Long_Method;

	public Method(int methodID, String package1, String class1, String method, int nOM_class, int lOC_class,
			int wMC_class, boolean is_God_Class, int lOC_method, int cYCLO_method, boolean is_Long_Method) {
		super();
		methodID = methodID;
		m_package = package1;
		m_class = class1;
		m_method = method;
		nom_class = nOM_class;
		loc_class = lOC_class;
		wmc_class = wMC_class;
		this.is_God_Class = is_God_Class;
		loc_method = lOC_method;
		cyclo_method = cYCLO_method;
		this.is_Long_Method = is_Long_Method;
	}
}
