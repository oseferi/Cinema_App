package com.al.akademia.converters;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.al.akademia.dao.CountryDao;
import com.al.akademia.entitete.Country;

@ManagedBean
@RequestScoped
public class CountryConverter implements Converter {

	 public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
	        if (submittedValue == null || submittedValue.isEmpty()) {
	            return null;
	        }

	        try {
	        	Country obj = CountryDao.getCountryById( Integer.valueOf(submittedValue));
	            return obj;
	           
	        } catch (NumberFormatException e) {
	        	 e.printStackTrace();
	        	FacesMessage msg = new FacesMessage("Error converting Country","Invalid Country format");
	                 msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	                 throw new ConverterException(msg);
	                
	        	
	        }
	    }

	 public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
	        if (modelValue == null) {
	            return "";
	        }

	       if (modelValue instanceof Country) {
	    	   String stringToBeReturned = String.valueOf(((Country) modelValue).getId());
	            return stringToBeReturned;
	        } else {
	            Throwable e = null;
				throw new ConverterException(new FacesMessage(String.format("%s is not a valid User", modelValue)), e);
	        }
	    }

}
