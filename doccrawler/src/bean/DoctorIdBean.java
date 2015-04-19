package bean;

public class DoctorIdBean {

	private String englishName;
	
	private String chineseName;
	
	private String telephone;
	
	private String url;

	public DoctorIdBean(String englishName, String chineseName,
			String telephone, String url) {
		super();
		this.setEnglishName(englishName);
		this.setChineseName(chineseName);
		this.setTelephone(telephone);
		this.setUrl(url);
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
