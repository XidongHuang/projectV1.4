package tony.project.language.interfaces;

import java.util.ArrayList;
import java.util.List;

import tony.project.language.domain.ScoresDetail;

public interface ScoresDetailDM {

	public void saveAScore(ScoresDetail score);

	public ScoresDetail loadAScore(Integer studentID, String courseCode);

	public void deleteAScore(Integer studentID, String courseCode);
	
	public ArrayList<ScoresDetail> getScoresDetailFromJSON(String json);
	
	public void batchSaveScoresDetail(List<ScoresDetail> socresList, String courseCode);
	
	public List<ScoresDetail> loadScoresDetails(String filterConditionName, String filterConditionValue);
}
