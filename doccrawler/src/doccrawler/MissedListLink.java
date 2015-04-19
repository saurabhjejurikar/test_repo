package doccrawler;

import org.jsoup.Jsoup;
//import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import bean.DoctorIdBean;
import utils.RegexOperations;
import dbopertions.DoctorDetails;
import dbopertions.DoctorId;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;


/**
 * Example program to list links from a URL.
 */
public class MissedListLink {
	public static void main(String[] args) throws IOException, InterruptedException {
		// String url = "http://jsoup.org/cookbook/extracting-data/example-list-links";
		
		Set<DoctorIdBean> set = DoctorId.missedUrl();
		int i=1;
		for (DoctorIdBean bean: set){
			String engName=bean.getEnglishName();
			String chinese=bean.getChineseName();
			String contactNo=bean.getTelephone();
			String linkUrl = bean.getUrl();
			System.out.println(i +". "+engName);
			i++;
			
			print("%s %s %s ----> %s", engName,chinese,contactNo, linkUrl);
			
			addToDoctorDetails(linkUrl, engName, chinese);
		}


			
	}

	private static void addToDoctorDetails(String linkUrl, String engName,
			String chinese) {
		
		try {
			if (DoctorDetails.recordExists(engName, chinese)){
				System.out.println(engName + " already exists");
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(10000);
			Document doc = Jsoup.connect(linkUrl)
					.referrer("http://www.hkdoctors.org/english/docsearch1.asp")
					.userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36")
					.followRedirects(true)
					.get();


			Elements genderEle = doc.select("tr:contains(Gender:) td:eq(1)");
			Elements qualificationsEle = doc.select("tr:contains(Qualifications:) td:eq(1)");
			Elements registeredQualEle	= doc.select("tr:contains(Qualifications:)+tr td:eq(1) a");
			Elements districtEle = doc.select("tr:contains(District:) td:eq(1)");
			Elements practiceTypeEle = doc.select("tr:contains(Type of Practice:) td:eq(1)");
			Elements consultHrsEle = doc.select("tr:contains(Consultation Hours:) >  td:eq(1)");
			Elements medicalServiceAvailEle= doc.select("tr:contains(Medical Services Available:) >  td:eq(1)");
			Elements othermedicalServiceEle= doc.select("tr:contains(Medical Services Provided Other Than in the Office:) >  td:eq(1)");
			Elements emergencyEle = doc.select("tr:contains(Emergency Service Available:) td:eq(1)");
			Elements feesEle = doc.select("tr:contains(Consultation Fee:) td:eq(1)");
			Elements languageEle = doc.select("tr:contains(Language:) td:eq(1)");
			Elements afflHospitalEle = doc.select("tr:contains(Affiliated Hospitals:) td:eq(1)");
			Elements telephoneEle = doc.select("tr:contains(Telephone:) td:eq(1)");
			Elements faxEle = doc.select("tr:contains(Fax:) td:eq(1)");
			Elements mobEle = doc.select("tr:contains(Mobile Phone:) td:eq(1)");
			Elements emailEle = doc.select("tr:contains(Email:) td:eq(1)");

			String genderStr  = "";
			String qualificationsStr  = "";
			String registeredQualStr	 = "";
			String districtStr  = "";
			String practiceTypeStr  = "";
			String consultHrsStr  = "";
			String medicalServiceAvailStr = "";
			String othermedicalServiceStr = "";
			String emergencyStr  = "";
			String feesStr  = "";
			String languageStr  = "";
			String afflHospitalStr  = "";
			String telephoneStr  = "";
			String faxStr  = "";
			String mobStr  = "";
			String emailStr  = "";

			if (genderEle != null && genderEle.size()>0 && genderEle.get(0).text()!=null){
				genderStr= RegexOperations.replaceQuotesForSql(genderEle.get(0).text());
			}
			if (qualificationsEle != null && qualificationsEle.size()>0 && qualificationsEle.get(0).text()!=null){
				qualificationsStr= RegexOperations.replaceQuotesForSql(qualificationsEle.get(0).text());
			}

			if (registeredQualEle != null && registeredQualEle.size()>0 && registeredQualEle.get(0).text()!=null){
				registeredQualStr= RegexOperations.replaceQuotesForSql(registeredQualEle.get(0).text());
			}

			if (districtEle != null && districtEle.size()>0 && districtEle.get(0).text()!=null){
				districtStr= RegexOperations.replaceQuotesForSql(districtEle.get(0).text());
			}

			if (practiceTypeEle != null && practiceTypeEle.size()>0 && practiceTypeEle.get(0).text()!=null){
				practiceTypeStr= RegexOperations.replaceQuotesForSql(practiceTypeEle.get(0).text());
			}

			if (consultHrsEle != null && consultHrsEle.size()>0 && consultHrsEle.get(0).text()!=null){
				consultHrsStr= RegexOperations.replaceQuotesForSql(consultHrsEle.get(0).text());
			}

			if (medicalServiceAvailEle != null && medicalServiceAvailEle.size()>0 && medicalServiceAvailEle.get(0).text()!=null){
				medicalServiceAvailStr= RegexOperations.replaceQuotesForSql(medicalServiceAvailEle.get(0).text());
			}

			if (othermedicalServiceEle != null && othermedicalServiceEle.size()>0 && othermedicalServiceEle.get(0).text()!=null){
				othermedicalServiceStr= RegexOperations.replaceQuotesForSql(othermedicalServiceEle.get(0).text());
			}

			if (emergencyEle != null && emergencyEle.size()>0 && emergencyEle.get(0).text()!=null){
				emergencyStr= RegexOperations.replaceQuotesForSql(emergencyEle.get(0).text());
			}

			if (feesEle != null && feesEle.size()>0 && feesEle.get(0).text()!=null){
				feesStr= RegexOperations.replaceQuotesForSql(feesEle.get(0).text());
			}

			if (languageEle != null && languageEle.size()>0 && languageEle.get(0).text()!=null){
				languageStr= RegexOperations.replaceQuotesForSql(languageEle.get(0).text());
			}

			if (afflHospitalEle != null && afflHospitalEle.size()>0 && afflHospitalEle.get(0).text()!=null){
				afflHospitalStr= RegexOperations.replaceQuotesForSql(afflHospitalEle.get(0).text());
			}

			if (telephoneEle != null && telephoneEle.size()>0 && telephoneEle.get(0).text()!=null){
				telephoneStr= RegexOperations.replaceQuotesForSql(telephoneEle.get(0).text());
			}

			if (faxEle != null && faxEle.size()>0 && faxEle.get(0).text()!=null){
				faxStr= RegexOperations.replaceQuotesForSql(faxEle.get(0).text());
			}

			if (mobEle != null && mobEle.size()>0 && mobEle.get(0).text()!=null){
				mobStr= RegexOperations.replaceQuotesForSql(mobEle.get(0).text());
			}


			if (emailEle != null && emailEle.size()>0 && emailEle.get(0).text()!=null){
				emailStr= RegexOperations.replaceQuotesForSql(emailEle.get(0).text());
			}

			DoctorDetails.add(engName, chinese, genderStr, emailStr,  qualificationsStr,  registeredQualStr	, districtStr,  practiceTypeStr,  consultHrsStr,  medicalServiceAvailStr, othermedicalServiceStr, emergencyStr,  feesStr,  languageStr,  afflHospitalStr,  telephoneStr,  faxStr,  mobStr,  emailStr);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width-1) + ".";
		else
			return s;
	}
}