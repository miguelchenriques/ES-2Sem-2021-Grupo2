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
	}
	
	
	public int getMetric(String metric) throws IllegalArgumentException {
		switch (metric) {
		case Constants.LOC_CLASS: 
			return loc_class;
		case Constants.CYCLO_METHOD:
			return cyclo_method;
		case Constants.LOC_METHOD:
			return loc_method;
		case Constants.NOM:
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
