/*
 * Written by Harry Silman
 */
package coffeedatabase;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;



/**
 *
 * @author Silman
 */
public class Coffee {
    
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty company;
    private final SimpleStringProperty beanName;
    private final SimpleStringProperty roastMethod;
    private final SimpleStringProperty weight;
    private final SimpleStringProperty timeToFirstCrack;
    private final SimpleStringProperty timeToSecondCrack;
    private final SimpleStringProperty totalRoastTime;
    private final SimpleStringProperty flavors;
    private final SimpleStringProperty rating;

    public Coffee() {
        this.rating = new SimpleStringProperty();
        this.flavors = new SimpleStringProperty("");
        this.totalRoastTime = new SimpleStringProperty();
        this.timeToSecondCrack = new SimpleStringProperty();
        this.timeToFirstCrack = new SimpleStringProperty();
        this.weight = new SimpleStringProperty();
        this.roastMethod = new SimpleStringProperty("");
        this.beanName = new SimpleStringProperty("");
        this.company = new SimpleStringProperty("");
        this.id = new SimpleIntegerProperty();
    }

    public Coffee(SimpleIntegerProperty id, SimpleStringProperty company, SimpleStringProperty beanName, SimpleStringProperty roastMethod, SimpleStringProperty weight, SimpleStringProperty timeToFirstCrack, SimpleStringProperty timeToSecondCrack, SimpleStringProperty totalRoastTime, SimpleStringProperty flavors, SimpleStringProperty rating) {
        this.id = id;
        this.company = company;
        this.beanName = beanName;
        this.roastMethod = roastMethod;
        this.weight = weight;
        this.timeToFirstCrack = timeToFirstCrack;
        this.timeToSecondCrack = timeToSecondCrack;
        this.totalRoastTime = totalRoastTime;
        this.flavors = flavors;
        this.rating = rating;
        
    }
    
    // constructor with null ID
    public Coffee( SimpleStringProperty company, SimpleStringProperty beanName, SimpleStringProperty roastMethod, SimpleStringProperty weight, SimpleStringProperty timeToFirstCrack, SimpleStringProperty timeToSecondCrack, SimpleStringProperty totalRoastTime, SimpleStringProperty flavors, SimpleStringProperty rating) {
        this.id = null;
        this.company = company;
        this.beanName = beanName;
        this.roastMethod = roastMethod;
        this.weight = weight;
        this.timeToFirstCrack = timeToFirstCrack;
        this.timeToSecondCrack = timeToSecondCrack;
        this.totalRoastTime = totalRoastTime;
        this.flavors = flavors;
        this.rating = rating;
        
    }
    
    public SimpleIntegerProperty idProperty() {
        return id;
    }
    
    public final void setID(int value) {
        id.set(value);
    }
    
    public SimpleStringProperty companyProperty() {
        return company;
    }

    public final void setCompany(String value) {
        company.set(value);
    }

    public SimpleStringProperty beanNameProperty() {
        return beanName;
    }

    public final void setBeanName(String value) {
        beanName.set(value);
    }

    public SimpleStringProperty roastMethodProperty() {
        return roastMethod;
    }

    public final void setRoastMethod(String value) {
        roastMethod.set(value);
    }

    public SimpleStringProperty weightProperty() {
        return weight;
    }

    public final void setWeight(String value) {
        weight.set(value);
    }

    public SimpleStringProperty timeToFirstCrackProperty() {
        return timeToFirstCrack;
    }

    public final void setTimeToFirstCrack(String value) {
        timeToFirstCrack.set(value);
    }

    public SimpleStringProperty timeToSecondCrackProperty() {
        return timeToSecondCrack;
    }

    public final void setTimeToSecondCrack(String value) {
        timeToSecondCrack.set(value);
    }

    public SimpleStringProperty totalRoastTimeProperty() {
        return totalRoastTime;
    }

    public final void setTotalRoastTime(String value) {
        totalRoastTime.set(value);
    }

    public SimpleStringProperty flavorsProperty() {
        return flavors;
    }

    public final void setFlavors(String value) {
        flavors.set(value);
    }

    public SimpleStringProperty ratingProperty() {
        return rating;
    }

    public final void setRating(String value) {
        rating.set(value);
    }

    

}
