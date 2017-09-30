package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.MounthOfBirth;
import ua.service.MounthOfBirthService;

public class MounthOfBirthEditor extends PropertyEditorSupport{
	private final MounthOfBirthService mounthOfBirthService;
	
	public MounthOfBirthEditor(MounthOfBirthService mounthOfBirthService){
		this.mounthOfBirthService = mounthOfBirthService;
	}
	
	public void setAsText(String text) throws IllegalArgumentException{
		MounthOfBirth mounthOfBirth = mounthOfBirthService.findOne(Integer.valueOf(text));
		setValue(mounthOfBirth);
	}
}
