/**
 * LemoninfoCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.3  Built on : May 30, 2016 (04:08:57 BST)
 */
package com.example;


/**
 *  LemoninfoCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class LemoninfoCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public LemoninfoCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public LemoninfoCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for menus method
     * override this method for handling normal response from menus operation
     */
    public void receiveResultmenus(
        LemoninfoStub.MenusResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from menus operation
     */
    public void receiveErrormenus(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for liveopt method
     * override this method for handling normal response from liveopt operation
     */
    public void receiveResultliveopt(
        LemoninfoStub.LiveoptResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from liveopt operation
     */
    public void receiveErrorliveopt(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for opt method
     * override this method for handling normal response from opt operation
     */
    public void receiveResultopt(
        LemoninfoStub.OptResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from opt operation
     */
    public void receiveErroropt(Exception e) {
    }
}
