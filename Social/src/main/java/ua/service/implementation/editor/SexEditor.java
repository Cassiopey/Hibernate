package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Sex;
import ua.service.SexService;

public class SexEditor extends PropertyEditorSupport{
	
	private final SexService sexService;

	public SexEditor(SexService sexService) {
		this.sexService = sexService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Sex sex = sexService.findOne(Integer.valueOf(text));
		setValue(sex);
	}

}
