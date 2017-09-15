package com.example.rhardie.mypa;

/**
 * Created by Rachel Hardie on 15/09/2017.
 * 300960 (Spring 2017) Mobile Applications Development
 * Kingswood, Thursday 1200
 */

public class TableManager{
    public static final String TB_FRIENDS = "FRIENDS";
    public static final String TB_EVENTS = "EVENTS";
    public static final String TB_TODO = "TODO";
    public static final String TODO_COL_NAME = "todoName";
    public static final String TODO_COL_LOCATION = "todoLocation";
    public static final String TODO_COL_COMPLETE = "todoComplete";
    //create tables
    //friend table
    public static final String CREATE_FRIENDS_TABLE
            = "CREATE TABLE " + TB_FRIENDS
            + " (firstName TEXT, lastName TEXT, gender TEXT, age INT, address TEXT, suburb TEXT, state TEXT);";
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
