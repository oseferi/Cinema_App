package com.al.akademia.converters;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.al.akademia.dao.CategoryDao;
import com.al.akademia.entitete.Category;


@ManagedBean
@RequestScoped
public class CategoryConverter implements Converter {
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
        	
            return CategoryDao.getCategoryById( Integer.valueOf(submittedValue));
           
        } catch (NumberFormatException e) {
        	FacesMessage msg = new FacesMessage("Error converting Category","Invalid Category format");
                 msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                 throw new ConverterException(msg);
        	
        }
    }

	 public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
	        if (modelValue == null) {
	            return "";
	        }
	
	       if (modelValue instanceof Category) {
	    	   
	            return String.valueOf(((Category) modelValue).getId());
	            
	        } else {
	            Throwable e = null;
				throw new ConverterException(new FacesMessage(String.format("%s is not a valid Category", modelValue)), e);
	        }
	    }
	}
