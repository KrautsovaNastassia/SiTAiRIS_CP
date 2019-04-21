package managedsKravcova;

import entitysLishtvan.Flights;
import manageds.utilLishtvan.JsfUtilLishtvan;
import manageds.utilLishtvan.PaginationHelperLishtvan;
import sessionsShcherbuk.FlightsFacadeShcherbuk;

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

@Named("flightsController")
@SessionScoped
public class FlightsControllerKravcova implements Serializable {

    private Flights current;
    private DataModel items = null;
    @EJB
    private sessionsShcherbuk.FlightsFacadeShcherbuk ejbFacade;
    private PaginationHelperLishtvan pagination;
    private int selectedItemIndex;

    public FlightsControllerKravcova() {
    }

    public Flights getSelected() {
        if (current == null) {
            current = new Flights();
            selectedItemIndex = -1;
        }
        return current;
    }

    private FlightsFacadeShcherbuk getFacade() {
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
        current = (Flights) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Flights();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtilLishtvan.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FlightsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtilLishtvan.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Flights) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtilLishtvan.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FlightsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtilLishtvan.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Flights) getItems().getRowData();
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
            getFacade().remove(current);
            JsfUtilLishtvan.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FlightsDeleted"));
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

    public Flights getFlights(java.lang.String id) {
        return (Flights)ejbFacade.find(id);
    }

    @FacesConverter(forClass = Flights.class)
    public static class FlightsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FlightsControllerKravcova controller = (FlightsControllerKravcova) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "flightsController");
            return controller.getFlights(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Flights) {
                Flights o = (Flights) object;
                return getStringKey(o.getFlightCode());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Flights.class.getName());
            }
        }

    }

}
