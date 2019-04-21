package managedsKravcova;

import entitysLishtvan.Prices;
import manageds.utilLishtvan.JsfUtilLishtvan;
import manageds.utilLishtvan.PaginationHelperLishtvan;
import sessionsShcherbuk.PricesFacadeShcherbuk;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("pricesController")
@SessionScoped
public class PricesControllerKravcova implements Serializable {

    protected Prices current;
    protected DataModel items = null;
    @EJB
    protected sessionsShcherbuk.PricesFacadeShcherbuk ejbFacade;
    protected PaginationHelperLishtvan pagination;
    protected int selectedItemIndex;
    private String[] travelClasses = {"BUSINESS", " ECONOMY", "FIRST"};
    
    
    public PricesControllerKravcova() {
    }

    public String[] getTravelClasses() {
        return travelClasses;
    }

    public void setTravelClasses(String[] travelClasses) {
        this.travelClasses = travelClasses;
    }

    
    
    public Prices getSelected() {
        if (current == null) {
            current = new Prices();
            selectedItemIndex = -1;
        }
        return current;
    }

    protected PricesFacadeShcherbuk getFacade() {
        return ejbFacade;
    }

    public PaginationHelperLishtvan getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelperLishtvan(100) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Prices) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Prices();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtilLishtvan.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PricesCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtilLishtvan.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Prices) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtilLishtvan.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PricesUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtilLishtvan.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Prices) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    protected void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtilLishtvan.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PricesDeleted"));
        } catch (Exception e) {
            JsfUtilLishtvan.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    protected void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    protected void recreateModel() {
        items = null;
    }

    protected void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtilLishtvan.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtilLishtvan.getSelectItems(ejbFacade.findAll(), true);
    }

    public Prices getPrices(java.math.BigDecimal id) {
        return (Prices)ejbFacade.find(id);
    }

    @FacesConverter(forClass = Prices.class)
    public static class PricesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PricesControllerKravcova controller = (PricesControllerKravcova) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pricesController");
            return controller.getPrices(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Prices) {
                Prices o = (Prices) object;
                return getStringKey(o.getPriceId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Prices.class.getName());
            }
        }

    }

    
}
