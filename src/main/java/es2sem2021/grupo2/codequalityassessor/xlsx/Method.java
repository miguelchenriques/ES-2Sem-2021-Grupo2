package es2sem2021.grupo2.codequalityassessor.xlsx;

public class Method {

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

	public Method(String packageName, String className, String method, int nOM_class, int lOC_class,
			int wMC_class, int lOC_method, int cYCLO_method) {
		m_package = packageName;
		m_class = className;
		m_method = method;
		nom_class = nOM_class;
		loc_class = lOC_class;
		wmc_class = wMC_class;
		loc_method = lOC_method;
		cyclo_method = cYCLO_method;
		//this.is_God_Class = is_God_Class;
		//this.is_Long_Method = is_Long_Method;
	}
}
