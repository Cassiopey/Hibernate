package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.entity.Country;
import ua.service.CountryService;

public class CountryValidator implements Validator {

	private final CountryService countryService;
	private final Pattern pattern = Pattern.compile("\\w+");

	public CountryValidator(CountryService countryService) {
		this.countryService = countryService;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return Country.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Country country = (Country) obj;

		Matcher matcher = pattern.matcher(country.getName());
		if (!matcher.matches()) {
			errors.rejectValue("name", "", "Country name can't be empty");
		}
		if (countryService.findByName(country.getName()) != null) {
			errors.rejectValue("name", "",
					"Country with this name already exists");
		}
	}

}
