package es2sem2021.grupo2.codequalityassessor.xlsx;

import es2sem2021.grupo2.codequalityassessor.rules.Constants;

public class Method {

	public String m_package;
	public String m_class;
	public String m_method;
	public int nom_class;
	public int loc_class;
	public int wmc_class;
	public int loc_method;
	public int cyclo_method;

	/**
	 * 
	 * @param packageName
	 * @param className
	 * @param method
	 * @param NOM
	 * @param LOC_Class
	 * @param WMC_Class
	 * @param LOC_Method
	 * @param CYCLO_Method
	 */
	public Method(String packageName, String className, String method, int NOM, int LOC_Class,
			int WMC_Class, int LOC_Method, int CYCLO_Method) {
		m_package = packageName;
		m_class = className;
		m_method = method.replaceAll(" ", "");

	}
	
	/**
	 * 
	 * Returns the metric whose name corresponds to the passed string
	 * 
	 * @param metric	name
	 * @return			value from the metric
	 * @throws IllegalArgumentException	if the name doesn't correspond to any metric 
	 */
	public int getMetric(String metric) throws IllegalArgumentException {
		switch (metric) {
		case Constants.LOC_CLASS: 
			return loc_class;
		case Constants.CYCLO_METHOD:
			return cyclo_method;
		case Constants.LOC_METHOD:
			return loc_method;
		case Constants.NOM_CLASS:
			return nom_class;
		case Constants.WMC_CLASS:
			return wmc_class;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "Method [m_package=" + m_package + ", m_class=" + m_class + ", m_method=" + m_method + ", nom_class="
				+ nom_class + ", loc_class=" + loc_class + ", wmc_class=" + wmc_class + ", loc_method=" + loc_method
				+ ", cyclo_method=" + cyclo_method + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Method other = (Method) obj;
		
		return other.cyclo_method == cyclo_method &&
				other.loc_class == loc_class &&
				other.loc_method == loc_method &&
				other.m_class.equals(m_class) &&
				other.m_method.equals(m_method) &&
				other.m_package.equals(m_package) && 
				other.nom_class == nom_class &&
				other.wmc_class == wmc_class;		
	}
	
	
}
