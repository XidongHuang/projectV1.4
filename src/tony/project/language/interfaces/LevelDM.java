package tony.project.language.interfaces;

import tony.project.language.domain.Level;

public interface LevelDM {

	
	public void saveALevel(Level level);
	
	public Level loadALevel(Integer levelID, String semester);
	
	public void deleteALevel(Integer levelID, String semester);
}
