package com.example.rhardie.mypa;

/**
 * Created by Rachel Hardie on 15/09/2017.
 * 300960 (Spring 2017) Mobile Applications Development
 * Kingswood, Thursday 1200
 */

public class TableManager{
    //***********DECLARE VARIABLES***********
    //FRIENDS VARIABLES
    public static final String TB_FRIENDS = "FRIENDS";
    public static final String FRIEND_COL_FNAME = "firstName";
    public static final String FRIEND_COL_LNAME = "lastName";
    public static final String FRIEND_COL_GENDER = "gender";
    public static final String FRIEND_COL_AGE = "age";
    public static final String FRIEND_COL_ADDRESS = "address";
    public static final String FRIEND_COL_SUBURB = "suburb";
    public static final String FRIEND_COL_STATE = "state";
    //EVENTS VARIABLES
    public static final String TB_EVENTS = "EVENTS";
    //TO-DO VARIABLES
    public static final String TB_TODO = "TODO";
    public static final String TODO_COL_NAME = "todoName";
    public static final String TODO_COL_LOCATION = "todoLocation";
    public static final String TODO_COL_COMPLETE = "todoComplete";

    //***********CREATE TABLES****************
    //friend table
    public static final String CREATE_FRIENDS_TABLE
            = "CREATE TABLE " + TB_FRIENDS
            + "( " +
            FRIEND_COL_FNAME +" TEXT, " +
            FRIEND_COL_LNAME+" TEXT, " +
            FRIEND_COL_GENDER+" TEXT, " +
            FRIEND_COL_AGE+" INT, " +
            FRIEND_COL_ADDRESS+" TEXT, " +
            FRIEND_COL_SUBURB+" TEXT, " +
            FRIEND_COL_STATE +" TEXT );";
    //event table
    public static final String CREATE_EVENT_TABLE
            = "CREATE TABLE " + TB_EVENTS
            + " (eventName TEXT, upcoming BOOL, eventDate DATETIME, addressEvent TEXT);";
    //to-do table
    public static final String CREATE_TODO_TABLE
            = "CREATE TABLE " + TB_TODO
            +  "( " +
            TODO_COL_NAME + " TEXT, " +
            TODO_COL_LOCATION +" TEXT, " +
            TODO_COL_COMPLETE + " BOOLEAN);";
}
