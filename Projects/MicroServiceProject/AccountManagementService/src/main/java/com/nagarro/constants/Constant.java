package com.nagarro.constants;

public class Constant {
    public static final String NO_DATA_MESSAGE = "No Data Is Present!";
    public static final String SUCCESS_GET_MESSAGE = "Successfully retrieved data!";
    public static final String SUCCESS_ADD_MESSAGE = "Successfully added data!";
    public static final String CONFLICT_MESSAGE = "Conflict occur while processing your request!";
    public static final String UPDATE_SUCCESS_MESSAGE = "Updated Succesfully!";
    public static final String DELETE_SUCCESS_MESSAGE = "Deleted Succesfully!";
    public static final String CONFLICT_MESSAGE_CUSTOMER_EXIST = "Conflict occur while processing your request! "
            + "Maybe customer is already present";
    public static final String CONFLICT_MESSAGE_CUSTOMER_NOT_PRESENT = "Conflict occur while processing your request! "
            + "Maybe customer is not present to edit";

    public static final String ipAddress = "192.168.29.129";
    public static final String customerManagementServiceUrl = "http://"+ipAddress+":8082";

}

