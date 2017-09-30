package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.DayOfBirth;
import ua.service.DayOfBirthService;

public class DayOfBirthEditor extends PropertyEditorSupport{

	private final DayOfBirthService dayOfBirthService;
	
	public DayOfBirthEditor(DayOfBirthService dayOfBirthService){
		this.dayOfBirthService = dayOfBirthService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		DayOfBirth dayOfBirth = dayOfBirthService.findOne(Integer.valueOf(text));
		setValue(dayOfBirth);
	}
}
