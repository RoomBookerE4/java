package controller.action;

import java.awt.event.ActionEvent;
import java.sql.Time;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DaoFactory;
import model.RoomModel;
import model.UserModel;
import type.TypeAction;

public class RoomAction extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JTextField nameField;
	public JTextField numberField;
	public JTextField floorField;
	public JTextField timeOpenField;
	public JTextField timeCloseField;
	public JComboBox<?> maxTimeBox;
	public JCheckBox isBookableBox;
	
	public JComboBox roomNameToChange;
	
	public String action;
	
	public UserModel user;
	
	DaoFactory daoFactory = DaoFactory.getInstance();
	
	public RoomAction() {
		
	}
	


	public RoomAction(JTextField nameField, JTextField numberField, JTextField floorField, JTextField timeOpenField,
			JTextField timeCloseField, JComboBox<?> maxTimeBox, JCheckBox isBookableBox, String action, UserModel user) {
		super();
		this.nameField = nameField;
		this.numberField = numberField;
		this.floorField = floorField;
		this.timeOpenField = timeOpenField;
		this.timeCloseField = timeCloseField;
		this.maxTimeBox = maxTimeBox;
		this.isBookableBox = isBookableBox;
		this.action = action;
		this.user =user;
	}
	
	public RoomAction(JTextField nameField, JTextField numberField, JTextField floorField, JTextField timeOpenField,
			JTextField timeCloseField, JComboBox<?> maxTimeBox, JCheckBox isBookableBox, String action, UserModel user, JComboBox roomNameToChange) {
		super();
		this.nameField = nameField;
		this.numberField = numberField;
		this.floorField = floorField;
		this.timeOpenField = timeOpenField;
		this.timeCloseField = timeCloseField;
		this.maxTimeBox = maxTimeBox;
		this.isBookableBox = isBookableBox;
		this.action = action;
		this.user =user;
		this.roomNameToChange = roomNameToChange;
	}
	
	
	
	public void addRoom() {
		
	}
	
	public List<RoomModel> searchByEstablishmentId(int idEstablishment) {
		
		
		return daoFactory.getRoomDao().searchByEstablishmentId(idEstablishment);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(action.equals(TypeAction.add)) {
			
			String name = nameField.getText().toString();
			String number = numberField.getText().toString();
			Integer floor = Integer.valueOf(floorField.getText().toString());
			String timeOpen = timeOpenField.getText().toString();
			String timeClose =timeCloseField.getText().toString();
			String maxTime = String.valueOf(maxTimeBox.getSelectedItem());
			
			boolean isBookable = isBookableBox.isSelected();
			
			RoomModel room = new RoomModel(name, number, floor, 
					Time.valueOf(timeOpen), Time.valueOf(timeClose),Time.valueOf( maxTime), isBookable);	
			
			
			if(daoFactory.getRoomDao().addRoom(room, user.getEstablishment())) {
				JOptionPane.showConfirmDialog(new JPanel(), "Room created", "", JOptionPane.OK_OPTION);
			}else {
				JOptionPane.showConfirmDialog(new JPanel(), "Failed to create room", "", JOptionPane.OK_OPTION);
			}
			
			
		}else if (action.equals(TypeAction.edit)) {
			
			String lastName = String.valueOf(roomNameToChange.getSelectedItem());
			String name = nameField.getText().toString();
			String number = numberField.getText().toString();
			Integer floor = Integer.valueOf(floorField.getText().toString());
			String timeOpen = timeOpenField.getText().toString();
			String timeClose =timeCloseField.getText().toString();
			String maxTime = String.valueOf(maxTimeBox.getSelectedItem());
			
			boolean isBookable = isBookableBox.isSelected();
			
			RoomModel room = new RoomModel(name, number, floor, 
					Time.valueOf(timeOpen), Time.valueOf(timeClose),Time.valueOf( maxTime), isBookable);	
			
			if(daoFactory.getRoomDao().editRoom(room, lastName)) {
				JOptionPane.showConfirmDialog(new JPanel(), "Room edited!", "", JOptionPane.OK_OPTION);
				
			}else {
				JOptionPane.showConfirmDialog(new JPanel(), "Failed to edit room", "", JOptionPane.OK_OPTION);
			}
			
		}
	}
	

}
