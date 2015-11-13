package tony.project.language.interfaces;


import tony.project.language.domain.Staff;

public interface StaffDM {

	public void saveAStaff(Staff staff);
	
	public Staff loadAStaff(Integer staffID);
	
	public void deleteAStaff(Integer staffID);
	
	public Staff loadStaffByAccountName(String accountName);
	
	
}
