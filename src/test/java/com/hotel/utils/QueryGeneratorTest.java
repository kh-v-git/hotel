package com.hotel.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QueryGeneratorTest {
    String searchText;
    List<String> paramList;
    String resultQuery;
    String expectedQuery;

    @Before
    public void init() {
        paramList = new ArrayList<>();
        searchText = "";
    }

    @Test
    public void shouldGenerateEmptyQuery() {
        //
        // Given
        //
        expectedQuery = "";
        //
        // When
        //
        resultQuery = QueryGenerator.makeQuery(searchText, paramList);
        //
        // Then
        //
        Assert.assertTrue(resultQuery.isEmpty());
        Assert.assertEquals(expectedQuery, resultQuery);
    }

    @Test
    public void shouldGenerateStringQueryWithOneLike() {
        //
        // Given
        //
        expectedQuery = " WHERE LOWER(email) LIKE LOWER('%root%')";
        searchText = "root".toLowerCase();
        paramList.add("email");
        //
        // When
        //
        resultQuery = QueryGenerator.makeQuery(searchText, paramList);
        //
        // Then
        //
        Assert.assertFalse(resultQuery.isEmpty());
        Assert.assertEquals(expectedQuery, resultQuery);
    }

    @Test
    public void shouldGenerateStringQueryWithTwoLike() {
        //
        // Given
        //
        String expectedQuery = " WHERE LOWER(email) LIKE LOWER('%root%') OR LOWER(firstName) LIKE LOWER('%root%')";
        String resultQuery;
        searchText = "root".toLowerCase();
        paramList.add("email");
        paramList.add("firstName");
        //
        // When
        //
        resultQuery = QueryGenerator.makeQuery(searchText, paramList);
        //
        // Then
        //
        Assert.assertFalse(resultQuery.isEmpty());
        Assert.assertEquals(expectedQuery, resultQuery);
    }

    @Test
    public void shouldGenerateStringQueryWithThreeLike() {
        //
        // Given
        //
        String expectedQuery = " WHERE LOWER(email) LIKE LOWER('%root%') OR LOWER(firstName) LIKE LOWER('%root%') OR LOWER(lastName) LIKE LOWER('%root%')";
        String resultQuery;
        searchText = "root".toLowerCase();
        paramList.add("email");
        paramList.add("firstName");
        paramList.add("lastName");
        //
        // When
        //
        resultQuery = QueryGenerator.makeQuery(searchText, paramList);
        //
        // Then
        //
        Assert.assertFalse(resultQuery.isEmpty());
        Assert.assertEquals(expectedQuery, resultQuery);
    }

}