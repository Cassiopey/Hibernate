package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.YearOfBirth;
import ua.service.YearOfBirthService;

public class YearOfBirthEditor extends PropertyEditorSupport{

	private final YearOfBirthService yearOfBirthService;
	
	public YearOfBirthEditor(YearOfBirthService yearOfBirthService){
		this.yearOfBirthService = yearOfBirthService;
	}
	
	public void setAsText(String text) throws IllegalArgumentException{
		YearOfBirth yearOfBirth = yearOfBirthService.findOne(Integer.valueOf(text));
		setValue(yearOfBirth);
	}
}
