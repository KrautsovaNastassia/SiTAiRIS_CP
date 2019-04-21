package managedsKravcova;

import entitysLishtvan.Passengers;
import entitysLishtvan.Users;
import manageds.utilLishtvan.JsfUtilLishtvan;
import manageds.utilLishtvan.PaginationHelperLishtvan;
import sessionsShcherbuk.PassengersFacadeShcherbuk;

import java.io.Serializable;
import java.math.BigInteger;
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

@Named("passengersController")
@SessionScoped
public class PassengersControllerKravcova implements Serializable {

    private Passengers current;
    private DataModel items = null;
    @EJB
    private sessionsShcherbuk.PassengersFacadeShcherbuk ejbFacade;
//    @EJB
//    private sessions.UsersFacade userFacade;
    private PaginationHelperLishtvan pagination;
    private int selectedItemIndex;

    public PassengersControllerKravcova() {
    }

    public Passengers getSelected() {
        if (current == null) {
            current = new Passengers();
            selectedItemIndex = -1;
        }
        return current;
    }

    private PassengersFacadeShcherbuk getFacade() {
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
        current = (Passengers) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Passengers();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtilLishtvan.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PassengersCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtilLishtvan.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Passengers) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtilLishtvan.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PassengersUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtilLishtvan.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Passengers) getItems().getRowData();
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

    private void performDestroy() {
        try {
//            if(current.getUsername().getUserRole().equals(BigInteger.ZERO)) {
//                userFacade.remove((Users)current.getUsername());
//            }
            getFacade().remove(current);
            JsfUtilLishtvan.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PassengersDeleted"));
        } catch (Exception e) {
            JsfUtilLishtvan.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
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

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
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

    public Passengers getPassengers(java.math.BigDecimal id) {
        return (Passengers)ejbFacade.find(id);
    }

    @FacesConverter(forClass = Passengers.class)
    public static class PassengersControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PassengersControllerKravcova controller = (PassengersControllerKravcova) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "passengersController");
            return controller.getPassengers(getKey(value));
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
            if (object instanceof Passengers) {
                Passengers o = (Passengers) object;
                return getStringKey(o.getPassId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Passengers.class.getName());
            }
        }

    }

}
